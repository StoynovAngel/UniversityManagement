package utils.handlers;

import dto.Grade;
import dto.User;
import utils.exceptions.GradeNotFound;
import utils.validation.Validation;

import java.util.List;
import java.util.Scanner;

public class GradeHandler extends Validation {
    private final Scanner in = new Scanner(System.in);

    public void addGradeToUser(User user) {
        Grade newGrade = addGradeToUserForm();
        user.getGrades().add(newGrade);
    }

    public void updateUserGrade(User user) {
        System.out.print("What grade do you want to update? Enter subject to update: ");
        String subject = in.nextLine();
        Grade gradeToUpdate = getFilteredGrade(user, subject);
        updateUserGradeHandler(gradeToUpdate, subject);
    }

    public void deleteGrade(User user) {
        System.out.print("What grade do you want to delete? Enter subject to deleted: ");
        String subject = in.nextLine();
        List<Grade> grades = user.getGrades();
        if (!isGradeRemoved(grades, subject)) {
            throw new GradeNotFound("No grade found for subject: " + subject);
        }
        System.out.println("The grade with subject: " + subject + " was removed successfully.");
    }

    private Grade addGradeToUserForm() {
        System.out.print("Subject: ");
        String subject = in.nextLine();
        System.out.print("Mark: ");
        double mark = in.nextDouble();
        in.nextLine();
        return new Grade(subject, mark);
    }

    private Grade getFilteredGrade(User user, String subject) {
        List<Grade> grades = user.getGrades();
        return grades.stream()
                .filter(grade -> grade.getSubject().equalsIgnoreCase(subject))
                .findFirst()
                .orElseThrow(() -> new GradeNotFound("No grade found for subject: " + subject));
    }

    private void updateUserGradeHandler(Grade gradeToUpdate, String subject) {
        System.out.println("The previous grade for " + subject + " was: " + gradeToUpdate.getMark());
        System.out.print("Enter new grade: ");
        double newMark = in.nextDouble();
        in.nextLine();
        gradeToUpdate.setMark(newMark);
        System.out.println("Grade updated successfully.");
    }
}
