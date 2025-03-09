package com.angel.uni.management.menu.console.delete;

import com.angel.uni.management.command.DeleteCommand;
import com.angel.uni.management.config.QueryLogger;
import com.angel.uni.management.enums.ClassOptions;
import com.angel.uni.management.interfaces.Command;
import com.angel.uni.management.interfaces.Service;
import com.angel.uni.management.menu.console.Menu;
import com.angel.uni.management.utils.exceptions.IncorrectInputException;

import java.util.InputMismatchException;

public class DeleteMenu extends Menu implements Command {
    private static volatile DeleteMenu instance;

    public static DeleteMenu getInstance() {
        if (instance == null) {
            synchronized (DeleteMenu.class) {
                if (instance == null) {
                    instance = new DeleteMenu();
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
                2. Delete subject
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
        switch (ClassOptions.getByOptionNumber(choice)) {
            case SUBJECT -> deleteSubject();
            case RETURN_TO_INITIAL_MENU -> navigateTo(getInitialMenu());
            case EXIT -> exitApplication();
            default -> System.err.println("Incorrect choice provided " + choice + ". It must be between (0-3)");
        }
    }

    private <T> void delete(Service<T, ?, ?> service, Long id) {
        Command deleteCommand = new DeleteCommand<>(service, id);
        deleteCommand.execute();
    }

    private void deleteSubject() {
        try {
            delete(getContainer().getSubjectInstance(), deleteSubjectForm());
        } catch (IncorrectInputException e) {
            System.out.println("Unsuccessful update. Returning to UpdateMenu");
            navigateTo(getUpdateMenu());
        }
    }

    private long deleteSubjectForm() throws IncorrectInputException {
        try {
            System.out.print("Subject id to be deleted: ");
            if (!in.hasNextLong()) {
                in.nextLine();
                throw new InputMismatchException("Invalid input for Subject id. Expected a long.");
            }
            return in.nextLong();
        } catch (InputMismatchException e) {
            String errorMessage = "Token does not match the Integer regular expression, or is out of range";
            System.err.println("Incorrect id provided. Please write a integer value next time.");
            QueryLogger.logError(errorMessage, e.getMessage());
            throw new IncorrectInputException(errorMessage);
        }
    }
}
