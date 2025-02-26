package com.angel.uni.management.services;

import com.angel.uni.management.entity.Group;
import com.angel.uni.management.interfaces.GroupRepository;
import com.angel.uni.management.interfaces.QueryManager;
import com.angel.uni.management.utils.mappers.Mappers;

public class GroupService implements GroupRepository {
    private final QueryManager queryManager;

    public GroupService(QueryManager queryManager) {
        this.queryManager = queryManager;
    }

    @Override
    public Group getGroupById(Long id) {
        return queryManager.selectQuery().getGroupById(id, Mappers.getGroupMapper());
    }

    @Override
    public void updateGroupName(String name, Long id) {
        queryManager.updateQuery().updateGroupName(name, id);
    }

    @Override
    public void insertGroup(String name) {
        queryManager.insertQuery().insertGroup(name);
    }

    @Override
    public void insertStudentIntoGroup(String groupName, String studentUsername) {
        queryManager.insertQuery().insertStudentIntoGroup(groupName, studentUsername);
    }
}
