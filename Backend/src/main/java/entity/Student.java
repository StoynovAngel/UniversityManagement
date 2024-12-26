package entity;

import java.util.ArrayList;
import java.util.List;

public class Student extends User{
    private final List<Subject> subjects;
    private double averageGradePerSubject;
    private double averageGradeOverall;

    public Student(Long id, String username, List<Subject> subjects, double averageGradePerSubject, double averageGradeOverall) {
        super(id, username);
        this.subjects = subjects;
        this.averageGradePerSubject = averageGradePerSubject;
        this.averageGradeOverall = averageGradeOverall;
    }

    public Student(Long id, String username) {
        super(id, username);
        this.subjects = new ArrayList<>();
        this.averageGradeOverall = 0.0;
        this.averageGradePerSubject = 0.0;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + getId() +
                ", username='" + getUsername() + '\'' +
                ", subjects=" + subjects +
                ", averageGradePerSubject=" + averageGradePerSubject +
                ", averageGradeOverall=" + averageGradeOverall +
                '}';
    }

    public double getAverageGradePerSubject() {
        return averageGradePerSubject;
    }

    public double getAverageGradeOverall() {
        return averageGradeOverall;
    }
}
