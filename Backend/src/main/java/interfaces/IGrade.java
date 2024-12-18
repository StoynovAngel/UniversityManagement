package interfaces;

import dto.Grade;
import dto.Group;
import dto.User;

import java.util.List;

public interface IGrade {
    void addGrade(User user);
    void updateGrade(User user);
    void deleteGrade(User user);
}
