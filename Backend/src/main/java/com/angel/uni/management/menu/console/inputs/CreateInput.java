package com.angel.uni.management.menu.console.inputs;

import com.angel.uni.management.config.QueryLogger;
import com.angel.uni.management.dto.simple.*;
import com.angel.uni.management.enums.GradeType;
import com.angel.uni.management.interfaces.SimpleDTO;
import com.angel.uni.management.utils.container.DependencyContainer;

import java.util.function.Function;

public class CreateInput extends InputForms<SimpleDTO> {
    private final DependencyContainer dependencyContainer = DependencyContainer.getDependencyContainer();

    @Override
    public SimpleSubjectDTO inputSubjectForm() {
        while (true) {
            System.out.print("Please enter subject's name: ");
            String subjectName = in.nextLine().trim();
            while (checkIfStringIsEmpty(subjectName)) {
                System.out.println("Subject's name cannot be empty.");
                System.out.print("Please enter subject's name: ");
                subjectName = in.nextLine().trim();
            }

            System.out.print("Please enter how many hours per week the subject is being studied: ");
            while (!in.hasNextInt()) {
                System.out.println("Please enter a valid number for hours.");
                System.out.print("Please enter how many hours per week the subject is being studied: ");
                in.next();
            }
            int hoursPerWeek = in.nextInt();
            while (hoursPerWeek <= 0) {
                System.out.println("Hours per week must be greater than 0.");
                System.out.print("Please enter how many hours per week the subject is being studied: ");
                hoursPerWeek = in.nextInt();
            }
            in.nextLine();
            String teacherName = searchForTeacher();

            System.out.print("Please enter subject's description: ");
            String subjectDescription = in.nextLine().trim();
            while (subjectDescription.isEmpty()) {
                System.out.println("Subject's description cannot be empty.");
                System.out.print("Please enter subject's description: ");
                subjectDescription = in.nextLine().trim();
            }

            return new SimpleSubjectDTO(subjectName, hoursPerWeek, teacherName, subjectDescription);
        }
    }

    @Override
    public SimpleGradeDTO inputGradeForm() {
        while (true) {
            System.out.print("Please enter grade's name: ");
            String gradeName = in.nextLine().trim();
            if (checkIfStringIsEmpty(gradeName)) {
                System.out.println("Grade name cannot be empty. Try again.");
                continue;
            }
            double mark = validateStudentMark();
            String teacherName = searchForTeacher();
            String studentName = searchForStudent();
            String gradeType = chooseGradeType();
            return new SimpleGradeDTO(gradeName, mark, teacherName, studentName, gradeType);
        }
    }

    @Override
    public SimpleTeacherDTO inputTeacherForm() {
        return createFormForSingleStringInput(SimpleTeacherDTO::new, "Enter teacher's name: ");
    }

    @Override
    public SimpleStudentDTO inputStudentForm() {
        return createFormForSingleStringInput(SimpleStudentDTO::new, "Enter student's username: ");
    }

    @Override
    public SimpleGroupDTO inputGroupForm() {
        return createFormForSingleStringInput(SimpleGroupDTO::new, "Enter group name: ");
    }

    private double validateStudentMark() {
        double mark;
        while (true) {
            System.out.print("Student's mark: ");
            if (!in.hasNextDouble()) {
                System.out.println("Input is not a valid number. Try again.");
                QueryLogger.logError("Non-numeric input for mark in " + getClass().getSimpleName());
                in.nextLine();
                continue;
            }
            mark = in.nextDouble();
            in.nextLine();
            break;
        }
        return mark;
    }

    private String searchForTeacher() {
        String teacherName;
        while (true) {
            System.out.print("Please enter teacher's name. This user MUST exist: ");
            teacherName = in.nextLine();
            if (checkIfStringIsEmpty(teacherName)) {
                System.out.println("Teacher's name cannot be empty. Try again.");
                continue;
            }
            if (dependencyContainer.getTeacherInstance().read(teacherName).isEmpty()) {
                System.out.println("No such teacher found: " + teacherName + ". Try again.");
                continue;
            }
            break;
        }
        return teacherName;
    }

    private String searchForStudent() {
        String studentName;
        while (true) {
            System.out.print("Please enter student's name. This user MUST exist: ");
            studentName = in.nextLine();
            if (checkIfStringIsEmpty(studentName)) {
                System.out.println("Student's name cannot be empty. Try again.");
                continue;
            }
            if (dependencyContainer.getStudentInstance().read(studentName).isEmpty()) {
                System.out.println("No such student found: " + studentName + ". Try again.");
                continue;
            }
            break;
        }
        return studentName;
    }

    private boolean checkIfStringIsEmpty(String str) {
        return str.isEmpty();
    }

    private <T> T createFormForSingleStringInput(Function<String, T> dtoConstructor, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = in.nextLine();

            if (input == null || input.trim().isEmpty()) {
                String errorMessage = "Input is either null or empty for: " + prompt;
                System.out.println("Invalid input. Please try again.");
                QueryLogger.logError(errorMessage + " In " + getClass().getSimpleName());
            } else {

                return dtoConstructor.apply(input);
            }
        }
    }

    private String chooseGradeType() {
        while (true) {
            displayGradeTypeOptions();
            System.out.print("Choose grade type: ");

            if (!in.hasNextInt()) {
                System.err.println("Invalid input. Please enter a number between 1 and 5.");
                in.nextLine();
                continue;
            }

            int choice = in.nextInt();
            in.nextLine();

            String result = getGradeTypeToString(choice);
            if (result != null) {
                return result;
            }
        }
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
                default -> System.out.println("Invalid choice");
            }
        }
    }
}
