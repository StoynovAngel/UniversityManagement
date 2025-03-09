package com.angel.uni.management.menu.console.update;

import com.angel.uni.management.command.UpdateCommand;
import com.angel.uni.management.config.QueryLogger;
import com.angel.uni.management.dto.update.*;
import com.angel.uni.management.enums.ClassOptions;
import com.angel.uni.management.interfaces.Command;
import com.angel.uni.management.interfaces.Service;
import com.angel.uni.management.menu.console.Menu;
import com.angel.uni.management.menu.console.inputs.UpdateForm;
import com.angel.uni.management.utils.exceptions.IncorrectInputException;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;

public class UpdateMenu extends Menu implements Command {

    private static volatile UpdateMenu instance;
    private final UpdateForm updateForm = new UpdateForm();

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
        System.out.println("Update menu: ");
        ClassOptions.displayAllOptions();
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
        switch (ClassOptions.getByOptionNumber(choice)) {
            case RETURN_TO_INITIAL_MENU -> navigateTo(getInitialMenu());
            case TEACHER -> updateTeacherName();
            case SUBJECT -> updateSubjectDescription();
            case GRADE -> updateGradeMark();
            case STUDENT -> updateStudentUsername();
            case GROUP -> updateGroupName();
            case EXIT -> exitApplication();
            default -> System.err.println("Incorrect choice provided " + choice + ". It must be between (0-3)");
        }
    }

    private <U> void update(Service<?, U, ?> service, U dto) {
        Command updateCommand = new UpdateCommand<>(service, dto);
        updateCommand.execute();
    }

    private void updateTeacherName() {
        try {
            UpdateTeacherDTO updateTeacherDTO = updateGenericForm(updateForm.inputTeacherForm());
            update(getContainer().getTeacherInstance(), updateTeacherDTO);
        } catch (IncorrectInputException e) {
            System.out.println("Unsuccessful update. Returning to UpdateMenu");
            navigateTo(getUpdateMenu());
        }
    }

    private void updateSubjectDescription() {
        try {
            UpdateSubjectDTO updateSubjectDTO = updateGenericForm(updateForm.inputSubjectForm());
            update(getContainer().getSubjectInstance(), updateSubjectDTO);
        } catch (IncorrectInputException e) {
            System.out.println("Unsuccessful update. Returning to UpdateMenu");
            navigateTo(getUpdateMenu());
        }
    }

    private void updateGradeMark() {
        try {
            UpdateGradeDTO updateGradeDTO = updateGenericForm(updateForm.inputGradeForm());
            update(getContainer().getGradeInstance(), updateGradeDTO);
        } catch (IncorrectInputException e) {
            System.out.println("Unsuccessful update. Returning to UpdateMenu");
            navigateTo(getUpdateMenu());
        }
    }

    private void updateStudentUsername() {
        try {
            UpdateStudentDTO updateStudentDTO = updateGenericForm(updateForm.inputStudentForm());
            update(getContainer().getStudentInstance(), updateStudentDTO);
        } catch (IncorrectInputException e) {
            System.out.println("Unsuccessful update. Returning to UpdateMenu");
            navigateTo(getUpdateMenu());
        }
    }

    private void updateGroupName() {
        try {
            UpdateGroupDTO updateGroupDTO = updateGenericForm(updateForm.inputGroupForm());
            update(getContainer().getGroupInstance(), updateGroupDTO);
        } catch (IncorrectInputException e) {
            System.out.println("Unsuccessful update. Returning to UpdateMenu");
            navigateTo(getUpdateMenu());
        }
    }

    private <T> T updateGenericForm(T dto) throws IncorrectInputException {
        try {
            return dto;
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
}
