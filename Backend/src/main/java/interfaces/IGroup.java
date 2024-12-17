package interfaces;

import dto.Group;

public interface IGroup {
    void addGroup();
    void addNewUserToGroup();
    void displaySpecificGroupFromFile();
    Group getGroup();
    void addNewGradeToUser();
    void updateUserGrade();
    void deleteUserGrade();
    void deleteUserFromSpecificGroup();
    void displaySpecificUserGrades();
    void displayUserFromSpecificGroup();
}
