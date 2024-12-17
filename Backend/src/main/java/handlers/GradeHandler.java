package handlers;

import dto.Grade;

import java.util.List;
import java.util.Scanner;

public class GradeHandler {
    private final Scanner in = new Scanner(System.in);

    public Grade addGradeToUser() {
        System.out.print("Subject: ");
        String subject = in.nextLine();
        System.out.print("Mark: ");
        double mark = in.nextDouble();
        in.nextLine();
        return new Grade(subject, mark);
    }

    public void updateGrade(Grade grade) {
        System.out.println("The previous grade was: " + grade.getMark());
        System.out.print("New grade: ");
        double mark = in.nextDouble();
        grade.setMark(mark);
    }

    public void addGrade(List<Grade> grades) {
        System.out.print("Subject: ");
        String subject = in.nextLine();
        System.out.print("Mark: ");
        double mark = in.nextDouble();

        grades.add(new Grade(subject, mark));
    }
}
