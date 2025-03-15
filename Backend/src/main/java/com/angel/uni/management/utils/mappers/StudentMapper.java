package com.angel.uni.management.utils.mappers;

import com.angel.uni.management.config.QueryLogger;
import com.angel.uni.management.dto.GradeDTO;
import com.angel.uni.management.dto.StudentDTO;
import com.angel.uni.management.entity.Grade;
import com.angel.uni.management.entity.Student;
import com.angel.uni.management.interfaces.CustomRowMapper;
import com.angel.uni.management.menu.gui.Page;
import com.angel.uni.management.utils.exceptions.DataMappingException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Singleton class (double-checked locking) responsible for mapping between Student entities and StudentDTO objects.
 * <p>
 * This class prevents instantiation and provides a static method
 * {@link #getInstance()} to obtain the properties.
 * </p>
 */
public class StudentMapper implements CustomRowMapper<StudentDTO, Student> {
    private static volatile StudentMapper instance;

    private StudentMapper() {
        if (instance != null) {
            throw new UnsupportedOperationException("Should not instantiate " + getClass().getSimpleName());
        }
    }

    public static StudentMapper getInstance() {
        if (instance == null) {
            synchronized (StudentMapper.class) {
                if (instance == null) {
                    instance = new StudentMapper();
                }
            }
        }
        return instance;
    }

    @Override
    public Student mapToEntity(StudentDTO studentDTO) throws DataMappingException {
        return entityForm(studentDTO);
    }

    @Override
    public StudentDTO mapToDTO(Student student) throws DataMappingException {
        return dtoForm(student);
    }

    @Override
    public Student mapRow(ResultSet resultSet) throws DataMappingException {
        return mapForm(resultSet);
    }

    public List<Student> mapAllStudents(ResultSet resultSet) throws DataMappingException {
        return mapStudents(resultSet);
    }

    public Student mapStudentById(ResultSet resultSet, Long id) throws DataMappingException {
        Mappers.checkResultSetForNull(resultSet);
        try {
            Student s = new Student();
            s.setId(id);
            s.setUsername(resultSet.getString(TableMapperConstants.STUDENT_USERNAME));
            s.setAverageGradeOverall(resultSet.getDouble(TableMapperConstants.STUDENT_AVERAGE_GRADE_OVERALL));
            s.setGrades(new ArrayList<>());
            return s;
        } catch (SQLException e) {
            String errorMessage = "Failed to mapStudentById object with id: " + id;
            throw new DataMappingException(errorMessage, e);
        }
    }

    private Student entityForm(StudentDTO studentDTO) throws DataMappingException {
        List<Grade> gradeList = new ArrayList<>();
        for (GradeDTO grade : studentDTO.grades()) {
            gradeList.add(GradeMapper.getInstance().mapToEntity(grade));
        }
        return new Student(
              studentDTO.username(),
              gradeList,
              studentDTO.averageGradeOverall()
        );
    }

    private StudentDTO dtoForm(Student student) throws DataMappingException {
        List<GradeDTO> gradesList = new ArrayList<>();
        for (Grade grade: student.getGrades()) {
            gradesList.add(GradeMapper.getInstance().mapToDTO(grade));
        }
        return new StudentDTO(
                student.getUsername(),
                gradesList,
                student.getAverageGradeOverall()
        );
    }

    private Student mapForm(ResultSet resultSet) throws DataMappingException {
        Student student = mapStudent(resultSet);
        student.setGrades(new ArrayList<>());

        Mappers.checkResultSetForNull(resultSet);
        try {
            do {
                if (resultSet.getObject(TableMapperConstants.GRADE_ID) != null) {
                    Grade grade = Mappers.getGradeMapper().mapLight(resultSet);
                    student.getGrades().add(grade);
                }
            } while (resultSet.next());
        } catch (SQLException e) {
            String errorMessage = "Error mapping grades for Student id: " + student.getId();
            throw new DataMappingException(errorMessage, e);
        }

        return student;
    }

    private Student mapStudent(ResultSet resultSet) throws DataMappingException {
        Mappers.checkResultSetForNull(resultSet);
        try {
            return new Student(
                    resultSet.getLong(TableMapperConstants.STUDENT_ID),
                    resultSet.getString(TableMapperConstants.STUDENT_USERNAME),
                    null,
                    resultSet.getDouble(TableMapperConstants.STUDENT_AVERAGE_GRADE_OVERALL)
            );
        } catch (SQLException e) {
            String errorMessage = "Error mapping database result to Student";
            throw new DataMappingException(errorMessage, e);
        }
    }

    private List<Student> mapStudents(ResultSet resultSet) throws DataMappingException {
        Map<Long, Student> studentMap = new HashMap<>();

        Mappers.checkResultSetForNull(resultSet);
        try {
            do {
                long studentId = resultSet.getLong(TableMapperConstants.STUDENT_ID);
                if (studentId != 0) {
                    Student student = studentMap.computeIfAbsent(studentId, id -> {
                        Student newStudent = new Student();
                        try {
                            newStudent.setId(resultSet.getLong(TableMapperConstants.STUDENT_ID));
                            newStudent.setUsername(resultSet.getString(TableMapperConstants.STUDENT_USERNAME));
                            newStudent.setGrades(new ArrayList<>());
                        } catch (SQLException e) {
                            String messageBody = "Error mapping student with id: " + id;
                            QueryLogger.logError(messageBody, e);
                        }
                        return newStudent;
                    });

                    if (resultSet.getObject(TableMapperConstants.GRADE_ID) != null) {
                        Grade grade = Mappers.getGradeMapper().mapLight(resultSet);
                        student.getGrades().add(grade);
                    }
                }
            } while (resultSet.next());

            return new ArrayList<>(studentMap.values());

        } catch (SQLException e) {
            String errorMessage = "Error mapping database result to Student";
            throw new DataMappingException(errorMessage, e);
        }
    }
}
