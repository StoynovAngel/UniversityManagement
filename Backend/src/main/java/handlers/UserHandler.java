package handlers;

import dto.Grade;
import dto.Group;
import dto.User;
import exceptions.GradeNotFound;
import exceptions.InvalidUserInput;
import exceptions.UserNotFoundException;
import services.FileService;
import services.GradeService;
import services.GroupService;

import java.util.List;
import java.util.Scanner;

public class UserHandler {
    private final Scanner in = new Scanner(System.in);
    private final GradeService gradeService;

    public UserHandler(GradeService gradeService) {
        this.gradeService = gradeService;
    }

    public User createUser() {
        System.out.print("Username: ");
        String username = in.nextLine();
        User user = new User(username);
        while (true) {
            Grade newGrade = gradeService.addGradeToUser();
            user.getGrades().add(newGrade);

            System.out.println("Add another grade? (y/n): ");
            String choice = in.nextLine().toLowerCase().trim();
            if (!choice.equals("y")) {
                System.out.println("Finished adding grades.");
                break;
            }
        }
        return user;
    }
}
