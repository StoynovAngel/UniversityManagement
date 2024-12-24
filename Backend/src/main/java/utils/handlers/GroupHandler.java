package utils.handlers;

import dto.Group;
import utils.exceptions.*;
import services.FileService;
import utils.validation.Validator;

import java.util.List;
import java.util.Scanner;

public class GroupHandler extends Validator {
    private final Scanner in = new Scanner(System.in);
    private final FileService fileService;

    public GroupHandler(FileService fileService) {
        this.fileService = fileService;
    }

    public void displaySpecificGroupFromFile() {
        System.out.println(getGroup());
    }

    public void addGroup() {
        System.out.print("Enter group name: ");
        String groupName = in.nextLine();
        validateGroupUserInput(groupName);

        List<Group> loadedGroups = loadAllGroups();
        validateFindFileGroupName(loadedGroups, groupName);

        Group newGroup = new Group(groupName);
        saveGroup(newGroup);
    }


    private void saveGroup(Group group){
        fileService.saveGroupToFile(group);
    }

    private Group loadGroup(String searchedName) {
        return fileService.loadGroup(searchedName);
    }

    private List<Group> loadAllGroups() {
        return fileService.loadAllGroups();
    }


    private Group getGroup() {
        System.out.print("Enter group name: ");
        String searchedGroupName = in.nextLine();
        Group group = loadGroup(searchedGroupName);

        if (groupNameHandler(group, searchedGroupName)) {
            throw new GroupNotFoundException("Could not find group with the name: " + searchedGroupName);
        }

        return group;
    }

}
