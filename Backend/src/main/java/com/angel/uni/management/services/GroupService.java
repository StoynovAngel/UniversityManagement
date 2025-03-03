package com.angel.uni.management.services;

import com.angel.uni.management.dto.simple.SimpleGroupDTO;
import com.angel.uni.management.dto.update.UpdateGroupDTO;
import com.angel.uni.management.entity.Group;
import com.angel.uni.management.interfaces.QueryManager;
import com.angel.uni.management.interfaces.Service;
import com.angel.uni.management.utils.mappers.Mappers;

import java.util.Optional;

public class GroupService implements Service<Group, UpdateGroupDTO, SimpleGroupDTO> {
    private final QueryManager queryManager;

    public GroupService(QueryManager queryManager) {
        this.queryManager = queryManager;
    }

    @Override
    public void create(SimpleGroupDTO dto) {
        queryManager.insertQuery().insertGroup(dto.groupName());
    }

    @Override
    public Optional<Group> read(Long id) {
        return queryManager.selectQuery().getGroupById(id, Mappers.getGroupMapper());
    }

    @Override
    public Optional<Group> read(String name) {
        return queryManager.selectQuery().getGroupByName(name, Mappers.getGroupMapper());
    }

    @Override
    public void update(UpdateGroupDTO dto) {
        queryManager.updateQuery().updateGroupName(dto.name(), dto.id());
    }

    @Override
    public void delete(Long id) {
    }
}
