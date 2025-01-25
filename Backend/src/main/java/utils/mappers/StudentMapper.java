package utils.mappers;

import dto.StudentDTO;
import entity.Grade;
import entity.Student;
import interfaces.CustomRowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    private Student entityForm(StudentDTO studentDTO) {
        Student newStudent = new Student();
        newStudent.setUsername(studentDTO.username());
        newStudent.setGrades(studentDTO.grades().stream().map(getGradeMapper()::mapToEntity).toList());
        newStudent.setAverageGradePerSubject(studentDTO.averageGradePerSubject());
        newStudent.setAverageGradeOverall(studentDTO.averageGradePerSubject());
        return newStudent;
    }

    private StudentDTO dtoForm(Student student) {
        return new StudentDTO(
                student.getUsername(),
                student.getGrades().stream().map(getGradeMapper()::mapToDTO).toList(),
                student.getAverageGradePerSubject(),
                student.getAverageGradeOverall()
        );
    }

    private Student mapForm(ResultSet resultSet) throws SQLException {
        Student student = new Student();
        List<Grade> grades = mapGrades(resultSet);

        student.setGrades(grades);
        student.setId(resultSet.getLong("id"));
        student.setUsername(resultSet.getString("username"));
        student.setAverageGradePerSubject(resultSet.getDouble("average_grade_per_subject"));
        student.setAverageGradeOverall(resultSet.getDouble("average_grade_overall"));

        return student;
    }

    private List<Grade> mapGrades(ResultSet resultSet) throws SQLException{
        List<Grade> grades = new ArrayList<>();
        do {
            if (resultSet.getString("grade_id") != null) {
                Grade grade = getGradeMapper().mapRow(resultSet);
                grades.add(grade);
            }
        } while (resultSet.next());
        return grades;
    }
}
