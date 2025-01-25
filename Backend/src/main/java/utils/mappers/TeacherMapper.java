package utils.mappers;

import dto.TeacherDTO;
import entity.Teacher;
import interfaces.CustomRowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
public class TeacherMapper implements CustomRowMapper<TeacherDTO, Teacher> {

    @Override
    public Teacher mapToEntity(TeacherDTO teacherDTO) {
        return entityForm(teacherDTO);
    }

    @Override
    public TeacherDTO mapToDTO(Teacher teacher) {
        return dtoForm(teacher);
    }

    @Override
    public Teacher mapRow(ResultSet resultSet) throws SQLException {
        return mapForm(resultSet);
    }

    private Teacher entityForm(TeacherDTO teacherDTO) {
        Teacher newTeacher = new Teacher();
        newTeacher.setName(teacherDTO.name());
        return newTeacher;
    }

    private TeacherDTO dtoForm(Teacher teacher) {
        return new TeacherDTO(
                teacher.getName()
        );
    }

    private Teacher mapForm(ResultSet resultSet) throws SQLException {
        Teacher teacher = new Teacher();
        teacher.setId(resultSet.getLong("teacher_id"));
        teacher.setName(resultSet.getString("teacher_name"));
        return teacher;
    }
}