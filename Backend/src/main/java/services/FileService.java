package services;

import dto.Group;
import handlers.FileHandler;
import interfaces.IFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileService implements IFile {
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
