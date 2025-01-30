package utils.mappers;

import dto.GradeDTO;
import entity.Grade;
import entity.GradeBG;
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
        return new GradeBG(
                resultSet.getString("grade_name"),
                null,
                null,
                GradeType.valueOf(resultSet.getString("grade_type")),
                resultSet.getDouble("grade_mark"),
                resultSet.getDate("date_of_grading")
        );
    }

    private Grade entityForm(GradeDTO dto) {
        return new GradeBG(
                dto.name(),
                getStudentMapper().mapToEntity(dto.student()),
                getTeacherMapper().mapToEntity(dto.teacher()),
                dto.gradeType(),
                dto.mark(),
                dto.dateOfGrading()
        );
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
        return new GradeBG(
                resultSet.getString("name"),
                new Student(resultSet.getLong("student_id"), resultSet.getString("student_username")),
                new Teacher(resultSet.getLong("teacher_id"), resultSet.getString("teacher_name")),
                GradeType.valueOf(resultSet.getString("grade_type")),
                resultSet.getDouble("mark"),
                resultSet.getDate("date_of_grading")
        );
    }
}