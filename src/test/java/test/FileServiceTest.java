package test;

import dto.Group;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import services.FileService;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FileServiceTest {
    private Path tempDirectory;
    private FileService fileService;

    @BeforeEach
    void setUp() {
        try {
            tempDirectory = Files.createTempDirectory("testDirectory");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        fileService = new FileService(tempDirectory.toString());
    }

    @AfterEach
    void tearDown() {
        try {
            Files.walk(tempDirectory)
                    .map(Path::toFile)
                    .forEach(File::delete);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        tempDirectory.toFile().delete();
    }
    @Test
    void testSaveGroupToFile() {
        Group mockGroup = mock(Group.class);
        when(mockGroup.getGroupName()).thenReturn("mockGroup");
        fileService.saveGroupToFile(mockGroup);
        File savedFile = tempDirectory.resolve("mockGroup.dat").toFile();
        assertTrue(savedFile.exists(), "The group should exist.");
    }

    @Test
    void testLoadGroup() {
        Group mockGroup = mock(Group.class);
        when(mockGroup.getGroupName()).thenReturn("mockGroup");
        fileService.saveGroupToFile(mockGroup);
        Group loadedGroup = fileService.loadGroup(mockGroup.getGroupName());
        assertNotNull(loadedGroup, "The group should not be null");
    }

    @Test
    void testLoadAllGroups() {
    }
}