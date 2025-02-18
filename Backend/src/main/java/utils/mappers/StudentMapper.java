package utils.mappers;

import config.QueryLogger;
import dto.StudentDTO;
import entity.Grade;
import entity.Student;
import interfaces.CustomRowMapper;
import utils.exceptions.DataMappingException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Singleton class (double-checked locking) responsible for mapping between Student entities and StudentDTO objects.
 *  <p>
 *  This class prevents instantiation and provides a static method
 *  {@link #getUniqueInstance()} to obtain the properties.
 *  </p>
 */
public class StudentMapper implements CustomRowMapper<StudentDTO, Student> {
    private static volatile StudentMapper uniqueInstance;

    private StudentMapper() {
        if (uniqueInstance != null) {
            throw new UnsupportedOperationException("Should not instantiate " + getClass().getSimpleName());
        }
    }

    public static StudentMapper getUniqueInstance() {
        if (uniqueInstance == null) {
            synchronized (StudentMapper.class) {
                if (uniqueInstance == null) {
                    uniqueInstance = new StudentMapper();
                }
            }
        }
        return uniqueInstance;
    }

    @Override
    public Student mapToEntity(StudentDTO studentDTO) {
        return entityForm(studentDTO);
    }

    @Override
    public StudentDTO mapToDTO(Student student) {
        return dtoForm(student);
    }

    @Override
    public Student mapRow(ResultSet resultSet) {
        return mapForm(resultSet);
    }

    public List<Student> mapAllStudents(ResultSet resultSet) {
        return mapStudents(resultSet);
    }

    public Student mapStudentById(ResultSet resultSet, Long id) {
        Mappers.checkResultSetForNull(resultSet);
        try {
            Student s = new Student();
            s.setId(id);
            s.setUsername(resultSet.getString(TableMapperConstants.STUDENT_USERNAME));
            s.setAverageGradeOverall(resultSet.getDouble(TableMapperConstants.STUDENT_AVERAGE_GRADE_OVERALL));
            s.setGrades(new ArrayList<>());
            return s;
        } catch (SQLException e) {
            QueryLogger.logError("Failed to mapStudentById object with ID: " + id, e);
            throw new DataMappingException("Error by mapStudentById object with ID: " + id, e);
        }
    }

    private Student entityForm(StudentDTO studentDTO) {
        Student newStudent = new Student();
        newStudent.setUsername(studentDTO.username());
        newStudent.setGrades(studentDTO.grades().stream().map(Mappers.getGradeMapper()::mapToEntity).toList());
        newStudent.setAverageGradeOverall(studentDTO.averageGradeOverall());
        return newStudent;
    }

    private StudentDTO dtoForm(Student student) {
        return new StudentDTO(
                student.getUsername(),
                student.getGrades().stream().map(Mappers.getGradeMapper()::mapToDTO).toList(),
                student.getAverageGradeOverall()
        );
    }

    private Student mapForm(ResultSet resultSet) {
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
            QueryLogger.logError("Failed to map grades for Student ID: " + student.getId(), e);
            throw new DataMappingException("Error mapping grades for Student ID: " + student.getId(), e);
        }

        return student;
    }

    private Student mapStudent(ResultSet resultSet) {
        Mappers.checkResultSetForNull(resultSet);
        try {
            return new Student(
                resultSet.getLong(TableMapperConstants.STUDENT_ID),
                resultSet.getString(TableMapperConstants.STUDENT_USERNAME),
                null,
                resultSet.getDouble(TableMapperConstants.STUDENT_AVERAGE_GRADE_OVERALL)
            );
        } catch (SQLException e) {
            QueryLogger.logError("Failed to map Student object from ResultSet", e);
            throw new DataMappingException("Error mapping database result to Student", e);
        }
    }

    private List<Student> mapStudents(ResultSet resultSet)  {
        Map<Long, Student> studentMap = new HashMap<>();

        Mappers.checkResultSetForNull(resultSet);
        try{
            do {
                Long studentId = resultSet.getLong(TableMapperConstants.STUDENT_ID);
                if (studentId != 0) {
                    Student student = studentMap.computeIfAbsent(studentId, id -> {
                        Student newStudent = new Student();
                        try {
                            newStudent.setId(resultSet.getLong(TableMapperConstants.STUDENT_ID));
                            newStudent.setUsername(resultSet.getString(TableMapperConstants.STUDENT_USERNAME));
                            newStudent.setGrades(new ArrayList<>());
                        } catch (SQLException e) {
                            QueryLogger.logError("Error mapping student within mapStudents()", e);
                            throw new DataMappingException("Error mapping student with ID: " + id, e);
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
            QueryLogger.logError("Failed to map ResultSet to Student object", e);
            throw new DataMappingException("Error mapping database result to Student", e);
        }
    }
}
