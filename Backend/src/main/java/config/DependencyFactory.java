package config;

import handlers.*;
import services.*;

public class DependencyFactory {

    public FileHandler createFileHandler() {
        return new FileHandler("files/");
    }

    public UserService createUserService() {
        return new UserService(createUserHandler());
    }

    public UserHandler createUserHandler() {
        return new UserHandler(createGradeService());
    }

    public GradeService createGradeService() {
        return new GradeService(createGradeHandler());
    }

    public GradeHandler createGradeHandler() {
        return new GradeHandler();
    }

    public GroupService createGroupService() {
        return new GroupService(createGroupHandler());
    }

    public GroupHandler createGroupHandler() {
        return new GroupHandler(createFileService(), createUserService());
    }

    public Interaction createInteraction() {
        return new Interaction(createGroupService());
    }

    public FileService createFileService() {
        return new FileService(createFileHandler());
    }
}
