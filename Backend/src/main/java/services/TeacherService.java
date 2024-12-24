package services;

import dto.Grade;
import dto.Subject;
import dto.Teacher;
import interfaces.ITeacher;
import utils.handlers.TeacherHandler;

public class TeacherService implements ITeacher {
    private final TeacherHandler teacherHandler = new TeacherHandler();

    @Override
    public Subject createSubject() {
        return teacherHandler.createSubject();
    }

    @Override
    public void addGradeToSubject(Subject subject) {
        teacherHandler.addGradeToSubject(subject);
    }
}
