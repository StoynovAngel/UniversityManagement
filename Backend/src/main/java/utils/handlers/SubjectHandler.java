package utils.handlers;

import dto.Student;
import dto.Subject;
import dto.Teacher;

import java.util.List;

public class SubjectHandler {
    public void addStudent(Subject subject, Student student) {
        addToListIfNotPresent(subject.getStudentsAssignedToSubject(), student, student.getUsername());
    }

    public void deleteStudent(Subject subject, Student student) {
        deleteFromListIfPresent(subject.getStudentsAssignedToSubject(), student, student.getUsername());
    }

    public void addTeacher(Subject subject, Teacher teacher) {
        addToListIfNotPresent(subject.getTeachersAssignedToSubject(), teacher, teacher.getUsername());
    }

    public void deleteTeacher(Subject subject, Teacher teacher) {
        deleteFromListIfPresent(subject.getTeachersAssignedToSubject(), teacher, teacher.getUsername());
    }

    private <T> void addToListIfNotPresent(List<T> list, T item, String message) {
        if (!list.contains(item)) {
            list.add(item);
            System.out.println(message + " added successfully.");
            return;
        }
        System.out.println(message + " is already present.");
    }

    private <T> void deleteFromListIfPresent(List<T> list, T item, String message) {
        if (list.contains(item)) {
            list.remove(item);
            System.out.println(message + " removed successfully.");
            return;
        }
        System.out.println(message + " not found.");
    }
}
