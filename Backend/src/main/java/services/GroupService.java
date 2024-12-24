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
    public void displayUserFromSpecificGroup() {

    }

    @Override
    public void updateUserGradeAndSaveToFile() {

    }

    @Override
    public void deleteUserGradeAndSaveToFile() {

    }

    @Override
    public void deleteUserFromSpecificGroup() {

    }

    @Override
    public void addGroup() {
        groupHandler.addGroup();
    }

    @Override
    public void addNewUserToGroup() {

    }

    @Override
    public void addNewGradeToUserAndSaveToFile() {

    }

}
