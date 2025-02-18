package utils.mappers;

import config.QueryLogger;
import dto.GroupDTO;
import entity.*;
import interfaces.CustomRowMapper;
import utils.exceptions.DataMappingException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * Singleton class (double-checked locking) responsible for mapping between Group entities and GroupDTO objects.
 *  <p>
 *  This class prevents instantiation and provides a static method
 *  {@link #getUniqueInstance()} to obtain the properties.
 *  </p>
 */

public class GroupMapper implements CustomRowMapper<GroupDTO, Group> {
    private static volatile GroupMapper uniqueInstance;

    private GroupMapper() {
        if (uniqueInstance != null) {
            throw new UnsupportedOperationException("Should not instantiate " + getClass().getSimpleName());
        }
    }

    public static GroupMapper getUniqueInstance() {
        if (uniqueInstance == null) {
            synchronized (GroupMapper.class) {
                if (uniqueInstance == null) {
                    uniqueInstance = new GroupMapper();
                }
            }
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
        Mappers.checkResultSetForNull(resultSet);

        try{
            do {
                if (group == null) {
                    group = mapGroup(resultSet);
                }

                Long studentId = resultSet.getLong(TableMapperConstants.STUDENT_ID);
                Student student = studentMap.computeIfAbsent(studentId, id -> Mappers.getStudentMapper().mapStudentById(resultSet, id));

                if (resultSet.getObject(TableMapperConstants.GRADE_ID) != null) {
                    Grade grade = Mappers.getGradeMapper().mapLight(resultSet);
                    student.getGrades().add(grade);
                }
            } while (resultSet.next());

            group.setStudentsAssignedToGroup(new ArrayList<>(studentMap.values()));
            return group;
        }  catch (SQLException e) {
            QueryLogger.logError("Failed to map ResultSet to Group object within mapForm.", e);
            throw new DataMappingException("Error mapping database result to Group.", e);
        }
    }

    private Group mapGroup(ResultSet resultSet) {
        Mappers.checkResultSetForNull(resultSet);
        try {
            return new Group(
                resultSet.getLong(TableMapperConstants.GROUP_ID),
                resultSet.getString(TableMapperConstants.GROUP_NAME),
                null
            );
        } catch (SQLException e) {
            QueryLogger.logError("Failed to map ResultSet to Group object.", e);
            throw new DataMappingException("Error mapping database result to Group.", e);
        }
    }
}
