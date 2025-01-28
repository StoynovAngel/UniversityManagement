package interfaces;

import entity.Group;

public interface GroupRepository {
    Group getGroupById(Long id);
    void updateGroupName(String name, Long id);
}
