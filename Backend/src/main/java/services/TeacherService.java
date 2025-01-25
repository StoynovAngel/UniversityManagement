package services;

import entity.Teacher;
import interfaces.TeacherRepository;
import utils.mappers.TeacherMapper;

public class TeacherService extends BasicService implements TeacherRepository {
    private final TeacherMapper teacherMapper = new TeacherMapper();
    @Override
    public Teacher getTeacherById(Long id) {
        return selectQuery.getTeacherById(id, teacherMapper);
    }

}
