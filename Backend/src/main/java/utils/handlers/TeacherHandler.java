package utils.handlers;

import dto.Grade;
import dto.Subject;
import dto.Teacher;
import services.GradeService;

import java.util.Scanner;

public class TeacherHandler {
    private final Scanner in = new Scanner(System.in);
    private final GradeService gradeService = new GradeService(new GradeHandler());

    public void addGradeToSubject(Subject subject) {
        Grade newGrade = addGradeToSubjectForm(subject);
        subject.getAllGrades().add(newGrade);
    }

    public Subject createSubject() {
        return createSubjectForm();
    }

    public Teacher getTeacherFromSubject(Subject subject) {
        String username = teacherForm();
        return getTeacherFromAssignedList(subject, username);
    }

    private Subject createSubjectForm() {
        System.out.print("What is the name of the subject you would like to create: ");
        String name = in.nextLine();

        System.out.print("How many hours does the subject occur in a week: ");
        int hours = in.nextInt();
        in.nextLine();

        System.out.print("What is the description of the subject you would like to create: ");
        String description = in.nextLine();

        return new Subject(name, hours, description);
    }

    private String teacherForm() {
        System.out.println("What is your name(teacher would be changed): ");
        return in.nextLine();
    }

    private Teacher getTeacherFromAssignedList(Subject subject, String username) {
        return subject.getTeachersAssignedToSubject().stream()
                .filter(t -> t.getUsername().equals(username))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Teacher not found in subject."));
    }

    private Grade addGradeToSubjectForm(Subject subject) {
        Teacher teacher = getTeacherFromSubject(subject);
        System.out.println("Type of grade you want to add(FINAL_EXAM,MID_EXAM,ORAL,PROJECT_EXAM): ");

        System.out.print("Mark: ");
        double mark = in.nextDouble();
        in.nextLine();

        return new Grade(subject.getName(), mark, teacher, subject.getDescription(), gradeService.getGradeType());
    }

}
