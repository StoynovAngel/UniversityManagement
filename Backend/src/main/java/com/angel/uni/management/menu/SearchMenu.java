package com.angel.uni.management.menu;

import com.angel.uni.management.command.ReadCommand;
import com.angel.uni.management.config.QueryLogger;
import com.angel.uni.management.interfaces.Command;
import com.angel.uni.management.interfaces.IMenu;
import com.angel.uni.management.interfaces.Service;

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
        System.out.println("""
                1. Search specific teacher
                2. Search specific student
                3. Search specific group
                4. Search specific grade
                5. Search specific subject
                6. Go back to search menu
                0. Exit
                """);
    }

    protected  <T, U, S> void searchType(Service<T, U, S> service, String name) {
        Command readCommand = new ReadCommand<>(service, name);
        readCommand.execute();
    }

    protected <T, U, S> void searchType(Service<T, U, S> service, long id) {
        Command readCommand = new ReadCommand<>(service, id);
        readCommand.execute();
    }
}
