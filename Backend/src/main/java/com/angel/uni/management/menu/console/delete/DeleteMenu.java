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
        ClassOptions.displayDeleteMenu();
    }

    @Override
    public void handleUserChoice() {
        System.out.print("Please enter your choice (0-6): ");
        int choice = userChoiceHandler(getDeleteMenu());
        handleNavigation(choice);
    }

    @Override
    public void handleNavigation(int choice) {
        ClassOptions option = navigationHandler(ClassOptions.class, choice);
        switch (option) {
            case SUBJECT -> deleteSubject();
            case RETURN_TO_INITIAL_MENU -> navigateTo(getInitialMenu());
            case EXIT -> exitApplication();
            default -> System.out.println("Incorrect choice provided " + choice + ". It must be either 0,2 or 6)");
        }
    }

    private <T> void delete(Service<T, ?, ?> service, Long id) {
        Command deleteCommand = new DeleteCommand<>(service, id);
        deleteCommand.execute();
    }

    private void deleteSubject() {
        delete(getContainer().getSubjectInstance(), deleteSubjectForm());
    }

    private long deleteSubjectForm()  {
        System.out.print("Subject id to be deleted: ");
        if (!in.hasNextLong()) {
            in.nextLine();
            String errorMessage = "Invalid input for Subject id. Expected a long.";
            System.err.println("Incorrect id provided. Please write an integer value next time.");
            QueryLogger.logError(errorMessage);
        }
        return in.nextLong();
    }
}
