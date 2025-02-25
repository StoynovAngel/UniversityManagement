package com.angel.uni.management.utils.mappers;

import com.angel.uni.management.config.QueryLogger;
import com.angel.uni.management.dto.TeacherDTO;
import com.angel.uni.management.entity.Teacher;
import com.angel.uni.management.interfaces.CustomRowMapper;
import com.angel.uni.management.utils.exceptions.DataMappingException;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Singleton class (double-checked locking) responsible for mapping between Teacher entities and TeacherDTO objects.
 *  <p>
 *  This class prevents instantiation and provides a static method
 *  {@link #getUniqueInstance()} to obtain the properties.
 *  </p>
 */

public class TeacherMapper implements CustomRowMapper<TeacherDTO, Teacher> {
    private static volatile TeacherMapper uniqueInstance;

    private TeacherMapper() {
        if (uniqueInstance != null) {
            throw new UnsupportedOperationException("Should not instantiate " + getClass().getSimpleName());
        }
    }

    public static TeacherMapper getUniqueInstance() {
        if (uniqueInstance == null) {
            synchronized (TeacherMapper.class) {
                if (uniqueInstance == null) {
                    uniqueInstance = new TeacherMapper();
                }
            }
        }
        return uniqueInstance;
    }

    @Override
    public Teacher mapToEntity(TeacherDTO teacherDTO) {
        return entityForm(teacherDTO);
    }

    @Override
    public TeacherDTO mapToDTO(Teacher teacher) {
        return dtoForm(teacher);
    }

    @Override
    public Teacher mapRow(ResultSet resultSet) {
        return mapForm(resultSet);
    }

    private Teacher entityForm(TeacherDTO teacherDTO) {
        Teacher newTeacher = new Teacher();
        newTeacher.setName(teacherDTO.name());
        return newTeacher;
    }

    private TeacherDTO dtoForm(Teacher teacher) {
        return new TeacherDTO(
                teacher.getName()
        );
    }

    private Teacher mapForm(ResultSet resultSet) {
        Mappers.checkResultSetForNull(resultSet);
        try {
            return new Teacher(
                resultSet.getLong(TableMapperConstants.TEACHER_ID),
                resultSet.getString(TableMapperConstants.TEACHER_NAME)
            );
        } catch (SQLException e) {
            QueryLogger.logError("Failed to map ResultSet to Teacher object.", e);
            throw new DataMappingException("Error mapping database result to Teacher.", e);
        }
    }
}