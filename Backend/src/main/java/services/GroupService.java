package services;

import utils.handlers.GroupHandler;
import interfaces.IGroup;

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
    public void addNewGradeToUserAndSaveToFile() {
        groupHandler.addNewGradeToUserAndSaveToFile();
    }

    @Override
    public void updateUserGradeAndSaveToFile() {
        groupHandler.updateUserGradeAndSaveToFile();
    }

    @Override
    public void deleteUserGradeAndSaveToFile() {
        groupHandler.deleteUserGrade();
    }

    @Override
    public void deleteUserFromSpecificGroup() {
        groupHandler.deleteUserFromSpecificGroup();
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
