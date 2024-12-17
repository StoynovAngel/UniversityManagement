package services;

import dto.Group;
import exceptions.GroupAlreadyExists;
import exceptions.InvalidGroup;
import exceptions.InvalidUserInput;
import handlers.GroupHandler;
import interfaces.IGroup;

import java.util.List;
import java.util.Scanner;

public class GroupService implements IGroup {
    private final GroupHandler groupHandler;

    public GroupService(GroupHandler groupHandler) {
        this.groupHandler = groupHandler;
    }

    @Override
    public void displaySpecificGroupFromFile() {
        groupHandler.displaySpecificGroupFromFile();
    }


    @Override
    public Group getGroup() {
        return groupHandler.getGroup();
    }

    @Override
    public void addNewGradeToUser() {
        groupHandler.addNewUserToGroup();
    }

    @Override
    public void updateUserGrade() {
        groupHandler.updateUserGrade();
    }

    @Override
    public void deleteUserGrade() {
        groupHandler.deleteUserGrade();
    }

    @Override
    public void deleteUserFromSpecificGroup() {
        groupHandler.deleteUserFromSpecificGroup();
    }

    @Override
    public void displaySpecificUserGrades() {
        groupHandler.displaySpecificUserGrades();
    }

    @Override
    public void displayUserFromSpecificGroup() {
        groupHandler.displayUserFromSpecificGroup();
    }

    @Override
    public void addGroup() {
        groupHandler.addGroup();
    }

    @Override
    public void addNewUserToGroup() {
        groupHandler.addNewUserToGroup();
    }
}
