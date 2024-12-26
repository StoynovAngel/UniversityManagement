package entity;

import java.util.ArrayList;
import java.util.List;

public class Subject {
    private final Long id;
    private String name;
    private int hoursPerWeek;
    private String description;
    private final List<Teacher> teachersAssignedToSubject;
    private final List<Student> studentsAssignedToSubject;
    private final List<Grade> allGrades;

    public Subject(Long id, String name, int hoursPerWeek, String description,
                   List<Teacher> teachersAssignedToSubject,
                   List<Student> studentsAssignedToSubject, List<Grade> allGrades)
    {

        validateHoursPerWeek(hoursPerWeek);
        this.id = id;
        this.allGrades = allGrades;
        this.name = name;
        this.hoursPerWeek = hoursPerWeek;
        this.description = description;
        this.teachersAssignedToSubject = teachersAssignedToSubject;
        this.studentsAssignedToSubject = studentsAssignedToSubject;
    }

    public Subject(Long id, String name, int hoursPerWeek, String description) {
        this(id, name, hoursPerWeek, description, new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
    }

    public String getName() {
        return name;
    }

    public int getHoursPerWeek() {
        return hoursPerWeek;
    }

    public String getDescription() {
        return description;
    }

    public List<Student> getStudentsAssignedToSubject() {
        return studentsAssignedToSubject;
    }

    public List<Grade> getAllGrades() {
        return allGrades;
    }

    public List<Teacher> getTeachersAssignedToSubject() {
        return teachersAssignedToSubject;
    }

    private void validateHoursPerWeek(int hoursPerWeek) {
        if (hoursPerWeek < 0) {
            throw new IllegalArgumentException("Hours per week must be a positive number");
        }
    }
}
