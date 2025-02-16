package services;

import entity.Teacher;
import interfaces.TeacherRepository;
import utils.mappers.Mappers;

public class TeacherService extends BasicService implements TeacherRepository {

    @Override
    public Teacher getTeacherById(Long id) {
        return selectQuery.getTeacherById(id, Mappers.getTeacherMapper());
    }

    @Override
    public void updateTeacherName(String name, Long id) {
        updateQuery.updateTeacherName(name, id);
    }

    @Override
    public void insertTeacher(String name) {
        insertQuery.insertTeacher(name);
    }
}
