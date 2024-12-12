package test;

import dto.Group;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import services.FileService;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FileServiceTest {
    private Path tempDirectory;
    private FileService mockFileService;
    private FileService realFileService;

    @BeforeEach
    void setUp() throws IOException {
        mockFileService = mock(FileService.class);
        tempDirectory = Files.createTempDirectory("testDirectory");
        realFileService = new FileService(tempDirectory.toString());
    }

    @AfterEach
    void tearDown() throws IOException {
        if (tempDirectory != null) {
            Files.walk(tempDirectory)
                    .sorted(Comparator.reverseOrder())
                    .map(Path::toFile)
                    .forEach(File::delete);
        }
    }

    private Group createMockGroup(String mockGroupName) {
        return new Group(mockGroupName);
    }

    private Group getMockedGroup() {
        Group mockGroup = createMockGroup("mockGroup");
        when(mockFileService.loadGroup("mockGroup")).thenReturn(mockGroup);
        return mockFileService.loadGroup("mockGroup");
    }

    private List<Group> getListOfMockGroups(String... groupNames) {
        List<Group> mockGroups = new ArrayList<>();
        for (String groupName : groupNames) {
            mockGroups.add(new Group(groupName));
        }
        when(mockFileService.loadAllGroups()).thenReturn(mockGroups);
        return mockGroups;
    }

    private void saveGroupsToFile(Group... groups) {
        for (Group group : groups) {
            realFileService.saveGroupToFile(group);
        }
    }

    private void assertFileExists() {
        File file = tempDirectory.resolve("realGroup.dat").toFile();
        assertTrue(file.exists(), "The file " + "realGroup.dat" + " should exist after saving the group");
    }

    private void assertGroupExists(List<Group> groups, String groupName) {
        assertTrue(
                groups.stream().anyMatch(group -> groupName.equals(group.getGroupName())),
                "The list should contain group with name: " + groupName
        );
    }

    private void createDirectoryIfNotExists(String directory) {
        try {
            Files.createDirectories(Paths.get(directory));
        } catch (IOException e) {
            throw new RuntimeException("Failed to create directory: " + directory, e);
        }
    }


    @Test
    @DisplayName("Mocking test to verify saveGroupToFile.")
    void testSaveGroupToFileWithMock() {
        Group mockGroup = createMockGroup("mockGroup");
        mockFileService.saveGroupToFile(mockGroup);
        verify(mockFileService, times(1)).saveGroupToFile(mockGroup);
    }

    @Test
    @DisplayName("Mocking test to check for null in loadGroup.")
    void testLoadGroupWithMockNotNull() {
        Group loadedGroup = getMockedGroup();
        assertNotNull(loadedGroup, "The group should not be null");
    }

    @Test
    @DisplayName("Mocking test to check for equality in loadGroup.")
    void testLoadGroupWithMockEquals() {
        Group loadedGroup = getMockedGroup();
        assertEquals("mockGroup", loadedGroup.getGroupName(), "The group name should match");
    }

    @Test
    @DisplayName("Mocking test to check for equality in loadAllGroup.")
    void testLoadAllGroupsWithMockEquals() {
        List<Group> loadedGroups = getListOfMockGroups("mockGroup1", "mockGroup2");
        assertEquals(2, loadedGroups.size(), "Two groups should be loaded");
    }

    @Test
    @DisplayName("Mocking test to check for specific group in loadAllGroup.")
    void testLoadAllGroupsWithMockTrue() {
        List<Group> loadedGroups = getListOfMockGroups("mockGroup1", "mockGroup2");
        assertTrue(
                loadedGroups.stream().anyMatch(group -> "mockGroup1".equals(group.getGroupName())),
                "The list should contain mockGroup1"
        );
        assertTrue(
                loadedGroups.stream().anyMatch(group -> "mockGroup2".equals(group.getGroupName())),
                "The list should contain mockGroup2"
        );
    }

    @Test
    @DisplayName("Check the existence of a group in saveGroupToFile.")
    void testSaveGroupToFileExists() {
        Group group = createMockGroup("realGroup");
        realFileService.saveGroupToFile(group);
        assertFileExists();
    }

    @Test
    @DisplayName("Check for null in saveGroupToFile.")
    void testLoadGroupNotNull() {
        Group group = createMockGroup("realGroup");
        realFileService.saveGroupToFile(group);
        Group loadedGroup = realFileService.loadGroup("realGroup");
        assertNotNull(loadedGroup, "The group should not be null");
    }

    @Test
    @DisplayName("Check for equality in loadGroup.")
    void testLoadGroupWithEquals() {
        Group group = createMockGroup("realGroup");
        realFileService.saveGroupToFile(group);
        Group loadedGroup = realFileService.loadGroup("realGroup");
        assertEquals("realGroup", loadedGroup.getGroupName(), "The group name should match");
    }

    @Test
    @DisplayName("Check for equality in loadAllGroups.")
    void testLoadAllGroupsEquals() {
        Group group1 = createMockGroup("realGroup1");
        Group group2 = createMockGroup("realGroup2");
        saveGroupsToFile(group1, group2);
        List<Group> loadedGroups = realFileService.loadAllGroups();
        assertEquals(2, loadedGroups.size(), "Two groups should be loaded");
    }

    @Test
    @DisplayName("Check for the group existence in loadAllGroups.")
    void testLoadAllGroups() {
        Group group1 = createMockGroup("realGroup1");
        Group group2 = createMockGroup("realGroup2");
        saveGroupsToFile(group1, group2);
        List<Group> loadedGroups = realFileService.loadAllGroups();
        assertGroupExists(loadedGroups, "realGroup1");
        assertGroupExists(loadedGroups, "realGroup2");
    }

    @Test
    @DisplayName("Check if empty in loadAllGroups.")
    void testLoadAllGroupsEmpty() {
        List<Group> loadedGroups = realFileService.loadAllGroups();
        assertTrue(loadedGroups.isEmpty(), "No groups should be loaded if none are saved");
    }
    @Test
    @DisplayName("Check if the directory is incorrect.")
    void testCreateDirectoryIfNotExists() {
        String directory = "invalidDirectory";

        try (MockedStatic<Files> mockedFiles = mockStatic(Files.class)) {
            mockedFiles.when(() -> Files.createDirectories(Paths.get(directory)))
                    .thenThrow(new IOException("Mocked IOException"));

            RuntimeException exception = assertThrows(RuntimeException.class, () -> {
                createDirectoryIfNotExists(directory);
            });

            assert exception.getMessage().contains("Failed to create directory: " + directory);
        }
    }
}