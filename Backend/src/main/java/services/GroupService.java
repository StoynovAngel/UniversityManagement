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
    public void addGroup() {
        groupHandler.addGroup();
    }

}
