package interfaces;

import dto.User;

public interface IGrade {
    void addGrade(User user);
    void updateGrade(User user);
    void deleteGrade(User user);
}
