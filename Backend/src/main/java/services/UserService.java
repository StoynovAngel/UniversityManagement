package services;

import dto.Group;
import dto.User;
import utils.handlers.UserHandler;
import interfaces.IUser;

public class UserService implements IUser {
    private final UserHandler userHandler;

    public UserService(UserHandler userHandler) {
        this.userHandler = userHandler;
    }

    @Override
    public void addGradeUser(Group loadedGroup) {
        userHandler.addGradeUser(loadedGroup);
    }

    @Override
    public User createUser() {
        return userHandler.createUser();
    }

    @Override
    public User getUserFromGroup(Group loadedGroup) {
        return userHandler.getUserFromGroup(loadedGroup);
    }

    @Override
    public void updateUserGrade(Group loadedGroup) {
        userHandler.updateUserGrade(loadedGroup);
    }

    @Override
    public void deleteUserGrade(Group loadedGroup) {
        userHandler.deleteUserGrade(loadedGroup);
    }

    @Override
    public void deleteUser(Group loadedGroup) {
        userHandler.deleteUser(loadedGroup);
    }

}
