import config.DatabaseConnection;
import entity.*;
import org.junit.jupiter.api.*;
import utils.mappers.*;
import utils.queries.select.SelectQuery;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SelectQueryTest {

    private static SelectQuery selectQuery;
    private static Connection connection;

    @BeforeAll
    static void setUp() {
        connection = DatabaseConnection.getConnection();
        selectQuery = new SelectQuery();
    }

    @AfterAll
    static void closeDatabaseConnection() throws SQLException {
        connection.close();
        System.out.println("Database connection closed.");
    }

    @Test
    void getGroupById() {
        Long groupId = 3L;
        Group result = selectQuery.getGroupById(groupId, Mappers.getGroupMapper());
        assertNotNull(result, "Group should not be null");
        assertEquals(groupId, result.getId(), "Group ID does not match");
    }

    @Test
    void getSubjectById() {
        Long subjectId = 1L;
        Subject result = selectQuery.getSubjectById(subjectId, Mappers.getSubjectMapper());
        assertNotNull(result, "Subject should not be null");
        assertEquals(subjectId, result.getId(), "Subject id does not match");
    }

    @Test
    void getSubjectByName() {
        String name = "Math";
        Subject result = selectQuery.getSubjectByName(name, Mappers.getSubjectMapper());
        assertNotNull(result, "Subject should not be null");
        assertEquals(name, result.getName(), "Subject name does not match");
    }

    @Test
    void getTeacherById() {
        Long subjectId = 1L;
        Teacher result = selectQuery.getTeacherById(subjectId, Mappers.getTeacherMapper());
        assertNotNull(result, "Teacher should not be null");
        assertEquals(subjectId, result.getId(), "Teacher id does not match");
    }

    @Test
    void getStudentById() {
        Long subjectId = 1L;
        Student result = selectQuery.getStudentById(subjectId, Mappers.getStudentMapper());
        assertNotNull(result, "Student should not be null");
        assertEquals(subjectId, result.getId(), "Student id does not match");
    }

    @Test
    void getStudentByUsername() {
        String name = "jane_doe";
        Student result = selectQuery.getStudentByUsername(name, Mappers.getStudentMapper());
        assertNotNull(result, "Student should not be null");
        assertEquals(name, result.getUsername(), "Student username does not match");
    }

    @Test
    void getGradeByName() {
        String name = "Math";
        Grade result = selectQuery.getGradeByName(name, Mappers.getGradeMapper());
        assertNotNull(result, "Grade should not be null");
        assertEquals(name, result.getName(), "Grade name does not match");
    }

    @Test
    void getGradesByStudentName() {
        String studentName = "jane_doe";
        List<Grade> result = selectQuery.getGradesByStudentName(studentName, Mappers.getGradeMapper());
        assertNotNull(result, "Grade list should not be null");
        assertFalse(result.isEmpty(), "Grade list should not be empty for student: " + studentName);

        for (Grade grade : result) {
            assertNotNull(grade.getStudent(), "Grade should have an associated student");
            assertEquals(studentName, grade.getStudent().getUsername(), "Student's name does not match");
        }
    }
}