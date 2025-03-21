package com.angel.uni.management.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "student")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "username", nullable = false)
    private String username;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Grade> grades;

    @Column(name = "average_grade_overall")
    private double averageGradeOverall;

    public Student(Long id, String username) {
        this.id = id;
        this.username = username;
    }

    public Student(String username, List<Grade> grades, double averageGradeOverall) {
        this.username = username;
        this.grades = grades;
        this.averageGradeOverall = averageGradeOverall;
    }
}
