package com.angel.uni.management.menu;

import com.angel.uni.management.command.ReadCommand;
import com.angel.uni.management.interfaces.Command;
import com.angel.uni.management.interfaces.Menu;
import com.angel.uni.management.services.*;
import com.angel.uni.management.utils.container.DependencyContainer;

import java.util.Scanner;

public class SearchMenu implements Menu {
    private final DependencyContainer container;
    private Scanner in = new Scanner(System.in);
    private final Menu initialMenu;

    public SearchMenu(DependencyContainer container, Menu initialMenu) {
        this.container = container;
        this.initialMenu = initialMenu;
    }

    @Override
    public void run() {
        displayMenu();
        handleUserChoice();
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
        int choice = in.nextInt();
        handleNavigation(choice);
    }

    @Override
    public void handleNavigation(int choice) {
        switch (choice) {
            case 0 -> {
                System.out.println("Byeee...");
                System.exit(1);
            }
            case 1 -> searchById();
            case 2 -> searchByName();
            case 3 -> initialMenu.run();

            default -> System.err.println("Incorrect choice provided " + choice + ". It must be between (0-3)");
        }
    }

    private void searchById() {
        displaySpecifics();
        int choice = in.nextInt();
        if (choice == 0) {
            System.exit(1);
        } else if (choice >= 1 && choice <= 5) {
            long id = idHandler();
            getSpecificAttributeById(choice, id);
        } else if (choice == 6) {
            run();
        } else {
            System.err.println("Invalid choice.");
        }
    }

    private void searchByName() {
        displaySpecifics();
        int choice = in.nextInt();
        if (choice == 0) {
            System.exit(1);
        } else if (choice >= 1 && choice <= 5) {
            String name = nameHandler();
            getSpecificAttributeByName(choice, name);
        } else if (choice == 6) {
            run();
        } else {
            System.err.println("Invalid choice.");
        }
    }

    private void displaySpecifics() {
        System.out.println("""
                Search by Name:
                1. Search specific teacher
                2. Search specific student
                3. Search specific group
                4. Search specific grade
                5. Search specific subject
                6. Go back to search menu
                0. Exit
                """);
    }

    private void getSpecificAttributeById(int choice, long id) {
        switch (choice) {
            case 1 -> searchTeacherById(id);
            case 2 -> searchStudentById(id);
            case 3 -> searchGroupById(id);
            case 4 -> searchGradeById(id);
            case 5 -> searchSubjectById(id);
            default -> System.err.println("Invalid choice.");
        }
    }

    private void getSpecificAttributeByName(int choice, String name) {
        switch (choice) {
            case 1 -> searchTeacherByName(name);
            case 2 -> searchStudentByName(name);
            case 3 -> searchGroupByName(name);
            case 4 -> searchGradeByName(name);
            case 5 -> searchSubjectByName(name);
            default -> System.err.println("Invalid choice.");
        }
    }

    private void searchTeacherById(long id) {
        TeacherService teacherService = container.getTeacherInstance();
        Command readCommand = new ReadCommand<>(teacherService, id);
        readCommand.execute();
    }

    private void searchStudentById(long id) {
        StudentService studentService = container.getStudentInstance();
        Command readCommand = new ReadCommand<>(studentService, id);
        readCommand.execute();
    }

    private void searchGroupById(long id) {
        GroupService groupService = container.getGroupInstance();
        Command readCommand = new ReadCommand<>(groupService, id);
        readCommand.execute();
    }

    private void searchGradeById(long id) {
        GradeService gradeService = container.getGradeInstance();
        Command readCommand = new ReadCommand<>(gradeService, id);
        readCommand.execute();
    }

    private void searchSubjectById(long id) {
        SubjectService subjectService = container.getSubjectInstance();
        Command readCommand = new ReadCommand<>(subjectService, id);
        readCommand.execute();
    }

    private void searchTeacherByName(String name) {
        TeacherService teacherService = container.getTeacherInstance();
        Command readCommand = new ReadCommand<>(teacherService, name);
        readCommand.execute();
    }

    private void searchStudentByName(String name) {
        StudentService studentService = container.getStudentInstance();
        Command readCommand = new ReadCommand<>(studentService, name);
        readCommand.execute();
    }

    private void searchGroupByName(String name) {
        GroupService groupService = container.getGroupInstance();
        Command readCommand = new ReadCommand<>(groupService, name);
        readCommand.execute();
    }

    private void searchGradeByName(String name) {
        GradeService gradeService = container.getGradeInstance();
        Command readCommand = new ReadCommand<>(gradeService, name);
        readCommand.execute();
    }

    private void searchSubjectByName(String name) {
        SubjectService subjectService = container.getSubjectInstance();
        Command readCommand = new ReadCommand<>(subjectService, name);
        readCommand.execute();
    }

    private long idHandler() {
        System.out.print("Enter valid id: ");
        return in.nextLong();
    }

    private String nameHandler() {
        System.out.print("Enter valid name: ");
        return in.next();
    }
}
