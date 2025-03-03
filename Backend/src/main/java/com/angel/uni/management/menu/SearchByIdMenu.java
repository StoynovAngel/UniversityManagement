package com.angel.uni.management.menu;

import com.angel.uni.management.interfaces.Menu;
import com.angel.uni.management.utils.container.DependencyContainer;

import java.util.Scanner;

public class SearchByIdMenu extends SearchMenu {
    private final Scanner in = new Scanner(System.in);

    public SearchByIdMenu(DependencyContainer container, Menu initialMenu) {
        super(container, initialMenu);
    }

    public void searchById() {
        displaySpecifics();
        int choice = in.nextInt();
        if (choice == 0) {
            System.exit(1);
        } else if (choice >= 1 && choice <= 5) {
            long id = idHandler();
            getSpecificAttributeById(choice, id);
        } else {
            System.err.println("Invalid choice.");
        }
    }

    protected void getSpecificAttributeById(int choice, long id) {
        switch (choice) {
            case 1 -> searchType(container.getTeacherInstance(), id);
            case 2 -> searchType(container.getStudentInstance(), id);
            case 3 -> searchType(container.getGroupInstance(), id);
            case 4 -> searchType(container.getGradeInstance(), id);
            case 5 -> searchType(container.getSubjectInstance(), id);
            case 6 -> run();
            default -> System.err.println("Invalid choice.");
        }
    }
}