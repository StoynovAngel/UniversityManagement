package com.angel.uni.management.menu.console.inputs;

import com.angel.uni.management.dto.update.*;

import java.util.InputMismatchException;

public class UpdateForm extends InputForms {

    @Override
    public UpdateTeacherDTO inputTeacherForm() {
        System.out.print("Teacher id: ");
        if (!in.hasNextLong()) {
            in.nextLine();
            throw new InputMismatchException("Invalid input for teacher id. Expected a long.");
        }
        long id = in.nextLong();
        in.nextLine();
        System.out.print("New name: ");
        String newTeacherName = in.nextLine();
        return new UpdateTeacherDTO(newTeacherName, id);
    }

    @Override
    public UpdateSubjectDTO inputSubjectForm() {
        in.nextLine();
        System.out.print("What is the name of the subject: ");
        String subjectName = in.nextLine();
        System.out.print("New description: ");
        String subjectDescription = in.nextLine();
        return new UpdateSubjectDTO(subjectDescription, subjectName);
    }

    @Override
    public UpdateGradeDTO inputGradeForm() {
        System.out.print("Grade id: ");
        if (!in.hasNextLong()) {
            in.nextLine();
            throw new InputMismatchException("Invalid input for grade id. Expected a long.");
        }
        long id = in.nextLong();

        System.out.print("New grade mark: ");
        if (!in.hasNextDouble()) {
            in.nextLine();
            throw new InputMismatchException("Invalid input for grade mark. Expected a double.");
        }
        double mark = in.nextDouble();
        return new UpdateGradeDTO(mark, id);
    }

    @Override
    public UpdateStudentDTO inputStudentForm() {
        System.out.print("What is the id of the student: ");
        if (!in.hasNextLong()) {
            in.nextLine();
            throw new InputMismatchException("Invalid input for student id. Expected a long.");
        }
        long id = in.nextLong();
        in.nextLine();
        System.out.print("New username: ");
        String studentUsername = in.nextLine();
        return new UpdateStudentDTO(studentUsername, id);
    }

    @Override
    public UpdateGroupDTO inputGroupForm() {
        System.out.print("What is the id of the group: ");
        if (!in.hasNextLong()) {
            in.nextLine();
            throw new InputMismatchException("Invalid input for group id. Expected a long.");
        }
        long id = in.nextLong();
        in.nextLine();
        System.out.print("New name: ");
        String name = in.nextLine();
        return new UpdateGroupDTO(name, id);
    }
}
