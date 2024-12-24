package services;

import dto.Group;
import dto.User;
import utils.handlers.StudentHandler;
import interfaces.IUser;
import utils.handlers.UserHandler;

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
