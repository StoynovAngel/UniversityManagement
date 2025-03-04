package com.angel.uni.management.menu;

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
        System.out.println("""
                Options:
                1. Search menu
                2. Create menu
                3. Delete menu
                4. Update menu
                0. Exit
                """);
    }

    @Override
    public void handleUserChoice() {
        System.out.print("Please enter your choice (0-4): ");
        int choice = in.nextInt();
        handleNavigation(choice);
    }

    @Override
    public void handleNavigation(int choice) {
        switch (choice) {
            case 0 -> exitApplication();
            case 1 -> navigateTo(getSearchMenu());
            case 2 -> navigateTo(getCreateMenu());
            default -> System.err.println("Invalid choice. Please enter a number between 0 and 4.");
        }
    }
}
