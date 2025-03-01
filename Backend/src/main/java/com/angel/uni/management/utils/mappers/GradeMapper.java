package com.angel.uni.management.utils.mappers;

import com.angel.uni.management.dto.GradeDTO;
import com.angel.uni.management.entity.Grade;
import com.angel.uni.management.entity.GradeBG;
import com.angel.uni.management.entity.Student;
import com.angel.uni.management.entity.Teacher;
import com.angel.uni.management.enums.GradeType;
import com.angel.uni.management.interfaces.CustomRowMapper;
import com.angel.uni.management.utils.exceptions.DataMappingException;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Singleton class (double-checked locking) responsible for mapping between Grade entities and GradeDTO objects.
 * <p>
 * This class prevents instantiation and provides a static method
 * {@link #getInstance()} to obtain the properties.
 * </p>
 */

public class GradeMapper implements CustomRowMapper<GradeDTO, Grade> {
    private static volatile GradeMapper instance;

    private GradeMapper() {
        if (instance != null) {
            throw new UnsupportedOperationException("Should not instantiate " + getClass().getSimpleName());
        }
    }

    public static GradeMapper getInstance() {
        if (instance == null) {
            synchronized (GradeMapper.class) {
                if (instance == null) {
                    instance = new GradeMapper();
                }
            }
        }
        return instance;
    }

    @Override
    public Grade mapToEntity(GradeDTO dto) {
        return entityForm(dto);
    }

    @Override
    public GradeDTO mapToDTO(Grade entity) {
        return dtoForm(entity);
    }

    @Override
    public Grade mapRow(ResultSet resultSet) throws DataMappingException {
        return mapForm(resultSet);
    }

    public Grade mapLight(ResultSet resultSet) throws DataMappingException {
        Mappers.checkResultSetForNull(resultSet);
        try {
            return new GradeBG(
                    resultSet.getString(TableMapperConstants.GRADE_NAME),
                    null,
                    null,
                    GradeType.valueOf(resultSet.getString(TableMapperConstants.GRADE_TYPE)),
                    resultSet.getDouble(TableMapperConstants.GRADE_MARK),
                    resultSet.getDate(TableMapperConstants.GRADE_DATE_OF_GRADING)
            );
        } catch (SQLException e) {
            String errorMessage = "Failed to map ResultSet to GradeBG object.";
            throw new DataMappingException(errorMessage, e);
        }
    }

    private Grade entityForm(GradeDTO dto) {
        return new GradeBG(
                dto.name(),
                Mappers.getStudentMapper().mapToEntity(dto.student()),
                Mappers.getTeacherMapper().mapToEntity(dto.teacher()),
                dto.gradeType(),
                dto.mark(),
                dto.dateOfGrading()
        );
    }

    private GradeDTO dtoForm(Grade entity) {
        return new GradeDTO(
                entity.getName(),
                Mappers.getStudentMapper().mapToDTO(entity.getStudent()),
                Mappers.getTeacherMapper().mapToDTO(entity.getTeacher()),
                entity.getGradeType(),
                entity.getMark(),
                entity.getDateOfGrading()
        );
    }

    private Grade mapForm(ResultSet resultSet) throws DataMappingException {
        Mappers.checkResultSetForNull(resultSet);
        try {
            return new GradeBG(
                    resultSet.getString(TableMapperConstants.GRADE_NAME),
                    new Student(resultSet.getLong(TableMapperConstants.STUDENT_ID), resultSet.getString(TableMapperConstants.STUDENT_USERNAME)),
                    new Teacher(resultSet.getLong(TableMapperConstants.TEACHER_ID), resultSet.getString(TableMapperConstants.TEACHER_NAME)),
                    GradeType.valueOf(resultSet.getString(TableMapperConstants.GRADE_TYPE)),
                    resultSet.getDouble(TableMapperConstants.GRADE_MARK),
                    resultSet.getDate(TableMapperConstants.GRADE_DATE_OF_GRADING)
            );
        } catch (SQLException e) {
            String errorMessage = "Failed to map ResultSet to GradeBG object.";
            throw new DataMappingException(errorMessage, e);
        }
    }
}