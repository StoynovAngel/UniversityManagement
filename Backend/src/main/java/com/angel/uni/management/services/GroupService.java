package com.angel.uni.management.services;

import com.angel.uni.management.entity.Group;
import com.angel.uni.management.interfaces.GroupRepository;
import com.angel.uni.management.utils.mappers.Mappers;
import com.angel.uni.management.utils.queries.insert.InsertQuery;
import com.angel.uni.management.utils.queries.select.SelectQuery;
import com.angel.uni.management.utils.queries.update.UpdateQuery;

public class GroupService implements GroupRepository {
    private static final SelectQuery selectQuery = QueryManager.getSelectQuery();
    private static final UpdateQuery updateQuery = QueryManager.getUpdateQuery();
    private static final InsertQuery insertQuery = QueryManager.getInsertQuery();

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
