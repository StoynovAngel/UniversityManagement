package com.angel.uni.management.menu;

import com.angel.uni.management.command.ReadCommand;
import com.angel.uni.management.interfaces.Command;
import com.angel.uni.management.interfaces.Menu;
import com.angel.uni.management.interfaces.Service;
import com.angel.uni.management.utils.container.DependencyContainer;

import java.util.Scanner;

public class SearchMenu implements Menu {
    protected final DependencyContainer container;
    private final Scanner in = new Scanner(System.in);
    private final Menu initialMenu;

    public SearchMenu(DependencyContainer container, Menu initialMenu) {
        this.container = container;
        this.initialMenu = initialMenu;
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
        int choice = in.nextInt();
        handleNavigation(choice);
    }

    @Override
    public void handleNavigation(int choice) {
        switch (choice) {
            case 0 -> exitApplication();
            case 1 -> new SearchByIdMenu(container, this).searchById();
            case 2 -> new SearchByNameMenu(container, this).searchByName();
            case 3 -> initialMenu.run();
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

    protected long idHandler() {
        System.out.print("Enter valid id: ");
        return in.nextLong();
    }

    protected String nameHandler() {
        System.out.print("Enter valid name: ");
        return in.next();
    }

    private void exitApplication() {
        System.out.println("Goodbye!");
        System.exit(0);
    }
}
