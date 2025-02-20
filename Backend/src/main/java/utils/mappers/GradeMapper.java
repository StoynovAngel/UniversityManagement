package utils.mappers;

import config.QueryLogger;
import dto.GradeDTO;
import entity.*;
import enums.GradeType;
import interfaces.CustomRowMapper;
import utils.exceptions.DataMappingException;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Singleton class (double-checked locking) responsible for mapping between Grade entities and GradeDTO objects.
 *  <p>
 *  This class prevents instantiation and provides a static method
 *  {@link #getUniqueInstance()} to obtain the properties.
 *  </p>
 */

public class GradeMapper implements CustomRowMapper<GradeDTO, Grade> {
    private static volatile GradeMapper uniqueInstance;

    private GradeMapper() {
        if (uniqueInstance != null) {
            throw new UnsupportedOperationException("Should not instantiate " + getClass().getSimpleName());
        }
    }

    public static GradeMapper getUniqueInstance() {
        if (uniqueInstance == null) {
            synchronized (GradeMapper.class) {
                if (uniqueInstance == null) {
                    uniqueInstance = new GradeMapper();
                }
            }
        }
        return uniqueInstance;
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
    public Grade mapRow(ResultSet resultSet) {
        return mapForm(resultSet);
    }

    public Grade mapLight(ResultSet resultSet) {
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
            QueryLogger.logError("Failed to map ResultSet within mapLight to Grade object.", e);
            throw new DataMappingException("Error mapping database result to Grade.", e);
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

    private Grade mapForm(ResultSet resultSet) {
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
            QueryLogger.logError("Failed to map ResultSet to Grade object", e);
            throw new DataMappingException("Error mapping database result to Grade", e);
        }
    }
}