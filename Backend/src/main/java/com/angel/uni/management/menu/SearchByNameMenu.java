package com.angel.uni.management.menu;

import java.util.Scanner;

public class SearchByNameMenu extends SearchMenu {
    private static volatile SearchByNameMenu instance;
    private final Scanner in = new Scanner(System.in);

    public static SearchByNameMenu getInstance() {
        if (instance == null) {
            synchronized (SearchByNameMenu.class) {
                if (instance == null) {
                    instance = new SearchByNameMenu();
                }
            }
        }
        return instance;
    }

    public void execute() {
        displaySpecifics();
        System.out.print("Please enter your choice (0-6): ");
        int choice = in.nextInt();
        getSpecificAttributeByName(choice);
    }

    protected void getSpecificAttributeByName(int choice) {
        String name = nameHandler();
        switch (choice) {
            case 1 -> searchType(getContainer().getTeacherInstance(), name);
            case 2 -> searchType(getContainer().getStudentInstance(), name);
            case 3 -> searchType(getContainer().getGroupInstance(), name);
            case 4 -> searchType(getContainer().getGradeInstance(), name);
            case 5 -> searchType(getContainer().getSubjectInstance(), name);
            default -> System.err.println("Invalid choice.");
        }
    }
}