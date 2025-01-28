package entity;

import lombok.*;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "university_group")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String groupName;

    @ManyToMany
    @JoinTable(
            name = "group_student",
            joinColumns = @JoinColumn(name = "group_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id")
    )
    private List<Student> studentsAssignedToGroup;
}
