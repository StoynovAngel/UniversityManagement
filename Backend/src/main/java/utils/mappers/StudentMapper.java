package utils.mappers;

import dto.StudentDTO;
import entity.Grade;
import entity.Student;
import interfaces.CustomRowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class StudentMapper extends Mappers implements CustomRowMapper<StudentDTO, Student> {

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

    public Student mapLight(ResultSet resultSet, Long id) {
        try {
            Student s = new Student();
            s.setId(id);
            s.setUsername(resultSet.getString("username"));
            s.setAverageGradeOverall(resultSet.getDouble("average_grade_overall"));
            s.setGrades(new ArrayList<>());
            return s;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Student entityForm(StudentDTO studentDTO) {
        Student newStudent = new Student();
        newStudent.setUsername(studentDTO.username());
        newStudent.setGrades(studentDTO.grades().stream().map(getGradeMapper()::mapToEntity).toList());
        newStudent.setAverageGradeOverall(studentDTO.averageGradeOverall());
        return newStudent;
    }

    private StudentDTO dtoForm(Student student) {
        return new StudentDTO(
                student.getUsername(),
                student.getGrades().stream().map(getGradeMapper()::mapToDTO).toList(),
                student.getAverageGradeOverall()
        );
    }

    private Student mapForm(ResultSet resultSet) throws SQLException {

        Student student = new Student();
        student.setId(resultSet.getLong("student_id"));
        student.setUsername(resultSet.getString("username"));
        student.setAverageGradeOverall(resultSet.getDouble("average_grade_overall"));
        student.setGrades(new ArrayList<>());

        do {
            if (resultSet.getObject("grade_id") != null) {
                Grade grade = getGradeMapper().mapLight(resultSet);

                student.getGrades().add(grade);
            }
        } while (resultSet.next());

        return student;
    }
}
