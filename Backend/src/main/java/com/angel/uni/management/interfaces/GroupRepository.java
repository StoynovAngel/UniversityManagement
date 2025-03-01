package com.angel.uni.management.interfaces;

import com.angel.uni.management.entity.Group;

import java.util.Optional;

public interface GroupRepository {
    Optional<Group> getGroupById(Long id);
    void updateGroupName(String name, Long id);
    void insertGroup(String name);
    void insertStudentIntoGroup(String groupName, String studentUsername);
}
