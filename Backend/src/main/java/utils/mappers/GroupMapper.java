package utils.mappers;

import dto.GroupDTO;
import entity.*;
import interfaces.CustomRowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class GroupMapper extends Mappers implements CustomRowMapper<GroupDTO, Group> {

    @Override
    public Group mapToEntity(GroupDTO groupDTO) {
        return entityForm(groupDTO);
    }

    @Override
    public GroupDTO mapToDTO(Group group) {
        return dtoForm(group);
    }

    @Override
    public Group mapRow(ResultSet resultSet) throws SQLException {
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
                group.getStudentsAssignedToGroup().stream().map(getStudentMapper()::mapToDTO).toList()
        );
    }

    private Group mapForm(ResultSet resultSet) throws SQLException {
        Group group = null;
        Map<Long, Student> studentMap = new HashMap<>();

        do {
            if (group == null) {
                group = mapGroup(resultSet);
            }

            Long studentId = resultSet.getLong(TableMapperConstants.STUDENT_ID);
            Student student = studentMap.computeIfAbsent(studentId, id -> getStudentMapper().mapLight(resultSet, id));

            if (resultSet.getObject(TableMapperConstants.GRADE_ID) != null) {
                Grade grade = getGradeMapper().mapLight(resultSet);
                student.getGrades().add(grade);
            }
        } while (resultSet.next());

        group.setStudentsAssignedToGroup(new ArrayList<>(studentMap.values()));

        return group;
    }

    private Group mapGroup(ResultSet resultSet) throws SQLException {
        Group group = new Group();
        group.setId(resultSet.getLong(TableMapperConstants.GROUP_ID));
        group.setGroupName(resultSet.getString(TableMapperConstants.GROUP_NAME));
        return group;
    }
}
