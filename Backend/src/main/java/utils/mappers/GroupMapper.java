package utils.mappers;

import config.QueryLogger;
import dto.GroupDTO;
import entity.*;
import interfaces.CustomRowMapper;
import utils.exceptions.DataMappingException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class GroupMapper implements CustomRowMapper<GroupDTO, Group> {
    private static GroupMapper uniqueInstance;

    private GroupMapper() {
    }

    public static GroupMapper getUniqueInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new GroupMapper();
        }
        return uniqueInstance;
    }

    @Override
    public Group mapToEntity(GroupDTO groupDTO) {
        return entityForm(groupDTO);
    }

    @Override
    public GroupDTO mapToDTO(Group group) {
        return dtoForm(group);
    }

    @Override
    public Group mapRow(ResultSet resultSet) {
        return mapForm(resultSet);
    }

    private Group entityForm(GroupDTO groupDTO) {
        Group group = new Group();
        group.setGroupName(groupDTO.groupName());
        return group;
    }

    private GroupDTO dtoForm(Group group) {
        return new GroupDTO(
                group.getGroupName(),
                group.getStudentsAssignedToGroup().stream().map(Mappers.getStudentMapper()::mapToDTO).toList()
        );
    }

    private Group mapForm(ResultSet resultSet) {
        Group group = null;
        Map<Long, Student> studentMap = new HashMap<>();

        if (resultSet == null) {
            QueryLogger.logMessage("ResultSet is null. Cannot map Group.");
            throw new DataMappingException("ResultSet is null. Mapping failed.");
        }

        try{
            do {
                if (group == null) {
                    group = mapGroup(resultSet);
                }

                Long studentId = resultSet.getLong(TableMapperConstants.STUDENT_ID);
                Student student = studentMap.computeIfAbsent(studentId, id -> Mappers.getStudentMapper().mapLight(resultSet, id));

                if (resultSet.getObject(TableMapperConstants.GRADE_ID) != null) {
                    Grade grade = Mappers.getGradeMapper().mapLight(resultSet);
                    student.getGrades().add(grade);
                }
            } while (resultSet.next());

            group.setStudentsAssignedToGroup(new ArrayList<>(studentMap.values()));
            return group;
        }  catch (SQLException e) {
            QueryLogger.logError("Failed to map ResultSet to Group object with ", e);
            throw new DataMappingException("Error mapping database result to Group", e);
        }
    }

    private Group mapGroup(ResultSet resultSet) {
        try {
            return new Group(
                resultSet.getLong(TableMapperConstants.GROUP_ID),
                resultSet.getString(TableMapperConstants.GROUP_NAME),
                null
            );
        } catch (SQLException e) {
            QueryLogger.logError("Failed to map ResultSet to Group object", e);
            throw new DataMappingException("Error mapping database result to Group", e);
        }
    }
}
