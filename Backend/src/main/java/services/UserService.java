package services;

import dto.Grade;
import dto.Group;
import dto.User;
import exceptions.GradeNotFound;
import exceptions.GroupNotFoundException;
import exceptions.InvalidUserInput;
import exceptions.UserNotFoundException;
import handlers.UserHandler;
import interfaces.IUser;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserService implements IUser {
    private final UserHandler userHandler;

    public UserService(UserHandler userHandler) {
        this.userHandler = userHandler;
    }

    @Override
    public User createUser() {
        return userHandler.createUser();
    }
}
