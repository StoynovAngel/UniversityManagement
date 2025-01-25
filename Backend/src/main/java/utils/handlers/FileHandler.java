package utils.handlers;

import entity.Group;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {
    private final String directory;
    private final String fileExtension = ".dat";

    public FileHandler(String directory) {
        this.directory = directory.endsWith("/") ? directory : directory + "/";
        createDirectoryIfNotExists();
    }

    public void saveGroupToFile(Group group) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(directory + group.getGroupName() + fileExtension))) {
            oos.writeObject(group);
            System.out.println("Group saved to " + directory + group.getGroupName());
        } catch (IOException e) {
            throw new RuntimeException("Error saving group to file", e);
        }
    }

    public Group loadGroup(String fileName) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(directory + fileName + fileExtension))) {
            return (Group) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException("Error reading groups from file", e);
        }
    }

    public List<Group> loadAllGroups() {
        List<Group> groups = new ArrayList<>();
        File folder = new File(directory);
        File[] files = folder.listFiles((dir, name) -> name.endsWith(fileExtension));
        if (files == null || files.length == 0) {
            System.out.println("No group files found in directory: " + directory);
            return groups;
        }
        for (File file : files) {
            try {
                groups.add(loadGroup(file.getName().replace(fileExtension, "")));
            } catch (RuntimeException e) {
                System.err.println("Failed to load group from file: " + file.getName());
            }
        }
        return groups;
    }

    private void createDirectoryIfNotExists() {
        try {
            Files.createDirectories(Paths.get(directory));
        } catch (IOException e) {
            throw new RuntimeException("Failed to create directory: " + directory, e);
        }
    }
}
