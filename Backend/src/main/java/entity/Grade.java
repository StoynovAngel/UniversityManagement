package entity;

import enums.GradeType;
import lombok.*;
import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public abstract class Grade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @ManyToOne
    @JoinColumn(name = "teacher_id", nullable = false)
    private Teacher teacher;

    @Enumerated(EnumType.STRING)
    @Column(name = "grade_type", nullable = false)
    private GradeType gradeType;

    @Column(name = "mark", nullable = false)
    private double mark;

    @Temporal(TemporalType.DATE)
    @Column(name = "date_of_grading", nullable = false)
    private Date dateOfGrading;

    public Grade(String name, Student student, Teacher teacher, GradeType gradeType, double mark, Date dateOfGrading) {
        this.name = name;
        this.student = student;
        this.teacher = teacher;
        this.gradeType = gradeType;
        setDateOfGrading(dateOfGrading);
        setMark(mark);
    }

    protected abstract void validateMark(double mark);
    public void setMark(double mark) {
        validateMark(mark);
        this.mark = mark;
    }
}