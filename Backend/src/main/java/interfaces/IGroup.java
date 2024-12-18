package interfaces;

public interface IGroup {
    void addGroup();
    void addNewUserToGroup();
    void addNewGradeToUserAndSaveToFile();
    void displaySpecificGroupFromFile();
    void displayUserFromSpecificGroup();
    void updateUserGradeAndSaveToFile();
    void deleteUserGradeAndSaveToFile();
    void deleteUserFromSpecificGroup();
}
