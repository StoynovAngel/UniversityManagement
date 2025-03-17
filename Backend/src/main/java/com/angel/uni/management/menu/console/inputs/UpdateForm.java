package com.angel.uni.management.menu.console.inputs;

import com.angel.uni.management.dto.update.*;
import com.angel.uni.management.interfaces.SimpleDTO;
import com.angel.uni.management.utils.container.DependencyContainer;

public class UpdateForm extends InputForms<SimpleDTO> {
    private final DependencyContainer dependencyContainer = DependencyContainer.getDependencyContainer();

    @Override
    public UpdateTeacherDTO inputTeacherForm() {
        long id = validateIdInput();
        in.nextLine();
        System.out.print("New name: ");
        String newTeacherName = validateUserProperty();
        return new UpdateTeacherDTO(newTeacherName, id);
    }

    @Override
    public UpdateSubjectDTO inputSubjectForm() {
        System.out.print("What is the name of the subject: ");
        String subjectName = searchForSubject();
        System.out.print("New description: ");
        String subjectDescription = validateStringInput();
        return new UpdateSubjectDTO(subjectDescription, subjectName);
    }

    @Override
    public UpdateGradeDTO inputGradeForm() {
        long id = validateIdInput();
        in.nextLine();
        double mark = validateDoubleInput();
        return new UpdateGradeDTO(mark, id);
    }

    @Override
    public UpdateStudentDTO inputStudentForm() {
        long id = validateIdInput();
        in.nextLine();
        System.out.print("New student name: ");
        String studentUsername = validateUserProperty();
        return new UpdateStudentDTO(studentUsername, id);
    }

    @Override
    public UpdateGroupDTO inputGroupForm() {
        long id = validateIdInput();
        in.nextLine();
        System.out.print("New group name: ");
        String name = validateStringInput();
        return new UpdateGroupDTO(name, id);
    }

    private String searchForSubject() {
        String subjectName;
        while (true) {
            System.out.print("Please enter subject's name. This subject MUST exist: ");
            subjectName = in.nextLine();
            if (isStringEmpty(subjectName)) {
                System.out.println("Subject's name cannot be empty. Try again.");
                continue;
            }
            if (dependencyContainer.getSubjectInstance().read(subjectName).isEmpty()) {
                System.out.println("No such subject found: " + subjectName + ". Try again.");
                continue;
            }
            break;
        }
        return subjectName;
    }

    private long validateIdInput() {
        while (true) {
            System.out.print("Id: ");
            if (!in.hasNextLong()) {
                System.out.println("Input must be a long. Please try again.");
                in.nextLine();
                continue;
            }
            return in.nextLong();
        }
    }

    private double validateDoubleInput() {
        while (true) {
            if (!in.hasNextDouble()) {
                System.out.println("Input must be a double. Please try again.");
                in.nextLine();
                continue;
            }
            return in.nextDouble();
        }
    }

    private String validateUserProperty() {
        while (true) {
            String str = in.nextLine().trim();
            if (isStringEmptyOrContainsNumeric(str)) {
                System.out.print("String must not be null or empty. Retry: ");
                continue;
            }
            return str;
        }
    }

    private String validateStringInput() {
        while (true) {
            String string = in.nextLine().trim();
            if (isStringEmpty(string)) {
                System.out.print("String must not be null or empty. Retry: ");
                continue;
            }
            return string;
        }
    }

    private boolean isStringEmptyOrContainsNumeric(String input) {
        if (isStringEmpty(input)) {
            return true;
        }
        return input.matches(".*\\d.*");
    }

    private boolean isStringEmpty(String input) {
        return input == null || input.isEmpty();
    }
}
