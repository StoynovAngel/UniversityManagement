package com.angel.uni.management.menu.create;

import com.angel.uni.management.command.CreateCommand;
import com.angel.uni.management.config.QueryLogger;
import com.angel.uni.management.interfaces.Command;
import com.angel.uni.management.interfaces.IMenu;
import com.angel.uni.management.interfaces.Service;
import com.angel.uni.management.menu.Menu;
import com.angel.uni.management.menu.inputs.CreateInput;
import java.util.InputMismatchException;

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
        System.out.println("""
                1. Create specific student
                2. Create specific group
                3. Create specific grade
                4. Create specific subject
                5. Create specific teacher
                6. Go back to search menu
                0. Exit
                """);
    }

    @Override
    public void handleUserChoice() {
        System.out.print("Please enter your choice (0-6): ");
        try {
            int choice = in.nextInt();
            in.nextLine();
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
            case 1 -> createStudent();
            case 2 -> createGroup();
            case 3 -> createGrade();
            case 4 -> createSubject();
            case 5 -> createTeacher();
            case 6 -> getInitialMenu().execute();
            case 0 -> exitApplication();
            default -> System.err.println("Incorrect choice provided " + choice + ". It must be between (0-5)");
        }
    }

    private  <T, U, S> void create(Service<T, U, S> service, S dto) {
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
