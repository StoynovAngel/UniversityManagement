package com.angel.uni.management.menu;

import com.angel.uni.management.command.CreateCommand;
import com.angel.uni.management.config.QueryLogger;
import com.angel.uni.management.dto.simple.SimpleGradeDTO;
import com.angel.uni.management.dto.simple.SimpleGroupDTO;
import com.angel.uni.management.dto.simple.SimpleStudentDTO;
import com.angel.uni.management.dto.simple.SimpleSubjectDTO;
import com.angel.uni.management.enums.GradeType;
import com.angel.uni.management.interfaces.Command;
import com.angel.uni.management.interfaces.IMenu;
import com.angel.uni.management.interfaces.Service;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.function.Function;

public class CreateMenu extends Menu implements IMenu, Command {

    private static volatile CreateMenu instance;

    public static CreateMenu getInstance() {
        if (instance == null) {
            synchronized (CreateMenu.class) {
                if (instance == null) {
                    instance = new CreateMenu();
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
                1. Create specific student
                2. Create specific group
                3. Create specific grade
                4. Create specific subject
                5. Go back to search menu
                0. Exit
                """);
    }

    @Override
    public void handleUserChoice() {
        System.out.print("Please enter your choice (0-6): ");
        try {
            int choice = in.nextInt();
            in.nextLine();
            handleNavigation(choice);
        } catch (InputMismatchException e) {
            QueryLogger.logError("Input should be an integer", e.getMessage());
            System.err.println("Cannot proceed because the input is not correct. Try again.");
            exitApplication();
        }
    }

    @Override
    public void handleNavigation(int choice) {
        switch (choice) {
            case 1 -> createStudent();
            case 2 -> createGroup();
            case 3 -> createGrade();
            case 4 -> createSubject();
            case 5 -> getInitialMenu().execute();
            case 0 -> exitApplication();
            default -> System.err.println("Incorrect choice provided " + choice + ". It must be between (0-3)");
        }
    }

    private  <T, U, S> void create(Service<T, U, S> service, S dto) {
        Command createCommand = new CreateCommand<>(service, dto);
        createCommand.execute();
    }

    private void createStudent() {
        String prompt = "Enter student's username: ";
        create(getContainer().getStudentInstance(), createFormForSingleStringInput(SimpleStudentDTO::new, prompt));
    }

    private void createGroup() {
        String prompt = "Enter group's name: ";
        create(getContainer().getGroupInstance(), createFormForSingleStringInput(SimpleGroupDTO::new, prompt));
    }

    private <T> T createFormForSingleStringInput(Function<String, T> dto, String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                String input = in.nextLine().trim();
                if (!input.isEmpty()) {
                    return dto.apply(input);
                }
                System.err.println("Input cannot be empty. Please try again.");
            } catch (NoSuchElementException e) {
                QueryLogger.logError("Does not contain the specific element", e.getMessage());
                System.err.println("No input found. Please try again.");
                in.nextLine();
            }
        }
    }

    private void createSubject() {
        create(getContainer().getSubjectInstance(), subjectForm());
    }

    private SimpleSubjectDTO subjectForm() {
        while (true) {
            try {
                System.out.print("Please enter subject's name: ");
                String subjectName = in.nextLine().trim();

                System.out.print("Please enter how many hours per week the subject is being studied: ");
                int hoursPerWeek = in.nextInt();
                in.nextLine();

                System.out.print("Please enter teacher's name. User must exist: ");
                String teacherName = in.nextLine().trim();

                System.out.print("Please enter subject's description: ");
                String subjectDescription = in.nextLine().trim();

                return new SimpleSubjectDTO(subjectName, hoursPerWeek, teacherName, subjectDescription);

            } catch (InputMismatchException e) {
                String errorMessage = "Hours per week must be an integer. Please try again.";
                QueryLogger.logError(errorMessage, e.getMessage());
                System.err.println(errorMessage);
                in.nextLine();
            } catch (NoSuchElementException e) {
                String errorMessage = "No input found. Please try again.";
                QueryLogger.logError(errorMessage, e.getMessage());
                System.err.println(errorMessage);
                in.nextLine();
            }
        }
    }

    private void createGrade() {
        create(getContainer().getGradeInstance(), gradeForm());
    }

    private SimpleGradeDTO gradeForm() {
        while (true) {
            try {
                System.out.print("Please enter grade's name: ");
                String gradeName = in.nextLine().trim();

                System.out.print("Student's mark: ");
                double mark = in.nextDouble();
                in.nextLine();

                System.out.print("Please enter teacher's name. This user MUST exist: ");
                String teacherName = in.nextLine().trim();

                System.out.print("Please enter student's name. This user MUST exist: ");
                String studentName = in.nextLine().trim();

                String gradeType = chooseGradeType();

                return new SimpleGradeDTO(gradeName, mark, teacherName, studentName, gradeType);

            } catch (InputMismatchException e) {
                String errorMessage = "Hours per week must be an integer. Please try again.";
                QueryLogger.logError(errorMessage, e.getMessage());
                System.err.println(errorMessage);
                in.nextLine();
            } catch (NoSuchElementException e) {
                String errorMessage = "No input found. Please try again.";
                QueryLogger.logError(errorMessage, e.getMessage());
                System.err.println(errorMessage);
                in.nextLine();
            }
        }
    }
    private String chooseGradeType() {
        System.out.println("""
                1. Final exam
                2. Mid exam
                3. Oral
                4. Project exam
                5. Homework
                """);
        System.out.print("Choose grade type: ");
        int choice = in.nextInt();
        in.nextLine();
        while(true) {
            switch (choice) {
                case 1 -> {
                    return GradeType.FINAL_EXAM.toString();
                }
                case 2 -> {
                    return GradeType.MID_EXAM.toString();
                }
                case 3 -> {
                    return GradeType.ORAL.toString();
                }
                case 4 -> {
                    return GradeType.PROJECT_EXAM.toString();
                }
                case 5 -> {
                    return GradeType.HOMEWORK.toString();
                }
                default -> {
                    System.err.println("Invalid choice");
                    in.nextLine();
                }
            }
        }
    }
}
