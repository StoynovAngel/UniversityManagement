package com.angel.uni.management.utils.mappers;

import com.angel.uni.management.dto.SubjectDTO;
import com.angel.uni.management.entity.Student;
import com.angel.uni.management.entity.Subject;
import com.angel.uni.management.entity.Teacher;
import com.angel.uni.management.interfaces.CustomRowMapper;
import com.angel.uni.management.utils.exceptions.DataMappingException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Singleton class (double-checked locking) responsible for mapping between Subject entities and SubjectDTO objects.
 * <p>
 * This class prevents instantiation and provides a static method
 * {@link #getInstance()} to obtain the properties.
 * </p>
 */

public class SubjectMapper implements CustomRowMapper<SubjectDTO, Subject> {
    private static volatile SubjectMapper instance;

    private SubjectMapper() {
        if (instance != null) {
            throw new UnsupportedOperationException("Should not instantiate " + getClass().getSimpleName());
        }
    }

    public static SubjectMapper getInstance() {
        if (instance == null) {
            synchronized (SubjectMapper.class) {
                if (instance == null) {
                    instance = new SubjectMapper();
                }
            }
        }
        return instance;
    }

    public Subject mapToEntity(SubjectDTO subjectDTO) {
        return entityForm(subjectDTO);
    }

    public SubjectDTO mapToDTO(Subject subject) {
        return dtoForm(subject);
    }

    @Override
    public Subject mapRow(ResultSet resultSet) throws DataMappingException {
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

    private Subject mapForm(ResultSet resultSet) throws DataMappingException {
        Mappers.checkResultSetForNull(resultSet);
        Subject subject = mapSubject(resultSet);
        Teacher teacher = TeacherMapper.getInstance().mapRow(resultSet);
        subject.setTeacher(teacher);

        List<Student> students = StudentMapper.getInstance().mapAllStudents(resultSet);
        subject.setStudentsAssignedToSubject(students);

        return subject;
    }

    private Subject mapSubject(ResultSet resultSet) throws DataMappingException {
        Mappers.checkResultSetForNull(resultSet);
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
            String errorMessage = "Error mapping database result to Subject";
            throw new DataMappingException(errorMessage, e);
        }
    }
}
