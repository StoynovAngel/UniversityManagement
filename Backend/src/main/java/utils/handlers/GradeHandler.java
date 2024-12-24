package utils.handlers;

import dto.Grade;
import dto.Subject;
import enums.GradeType;
import utils.exceptions.GradeNotFound;
import utils.validation.Validator;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class GradeHandler extends Validator {
    private final Scanner in = new Scanner(System.in);

    public void updateSubjectGrade(Subject subject) {
        String subjectName = subjectNameForm();
        Grade gradeToUpdate = getFilteredGrade(subject, subjectName);
        updateStudentGradeHandler(gradeToUpdate, subjectName);
    }

    public void viewGradesBasedOnDate(Subject subject, LocalDate date) {
        subject.getAllGrades().stream().filter(grade -> grade.getDateOfGrading().equals(date)).forEach(System.out::println);
    }

    public void deleteGrade(Subject subject) {
        String subjectName = subjectNameForm();
        List<Grade> grades = subject.getAllGrades();
        if (!isGradeRemoved(grades, subjectName)) {
            throw new GradeNotFound("No grade found for subject: " + subject);
        }
        System.out.println("The grade with subject: " + subject + " was removed successfully.");
    }

    public GradeType getGradeType() {
        return GradeType.valueOf(in.nextLine());
    }

    private String subjectNameForm() {
        System.out.print("What grade do you want to delete? Enter subject to deleted: ");
        return in.nextLine();
    }

    private Grade getFilteredGrade(Subject subject, String subjectName) {
        List<Grade> grades = subject.getAllGrades();
        return grades.stream()
                .filter(grade -> grade.getSubject().equalsIgnoreCase(subjectName))
                .findFirst()
                .orElseThrow(() -> new GradeNotFound("No grade found for subject: " + subjectName));
    }

    private void updateStudentGradeHandler(Grade gradeToUpdate, String subject) {
        System.out.println("The previous grade for " + subject + " was: " + gradeToUpdate.getMark());
        System.out.print("Enter new grade: ");
        double newMark = in.nextDouble();
        in.nextLine();
        gradeToUpdate.setMark(newMark);
        System.out.println("Grade updated successfully.");
    }
}
