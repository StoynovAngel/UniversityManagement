package interfaces;

import entity.Group;

import java.util.List;

public interface FileRepository {
    void saveGroupToFile(Group group);
    Group loadGroup(String fileName);
    List<Group> loadAllGroups();
}
