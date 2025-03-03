package com.angel.uni.management.menu;

import com.angel.uni.management.interfaces.Menu;
import com.angel.uni.management.utils.container.DependencyContainer;

import java.util.Scanner;

public class InitialMenu implements Menu {
    private final Scanner in = new Scanner(System.in);
    private final DependencyContainer container;

    public InitialMenu(DependencyContainer container) {
        this.container = container;
    }

    @Override
    public void run() {
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
            case 1 -> navigateTo(new SearchMenu(container, this));
            default -> System.err.println("Invalid choice. Please enter a number between 0 and 4.");
        }
    }

    private void navigateTo(Menu menu) {
        menu.run();
    }

    private void exitApplication() {
        System.out.println("Goodbye!");
        System.exit(0);
    }
}
