package com.angel.uni.management.menu.console.search;

import com.angel.uni.management.command.ReadCommand;
import com.angel.uni.management.config.QueryLogger;
import com.angel.uni.management.enums.ClassOptions;
import com.angel.uni.management.enums.SearchOptions;
import com.angel.uni.management.interfaces.Command;
import com.angel.uni.management.interfaces.IMenu;
import com.angel.uni.management.interfaces.Service;
import com.angel.uni.management.menu.console.InitialMenu;
import com.angel.uni.management.menu.console.Menu;
import com.angel.uni.management.utils.exceptions.IncorrectInputException;

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
        SearchOptions.displayAllOptions();
    }

    @Override
    public void handleUserChoice() {
        System.out.print("Please enter your choice (0-3): ");
        try {
            int choice = in.nextInt();
            System.out.println(choice);
            handleNavigation(choice);
        } catch (InputMismatchException e) {
            QueryLogger.logError("Input should be an integer" , e.getMessage());
            System.err.println("Cannot proceed because the input is not correct. Try again.");
            exitApplication();
        }
    }

    @Override
    public void handleNavigation(int choice) {
        try{
            switch (SearchOptions.getByOptionNumber(choice)) {
                case SEARCH_BY_ID -> getSearchByIdMenu().execute();
                case SEARCH_BY_NAME -> getSearchByNameMenu().execute();
                case RETURN_TO_INITIAL_MENU -> getInitialMenu().execute();
                case EXIT -> exitApplication();
                default -> System.err.println("Incorrect choice provided " + choice + ". It must be between (0-3)");
            }
        } catch (IncorrectInputException e) {
            System.err.println("Returning to initial menu");
            getInitialMenu().execute();
        }
    }

    public void displaySpecifics() {
        ClassOptions.displayAllOptions();
    }

    private <T, P> void searchType(Service<T, ?, ?> service, P param) {
        Command readCommand = new ReadCommand<>(service, param);
        readCommand.execute();
    }

    protected <T> void getSpecificAttribute(int choice, T param) {
        try {
            switch (ClassOptions.getByOptionNumber(choice)) {
                case TEACHER -> searchType(getContainer().getTeacherInstance(), param);
                case STUDENT -> searchType(getContainer().getStudentInstance(), param);
                case GROUP -> searchType(getContainer().getGroupInstance(), param);
                case GRADE -> searchType(getContainer().getGradeInstance(), param);
                case SUBJECT -> searchType(getContainer().getSubjectInstance(), param);
                case RETURN_TO_INITIAL_MENU -> InitialMenu.getInstance().execute();
                case EXIT -> exitApplication();
                default -> System.err.println("Invalid choice.");
            }
        } catch (IncorrectInputException e) {
            System.err.println("Returning to initial menu");
            getInitialMenu().execute();
        }
    }
}
