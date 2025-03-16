package com.angel.uni.management.menu.console.create;

import com.angel.uni.management.command.CreateCommand;
import com.angel.uni.management.config.QueryLogger;
import com.angel.uni.management.enums.ClassOptions;
import com.angel.uni.management.interfaces.Command;
import com.angel.uni.management.interfaces.IMenu;
import com.angel.uni.management.interfaces.Service;
import com.angel.uni.management.menu.console.Menu;
import com.angel.uni.management.menu.console.inputs.CreateInput;

public class CreateMenu extends Menu implements IMenu, Command {
    private static volatile CreateMenu instance;
    private final CreateInput createInput = new CreateInput();

    public static CreateMenu getInstance() {
        if (instance == null) {
            synchronized (CreateMenu.class) {
                if (instance == null) {
                    instance = new CreateMenu();
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
        ClassOptions.displayAllOptions();
    }

    @Override
    public void handleUserChoice() {
        System.out.print("Please enter your choice (0-6): ");

        if (!in.hasNextInt()) {
            System.out.flush();
            System.out.println("Input is not a valid integer. Try again.");
            QueryLogger.logError("Non-integer input provided in " + getClass().getSimpleName());
            in.nextLine();
            navigateTo(getCreateMenu());
            return;
        }
        int choice = in.nextInt();
        in.nextLine();
        handleNavigation(choice);
    }

    @Override
    public void handleNavigation(int choice) {
        ClassOptions option = ClassOptions.getByOptionNumber(choice);
        if (option == null) {
            System.out.println("Choice out of range. Please select a valid option.");
            QueryLogger.logError("Invalid enum option number in " + getClass().getSimpleName());
            return;
        }
        switch (option) {
            case STUDENT -> createStudent();
            case GROUP -> createGroup();
            case GRADE -> createGrade();
            case SUBJECT -> createSubject();
            case TEACHER -> createTeacher();
            case RETURN_TO_INITIAL_MENU -> getInitialMenu().execute();
            case EXIT -> exitApplication();
        }
    }

    private <T, U, S> void create(final Service<T, U, S> service, S dto) {
        Command createCommand = new CreateCommand<>(service, dto);
        createCommand.execute();
    }

    private void createStudent() {
        create(getContainer().getStudentInstance(), createInput.inputStudentForm());
    }

    private void createGroup() {
        create(getContainer().getGroupInstance(), createInput.inputGroupForm());
    }

    private void createSubject() {
        create(getContainer().getSubjectInstance(), createInput.inputSubjectForm());
    }

    private void createGrade() {
        create(getContainer().getGradeInstance(), createInput.inputGradeForm());
    }

    private void createTeacher() {
        create(getContainer().getTeacherInstance(), createInput.inputTeacherForm());
    }
}
