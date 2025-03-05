package com.angel.uni.management.menu;

import com.angel.uni.management.command.UpdateCommand;
import com.angel.uni.management.config.QueryLogger;
import com.angel.uni.management.dto.update.*;
import com.angel.uni.management.interfaces.Command;
import com.angel.uni.management.interfaces.Service;
import com.angel.uni.management.utils.exceptions.IncorrectInputException;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.function.Supplier;

public class UpdateMenu extends Menu implements Command {
    private static volatile UpdateMenu instance;

    public static UpdateMenu getInstance() {
        if (instance == null) {
            synchronized (UpdateMenu.class) {
                if (instance == null) {
                    instance = new UpdateMenu();
                }
            }
        }
        return instance;
    }

    @Override
    public void execute() {
        while (true) {
            displayMenu();
            handleUserChoice();
        }
    }

    @Override
    public void displayMenu() {
        System.out.println("""
                Search:
                1. Update teacher's name
                2. Update subject's description
                3. Update grade's mark
                4. Update student's username
                5. Update group's name
                6. Return to initial menu
                0. Exit
                """);
    }

    @Override
    public void handleUserChoice() {
        System.out.print("Please enter your choice (0-6): ");
        try {
            int choice = in.nextInt();
            handleNavigation(choice);
        } catch (InputMismatchException e) {
            QueryLogger.logError("Input should be an integer", e.getMessage());
            System.err.println("Cannot proceed because the input is not correct. Try again.");
            exitApplication();
        }
    }

    @Override
    public void handleNavigation(int choice) {
        switch (choice) {
            case 1 -> updateTeacherName();
            case 2 -> updateSubjectDescription();
            case 3 -> updateGradeMark();
            case 4 -> updateStudentUsername();
            case 5 -> updateGroupName();
            case 6 -> navigateTo(getInitialMenu());
            case 0 -> exitApplication();
            default -> System.err.println("Incorrect choice provided " + choice + ". It must be between (0-3)");
        }
    }

    private <U> void update(Service<?, U, ?> service, U dto) {
        UpdateCommand updateCommand = new UpdateCommand(service, dto);
        updateCommand.execute();
    }

    private void updateTeacherName() {
        try {
            UpdateTeacherDTO updateTeacherDTO = updateGenericForm(this::inputTeacherForm);
            update(getContainer().getTeacherInstance(), updateTeacherDTO);
        } catch (IncorrectInputException e) {
            System.out.println("Unsuccessful update. Returning to UpdateMenu");
            navigateTo(getUpdateMenu());
        }
    }

    private void updateSubjectDescription() {
        try {
            UpdateSubjectDTO updateSubjectDTO = updateGenericForm(this::inputSubjectForm);
            update(getContainer().getSubjectInstance(), updateSubjectDTO);
        } catch (IncorrectInputException e) {
            System.out.println("Unsuccessful update. Returning to UpdateMenu");
            navigateTo(getUpdateMenu());
        }
    }

    private void updateGradeMark() {
        try {
            UpdateGradeDTO updateGradeDTO = updateGenericForm(this::inputGradeForm);
            update(getContainer().getGradeInstance(), updateGradeDTO);
        } catch (IncorrectInputException e) {
            System.out.println("Unsuccessful update. Returning to UpdateMenu");
            navigateTo(getUpdateMenu());
        }
    }

    private void updateStudentUsername() {
        try {
            UpdateStudentDTO updateStudentDTO = updateGenericForm(this::inputStudentForm);
            update(getContainer().getStudentInstance(), updateStudentDTO);
        } catch (IncorrectInputException e) {
            System.out.println("Unsuccessful update. Returning to UpdateMenu");
            navigateTo(getUpdateMenu());
        }
    }

    private void updateGroupName() {
        try {
            UpdateGroupDTO updateGroupDTO = updateGenericForm(this::inputGroupForm);
            update(getContainer().getGroupInstance(), updateGroupDTO);
        } catch (IncorrectInputException e) {
            System.out.println("Unsuccessful update. Returning to UpdateMenu");
            navigateTo(getUpdateMenu());
        }
    }

    private <T> T updateGenericForm(Supplier<T> formSupplier) throws IncorrectInputException {
        try {
            return formSupplier.get();
        } catch (InputMismatchException e) {
            String errorMessage = "Token does not match the Integer regular expression, or is out of range";
            System.err.println("Incorrect id provided. Please write a integer value next time.");
            QueryLogger.logError(errorMessage, e.getMessage());
            throw new IncorrectInputException(errorMessage);
        } catch (NoSuchElementException e) {
            String errorMessage = "Element being requested does not exist";
            System.err.println("Some of the elements might be missing. Try again.");
            QueryLogger.logError(errorMessage, e.getMessage());
            throw new IncorrectInputException(errorMessage);
        }
    }

    private UpdateTeacherDTO inputTeacherForm() {
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

    private UpdateSubjectDTO inputSubjectForm() {
        in.nextLine();
        System.out.print("What is the name of the subject: ");
        String subjectName = in.nextLine();
        System.out.print("New description: ");
        String subjectDescription = in.nextLine();
        return new UpdateSubjectDTO(subjectDescription, subjectName);
    }

    private UpdateGradeDTO inputGradeForm() {
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

    private UpdateStudentDTO inputStudentForm() {
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

    private UpdateGroupDTO inputGroupForm() {
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
