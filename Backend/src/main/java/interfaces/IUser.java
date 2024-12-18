package interfaces;

import dto.Group;
import dto.User;

public interface IUser {
    void addGradeUser(Group loadedGroup);
    User createUser();
    User getUserFromGroup(Group loadedGroup);
    void updateUserGrade(Group loadedGroup);
    void deleteUserGrade(Group loadedGroup);
    void deleteUser(Group loadedGroup);
}
