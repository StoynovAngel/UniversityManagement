package utils.mappers;

import dto.GradeDTO;
import entity.Grade;
import entity.Student;
import entity.Teacher;
import enums.GradeType;
import interfaces.CustomRowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GradeMapper extends Mappers implements CustomRowMapper<GradeDTO, Grade> {

    @Override
    public Grade mapToEntity(GradeDTO dto) {
        return entityForm(dto);
    }

    @Override
    public GradeDTO mapToDTO(Grade entity) {
        return dtoForm(entity);
    }

    @Override
    public Grade mapRow(ResultSet resultSet) throws SQLException {
        return mapForm(resultSet);
    }

    public Grade mapLight(ResultSet resultSet) throws SQLException {
        Grade grade = new Grade();
        grade.setId(resultSet.getLong("grade_id"));
        grade.setName(resultSet.getString("grade_name"));
        grade.setMark(resultSet.getDouble("grade_mark"));
        grade.setGradeType(GradeType.valueOf(resultSet.getString("grade_type")));
        grade.setDateOfGrading(resultSet.getDate("date_of_grading"));
        return grade;
    }

    private Grade entityForm(GradeDTO dto) {
        Grade grade = new Grade();
        grade.setName(dto.name());
        grade.setMark(dto.mark());
        grade.setStudent(getStudentMapper().mapToEntity(dto.student()));
        grade.setTeacher(getTeacherMapper().mapToEntity(dto.teacher()));
        grade.setDateOfGrading(dto.dateOfGrading());
        grade.setGradeType(dto.gradeType());
        return grade;
    }

    private GradeDTO dtoForm(Grade entity) {
        return new GradeDTO(
                entity.getName(),
                getStudentMapper().mapToDTO(entity.getStudent()),
                getTeacherMapper().mapToDTO(entity.getTeacher()),
                entity.getGradeType(),
                entity.getMark(),
                entity.getDateOfGrading()
        );
    }

    private Grade mapForm(ResultSet resultSet) throws SQLException {
        Grade grade = new Grade();
        grade.setId(resultSet.getLong("grade_id"));
        grade.setName(resultSet.getString("name"));
        grade.setMark(resultSet.getDouble("mark"));
        grade.setGradeType(GradeType.valueOf(resultSet.getString("grade_type")));
        grade.setDateOfGrading(resultSet.getDate("date_of_grading"));

        Student student = new Student();
        student.setId(resultSet.getLong("student_id"));
        student.setUsername(resultSet.getString("student_username"));
        grade.setStudent(student);

        Teacher teacher = new Teacher();
        teacher.setId(resultSet.getLong("teacher_id"));
        teacher.setName(resultSet.getString("teacher_name"));
        grade.setTeacher(teacher);

        return grade;
    }

}