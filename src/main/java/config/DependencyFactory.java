package config;

import services.FileService;
import services.GroupService;
import services.InitialMenu;
import services.UserService;

public class DependencyFactory {


    public FileService createFileService() {
        return new FileService("files/");
    }

    public UserService createUserService() {
        return new UserService(createFileService());
    }

    public GroupService createGroupService() {
        return new GroupService(createFileService());
    }

    public InitialMenu createInitialMenu() {
        return new InitialMenu(createUserService(), createGroupService());
    }
}
