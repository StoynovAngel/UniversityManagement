package services;

import dto.Student;
import dto.Subject;
import dto.Teacher;
import interfaces.ISubject;
import utils.handlers.SubjectHandler;

public class SubjectService implements ISubject {
    private SubjectHandler subjectHandler = new SubjectHandler();
    @Override
    public void addStudent(Subject subject, Student student) {
        subjectHandler.addStudent(subject, student);
    }

    @Override
    public void deleteStudent(Subject subject, Student student) {
        subjectHandler.deleteStudent(subject, student);
    }

    @Override
    public void addTeacher(Subject subject, Teacher teacher) {
        subjectHandler.addTeacher(subject, teacher);
    }

    @Override
    public void deleteTeacher(Subject subject, Teacher teacher) {
        subjectHandler.deleteTeacher(subject, teacher);
    }
}
