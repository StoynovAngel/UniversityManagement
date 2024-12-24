package utils.handlers;

import dto.Grade;
import dto.Group;
import dto.Student;
import utils.validation.Validation;

public class StudentHandler extends Validation {
    public void viewGrades(Student student) {
        student.getSubjects().forEach(subject -> System.out.println(subject.getAllGrades().toString()));
    }

    public void viewSubjects(Student student) {
        student.getSubjects().forEach(subject -> System.out.println(subject.toString()));
    }

    public double calculateOverallGrade(Student student) {
        return student.getSubjects().stream()
                .flatMap(subject -> subject.getAllGrades().stream())
                .mapToDouble(Grade::getMark)
                .average()
                .orElseThrow(() -> new IllegalArgumentException("No grades found for this subject and student"));
    }

    public double calculateGradeForSubject(String subjectName, Student student) {
        return student.getSubjects().stream()
                .filter(subject -> subject.getName().equals(subjectName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Subject not found for the student"))
                .getAllGrades().stream()
                .filter(grade -> grade.getTeacher().getUsername().equals(student.getUsername()))
                .mapToDouble(Grade::getMark)
                .average()
                .orElseThrow(() -> new IllegalArgumentException("No grades found for this subject and student"));

    }

    public void enrollInGroup(Student student, Group group) {
        group.getGroupMembers().add(student);
    }
}
