package com.angel.uni.management.entity;

import lombok.*;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "subject")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "hours_per_week")
    private int hoursPerWeek;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "teacher_id", nullable = false)
    private Teacher teacher;

    @ManyToMany
    @JoinTable(
            name = "subject_student",
            joinColumns = @JoinColumn(name = "subject_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id")
    )
    private List<Student> studentsAssignedToSubject;

    public Subject(String name, int hoursPerWeek, String description, Teacher teacher, List<Student> studentsAssignedToSubject) {
        this.name = name;
        this.hoursPerWeek = hoursPerWeek;
        this.description = description;
        this.teacher = teacher;
        this.studentsAssignedToSubject = studentsAssignedToSubject;
    }
}
