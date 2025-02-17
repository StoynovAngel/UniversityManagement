package utils.mappers;

import config.QueryLogger;
import dto.SubjectDTO;
import entity.*;
import interfaces.CustomRowMapper;
import utils.exceptions.DataMappingException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * Singleton class (double-checked locking) responsible for mapping between Grade entities and GradeDTO objects.
 *  <p>
 *  This class prevents instantiation and provides a static method
 *  {@link #getUniqueInstance()} to obtain the properties.
 *  </p>
 */

public class SubjectMapper implements CustomRowMapper<SubjectDTO, Subject> {
    private static volatile SubjectMapper uniqueInstance;

    private SubjectMapper() {
        if (uniqueInstance != null) {
            throw new UnsupportedOperationException("Should not instantiate " + getClass().getSimpleName());
        }
    }

    public static SubjectMapper getUniqueInstance() {
        if (uniqueInstance == null) {
            synchronized (SubjectMapper.class) {
                if (uniqueInstance == null) {
                    uniqueInstance = new SubjectMapper();
                }
            }
        }
        return uniqueInstance;
    }

    public Subject mapToEntity(SubjectDTO subjectDTO) {
        return entityForm(subjectDTO);
    }

    public SubjectDTO mapToDTO(Subject subject) {
        return dtoForm(subject);
    }

    @Override
    public Subject mapRow(ResultSet resultSet) {
        return mapForm(resultSet);
    }

    private Subject entityForm(SubjectDTO subjectDTO) {
        Subject newSubject = new Subject();
        newSubject.setName(subjectDTO.name());
        newSubject.setHoursPerWeek(subjectDTO.hoursPerWeek());
        newSubject.setDescription(subjectDTO.description());
        newSubject.setTeacher(Mappers.getTeacherMapper().mapToEntity(subjectDTO.teacher()));
        newSubject.setStudentsAssignedToSubject(subjectDTO.students().stream().map(Mappers.getStudentMapper()::mapToEntity).toList());
        return newSubject;
    }

    private SubjectDTO dtoForm(Subject subject) {
        return new SubjectDTO(
                subject.getName(),
                subject.getHoursPerWeek(),
                subject.getDescription(),
                Mappers.getTeacherMapper().mapToDTO(subject.getTeacher()),
                subject.getStudentsAssignedToSubject().stream().map(Mappers.getStudentMapper()::mapToDTO).toList()
        );
    }

    private Subject mapForm(ResultSet resultSet){
        Subject subject = mapSubject(resultSet);
        Teacher teacher = TeacherMapper.getUniqueInstance().mapRow(resultSet);
        subject.setTeacher(teacher);

        List<Student> students = StudentMapper.getUniqueInstance().mapAllStudents(resultSet);
        subject.setStudentsAssignedToSubject(students);

        return subject;
    }

    private Subject mapSubject(ResultSet resultSet) {
        try {
            return new Subject(
                    resultSet.getLong(TableMapperConstants.SUBJECT_ID),
                    resultSet.getString(TableMapperConstants.SUBJECT_NAME),
                    resultSet.getInt(TableMapperConstants.SUBJECT_HOURS_PER_WEEK),
                    resultSet.getString(TableMapperConstants.SUBJECT_DESCRIPTION),
                    null,
                    null
            );
        } catch (SQLException e) {
            QueryLogger.logError("Failed to map ResultSet to Subject object", e);
            throw new DataMappingException("Error mapping database result to Subject", e);
        }
    }
}
