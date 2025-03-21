package com.angel.uni.management.utils.mappers;

import com.angel.uni.management.dto.GroupDTO;
import com.angel.uni.management.dto.StudentDTO;
import com.angel.uni.management.entity.Grade;
import com.angel.uni.management.entity.Group;
import com.angel.uni.management.entity.Student;
import com.angel.uni.management.interfaces.CustomRowMapper;
import com.angel.uni.management.utils.exceptions.DataMappingException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Singleton class (double-checked locking) responsible for mapping between Group entities and GroupDTO objects.
 * <p>
 * This class prevents instantiation and provides a static method
 * {@link #getInstance()} to obtain the properties.
 * </p>
 */

public class GroupMapper implements CustomRowMapper<GroupDTO, Group> {
    private static volatile GroupMapper instance;

    private GroupMapper() {
        if (instance != null) {
            throw new UnsupportedOperationException("Should not instantiate " + getClass().getSimpleName());
        }
    }

    public static GroupMapper getInstance() {
        if (instance == null) {
            synchronized (GroupMapper.class) {
                if (instance == null) {
                    instance = new GroupMapper();
                }
            }
        }
        return instance;
    }

    @Override
    public Group mapToEntity(GroupDTO groupDTO) {
        return entityForm(groupDTO);
    }

    @Override
    public GroupDTO mapToDTO(Group group) throws DataMappingException {
        return dtoForm(group);
    }

    @Override
    public Group mapRow(ResultSet resultSet) throws DataMappingException {
        return mapForm(resultSet);
    }

    private Group entityForm(GroupDTO groupDTO) {
        Group group = new Group();
        group.setGroupName(groupDTO.groupName());
        return group;
    }

    private GroupDTO dtoForm(Group group) throws DataMappingException {
        List<StudentDTO> students = new ArrayList<>();
        for (Student student: group.getStudentsAssignedToGroup()) {
            students.add(StudentMapper.getInstance().mapToDTO(student));
        }
        return new GroupDTO(
                group.getGroupName(),
                students
        );
    }

    private Group mapForm(ResultSet resultSet) throws DataMappingException {
        Group group = null;
        Map<Long, Student> studentMap = new HashMap<>();
        Mappers.checkResultSetForNull(resultSet);

        try {
            do {
                if (group == null) {
                    group = mapGroup(resultSet);
                }

                Long studentId = resultSet.getLong(TableMapperConstants.STUDENT_ID);
                Student student = studentMap.computeIfAbsent(studentId, id -> {
                    try {
                        return Mappers.getStudentMapper().mapStudentById(resultSet, id);
                    } catch (DataMappingException e) {
                        throw new RuntimeException(e);
                    }
                });

                if (resultSet.getObject(TableMapperConstants.GRADE_ID) != null) {
                    Grade grade = Mappers.getGradeMapper().mapLight(resultSet);
                    student.getGrades().add(grade);
                }
            } while (resultSet.next());

            group.setStudentsAssignedToGroup(new ArrayList<>(studentMap.values()));
            return group;
        } catch (SQLException e) {
            String errorMessage = "Failed to map ResultSet to Group object.";
            throw new DataMappingException(errorMessage, e);
        }
    }

    private Group mapGroup(ResultSet resultSet) throws DataMappingException {
        Mappers.checkResultSetForNull(resultSet);
        try {
            return new Group(
                    resultSet.getLong(TableMapperConstants.GROUP_ID),
                    resultSet.getString(TableMapperConstants.GROUP_NAME),
                    null
            );
        } catch (SQLException e) {
            String errorMessage = "Failed to map ResultSet to Group object.";
            throw new DataMappingException(errorMessage, e);
        }
    }
}
