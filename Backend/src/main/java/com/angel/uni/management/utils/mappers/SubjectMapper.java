package com.angel.uni.management.utils.mappers;

import com.angel.uni.management.dto.StudentDTO;
import com.angel.uni.management.dto.SubjectDTO;
import com.angel.uni.management.entity.Student;
import com.angel.uni.management.entity.Subject;
import com.angel.uni.management.entity.Teacher;
import com.angel.uni.management.interfaces.CustomRowMapper;
import com.angel.uni.management.utils.exceptions.DataMappingException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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

    public Subject mapToEntity(SubjectDTO subjectDTO) throws DataMappingException {
        return entityForm(subjectDTO);
    }

    public SubjectDTO mapToDTO(Subject subject) throws DataMappingException {
        return dtoForm(subject);
    }

    @Override
    public Subject mapRow(ResultSet resultSet) throws DataMappingException {
        return mapForm(resultSet);
    }

    private Subject entityForm(SubjectDTO subjectDTO) throws DataMappingException {
        Teacher teacher = TeacherMapper.getInstance().mapToEntity(subjectDTO.teacher());
        List<Student> studentList = new ArrayList<>();

        for(StudentDTO studentDTO: subjectDTO.students()) {
            studentList.add(StudentMapper.getInstance().mapToEntity(studentDTO));
        }
        return new Subject(
                subjectDTO.name(),
                subjectDTO.hoursPerWeek(),
                subjectDTO.description(),
                teacher,
                studentList
        );
    }

    private SubjectDTO dtoForm(Subject subject) throws DataMappingException {
        List<StudentDTO> studentDTOS = new ArrayList<>();

        for(Student student: subject.getStudentsAssignedToSubject()) {
            studentDTOS.add(StudentMapper.getInstance().mapToDTO(student));
        }

        return new SubjectDTO(
                subject.getName(),
                subject.getHoursPerWeek(),
                subject.getDescription(),
                Mappers.getTeacherMapper().mapToDTO(subject.getTeacher()),
                studentDTOS
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
