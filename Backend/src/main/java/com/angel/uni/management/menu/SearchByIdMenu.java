package com.angel.uni.management.menu;

public class SearchByIdMenu extends SearchMenu {
    private static volatile SearchByIdMenu instance;

    public static SearchByIdMenu getInstance() {
        if (instance == null) {
            synchronized (SearchByIdMenu.class) {
                if (instance == null) {
                    instance = new SearchByIdMenu();
                }
            }
        }
        return instance;
    }

    public void execute() {
        displaySpecifics();
        System.out.print("Please enter your choice (0-6): ");
        int choice = in.nextInt();
        getSpecificAttributeById(choice);
    }

    protected void getSpecificAttributeById(int choice) {
        long id = idHandler();
        switch (choice) {
            case 1 -> searchType(getContainer().getTeacherInstance(), id);
            case 2 -> searchType(getContainer().getStudentInstance(), id);
            case 3 -> searchType(getContainer().getGroupInstance(), id);
            case 4 -> searchType(getContainer().getGradeInstance(), id);
            case 5 -> searchType(getContainer().getSubjectInstance(), id);
            case 6 -> InitialMenu.getInstance().execute();
            default -> System.err.println("Invalid choice.");
        }
    }
}