package services;

import entity.Group;
import interfaces.GroupRepository;
import utils.mappers.Mappers;

public class GroupService implements GroupRepository {

    @Override
    public Group getGroupById(Long id) {
        return QueryManager.getSelectQuery().getGroupById(id, Mappers.getGroupMapper());
    }

    @Override
    public void updateGroupName(String name, Long id) {
        QueryManager.getUpdateQuery().updateGroupName(name, id);
    }

    @Override
    public void insertGroup(String name) {
        QueryManager.getInsertQuery().insertGroup(name);
    }

    @Override
    public void insertStudentIntoGroup(String groupName, String studentUsername) {
        QueryManager.getInsertQuery().insertStudentIntoGroup(groupName, studentUsername);
    }
}
