package services;

import entity.Group;
import interfaces.GroupRepository;
import utils.mappers.Mappers;

public class GroupService extends BasicService implements GroupRepository {

    @Override
    public Group getGroupById(Long id) {
        return selectQuery.getGroupById(id, Mappers.getGroupMapper());
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
