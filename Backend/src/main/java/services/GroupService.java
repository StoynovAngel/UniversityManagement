package services;

import entity.Group;
import interfaces.GroupRepository;
import utils.mappers.GroupMapper;

public class GroupService extends BasicService implements GroupRepository {
    private final GroupMapper groupMapper = new GroupMapper();

    @Override
    public Group getGroupById(Long id) {
        return selectQuery.getGroupById(id, groupMapper);
    }

    @Override
    public void updateGroupName(String name, Long id) {
        updateQuery.updateGroupName(name, id);
    }

    @Override
    public void insertGroup(String name) {
        insertQuery.insertGroup(name);
    }

    @Override
    public void insertStudentIntoGroup(String groupName, String studentUsername) {
        insertQuery.insertStudentIntoGroup(groupName, studentUsername);
    }
}
