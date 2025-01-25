package services;

import entity.Group;
import utils.handlers.FileHandler;
import interfaces.FileRepository;

import java.util.List;

public class FileService implements FileRepository {
    private final FileHandler fileHandler;

    public FileService(FileHandler fileHandler) {
        this.fileHandler = fileHandler;
    }

    @Override
    public void saveGroupToFile(Group group) {
        fileHandler.saveGroupToFile(group);
    }

    @Override
    public Group loadGroup(String fileName) {
        return fileHandler.loadGroup(fileName);
    }

    @Override
    public List<Group> loadAllGroups() {
       return fileHandler.loadAllGroups();
    }

}
