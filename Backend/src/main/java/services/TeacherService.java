package services;

import entity.Teacher;
import interfaces.TeacherRepository;
import utils.mappers.Mappers;

public class TeacherService implements TeacherRepository {

    @Override
    public Teacher getTeacherById(Long id) {
        return QueryManager.getSelectQuery().getTeacherById(id, Mappers.getTeacherMapper());
    }

    @Override
    public void updateTeacherName(String name, Long id) {
        QueryManager.getUpdateQuery().updateTeacherName(name, id);
    }

    @Override
    public void insertTeacher(String name) {
        QueryManager.getInsertQuery().insertTeacher(name);
    }
}
