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

public class StudentMapper implements CustomRowMapper<StudentDTO, Student> {
    private static StudentMapper uniqueInstance;

    private StudentMapper() {
    }

    public static StudentMapper getUniqueInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new StudentMapper();
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
    public Student mapRow(ResultSet resultSet) throws SQLException {
        return mapForm(resultSet);
    }

    public List<Student> mapAllStudents(ResultSet resultSet) {
        return mapStudents(resultSet);
    }

    public Student mapLight(ResultSet resultSet, Long id) {
        try {
            Student s = new Student();
            s.setId(id);
            s.setUsername(resultSet.getString(TableMapperConstants.STUDENT_USERNAME));
            s.setAverageGradeOverall(resultSet.getDouble(TableMapperConstants.STUDENT_AVERAGE_GRADE_OVERALL));
            s.setGrades(new ArrayList<>());
            return s;
        } catch (SQLException e) {
            throw new RuntimeException(e);
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

    private Student mapForm(ResultSet resultSet) throws SQLException {
        Student student = new Student();
        student.setId(resultSet.getLong(TableMapperConstants.STUDENT_ID));
        student.setUsername(resultSet.getString(TableMapperConstants.STUDENT_USERNAME));
        student.setAverageGradeOverall(resultSet.getDouble(TableMapperConstants.STUDENT_AVERAGE_GRADE_OVERALL));
        student.setGrades(new ArrayList<>());

        do {
            if (resultSet.getObject(TableMapperConstants.GRADE_ID) != null) {
                Grade grade = Mappers.getGradeMapper().mapLight(resultSet);

                student.getGrades().add(grade);
            }
        } while (resultSet.next());

        return student;
    }

    private List<Student> mapStudents(ResultSet resultSet)  {
        Map<Long, Student> studentMap = new HashMap<>();

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
                            throw new RuntimeException(e);
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
