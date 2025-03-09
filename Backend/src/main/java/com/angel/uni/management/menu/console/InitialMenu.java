package com.angel.uni.management.menu.console;

import com.angel.uni.management.enums.MenuOptions;
import com.angel.uni.management.interfaces.Command;
import com.angel.uni.management.interfaces.IMenu;

public class InitialMenu extends Menu implements IMenu, Command {
    private static volatile InitialMenu instance;

    public static InitialMenu getInstance() {
        if (instance == null) {
            synchronized (InitialMenu.class) {
                if (instance == null) {
                    instance = new InitialMenu();
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
        System.out.println("Options: ");
        MenuOptions.displayAllOptions();
    }

    @Override
    public void handleUserChoice() {
        System.out.print("Please enter your choice (0-4): ");
        int choice = in.nextInt();
        handleNavigation(choice);
    }

    @Override
    public void handleNavigation(int choice) {
        switch (MenuOptions.getByOptionNumber(choice)) {
            case EXIT -> exitApplication();
            case SEARCH -> navigateTo(getSearchMenu());
            case CREATE -> navigateTo(getCreateMenu());
            case DELETE -> navigateTo(getDeleteMenu());
            case UPDATE -> navigateTo(getUpdateMenu());
            default -> System.err.println("Invalid choice. Please enter a number between 0 and 4.");
        }
    }
}
