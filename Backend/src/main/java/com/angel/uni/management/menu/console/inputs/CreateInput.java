package com.angel.uni.management.menu.console.inputs;

import com.angel.uni.management.config.QueryLogger;
import com.angel.uni.management.dto.simple.*;
import com.angel.uni.management.enums.GradeType;
import com.angel.uni.management.interfaces.SimpleDTO;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.function.Function;

public class CreateInput extends InputForms<SimpleDTO> {

    @Override
    public SimpleSubjectDTO inputSubjectForm() {
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

    @Override
    public SimpleGradeDTO inputGradeForm() {
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

    @Override
    public SimpleTeacherDTO inputTeacherForm() {
        String prompt = "Enter teacher's name: ";
        return createFormForSingleStringInput(SimpleTeacherDTO::new, prompt);
    }

    @Override
    public SimpleStudentDTO inputStudentForm() {
        String prompt = "Enter student's username: ";
        return createFormForSingleStringInput(SimpleStudentDTO::new, prompt);
    }

    @Override
    public SimpleGroupDTO inputGroupForm() {
        String prompt = "Enter group's name: ";
        return createFormForSingleStringInput(SimpleGroupDTO::new, prompt);
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

    private String chooseGradeType() {
        displayGradeTypeOptions();
        System.out.print("Choose grade type: ");
        return getGradeTypeToString(in.nextInt());
    }

    private void displayGradeTypeOptions() {
        System.out.println("""
                1. Final exam
                2. Mid exam
                3. Oral
                4. Project exam
                5. Homework
                """);
    }

    private String getGradeTypeToString(int choice) {
        while (true) {
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
