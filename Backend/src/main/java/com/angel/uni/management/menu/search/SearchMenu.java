package com.angel.uni.management.menu.search;

import com.angel.uni.management.command.ReadCommand;
import com.angel.uni.management.config.QueryLogger;
import com.angel.uni.management.enums.MenuOptions;
import com.angel.uni.management.interfaces.Command;
import com.angel.uni.management.interfaces.IMenu;
import com.angel.uni.management.interfaces.Service;
import com.angel.uni.management.menu.InitialMenu;
import com.angel.uni.management.menu.Menu;

import java.util.InputMismatchException;

public class SearchMenu extends Menu implements IMenu {
    private static volatile SearchMenu instance;

    public static SearchMenu getInstance() {
        if (instance == null) {
            synchronized (SearchMenu.class) {
                if (instance == null) {
                    instance = new SearchMenu();
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
                1. Search by id
                2. Search by name
                3. Go back to initial menu
                0. Exit
                """);
    }

    @Override
    public void handleUserChoice() {
        System.out.print("Please enter your choice (0-3): ");
        try {
            int choice = in.nextInt();
            handleNavigation(choice);
        } catch (InputMismatchException e) {
            QueryLogger.logError("Input should be an integer" , e.getMessage());
            System.err.println("Cannot proceed because the input is not correct. Try again.");
            exitApplication();
        }
    }

    @Override
    public void handleNavigation(int choice) {
        switch (choice) {
            case 1 -> getSearchByIdMenu().execute();
            case 2 -> getSearchByNameMenu().execute();
            case 3 -> getInitialMenu().execute();
            case 0 -> exitApplication();
            default -> System.err.println("Incorrect choice provided " + choice + ". It must be between (0-3)");
        }
    }

    public void displaySpecifics() {
        MenuOptions.displaySpecifics();
    }

    protected <T, P> void searchType(Service<T, ?, ?> service, P param) {
        Command readCommand = new ReadCommand<>(service, param);
        readCommand.execute();
    }

    protected <T, U, S> void searchType(Service<T, U, S> service, long id) {
        Command readCommand = new ReadCommand<>(service, id);
        readCommand.execute();
    }

    protected <T> void getSpecificAttribute(int choice, T param) {
        switch (MenuOptions.getByOptionNumber(choice)) {
            case RETURN_TO_INITIAL_MENU -> InitialMenu.getInstance().execute();
            case TEACHER -> searchType(getContainer().getTeacherInstance(), param);
            case STUDENT -> searchType(getContainer().getStudentInstance(), param);
            case GROUP -> searchType(getContainer().getGroupInstance(), param);
            case GRADE -> searchType(getContainer().getGradeInstance(), param);
            case SUBJECT -> searchType(getContainer().getSubjectInstance(), param);
            default -> System.err.println("Invalid choice.");
        }
    }
}
