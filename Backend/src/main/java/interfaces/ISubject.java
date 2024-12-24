package interfaces;

import dto.Student;
import dto.Subject;
import dto.Teacher;

public interface ISubject {
    void addStudent(Subject subject, Student student);
    void deleteStudent(Subject subject, Student student);
    void addTeacher(Subject subject, Teacher teacher);
    void deleteTeacher(Subject subject, Teacher teacher);
}
