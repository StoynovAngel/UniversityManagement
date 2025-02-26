import com.angel.uni.management.config.DatabaseConnection;
import com.angel.uni.management.entity.*;
import com.angel.uni.management.utils.queries.delete.DeleteQuery;
import com.angel.uni.management.utils.queries.insert.InsertQuery;
import com.angel.uni.management.utils.queries.select.SelectQuery;
import com.angel.uni.management.utils.queries.update.UpdateQuery;
import org.junit.jupiter.api.*;
import com.angel.uni.management.services.QueryManagerImpl;
import com.angel.uni.management.utils.mappers.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;


class SelectQueryTest {
    private static Connection connection;
    private QueryManagerImpl queryManagerImpl;

    @BeforeAll
    static void setUp() throws SQLException {
        connection = DatabaseConnection.getConnection();
    }

    @AfterAll
    static void tearDown() throws SQLException {
        // Close the database connection
        if (connection != null && !connection.isClosed()) {
            connection.close();
            System.out.println("Database connection closed.");
        }
    }

    @BeforeEach
    void init() {
        queryManagerImpl = new QueryManagerImpl(new SelectQuery(), new UpdateQuery(), new DeleteQuery(), new InsertQuery());
    }

    @Test
    void getGroupById() {
        Long groupId = 3L;
        Group result = queryManagerImpl.selectQuery().getGroupById(groupId, Mappers.getGroupMapper());
        assertNotNull(result, "Group should not be null");
        assertEquals(groupId, result.getId(), "Group ID does not match");
    }

    @Test
    void getSubjectById() {
        Long subjectId = 1L;
        Subject result = queryManagerImpl.selectQuery().getSubjectById(subjectId, Mappers.getSubjectMapper());
        assertNotNull(result, "Subject should not be null");
        assertEquals(subjectId, result.getId(), "Subject ID does not match");
    }

    @Test
    void getSubjectByName() {
        String name = "Math";
        Subject result = queryManagerImpl.selectQuery().getSubjectByName(name, Mappers.getSubjectMapper());
        assertNotNull(result, "Subject should not be null");
        assertEquals(name, result.getName(), "Subject name does not match");
    }

    @Test
    void getTeacherById() {
        Long teacherId = 1L;
        Teacher result = queryManagerImpl.selectQuery().getTeacherById(teacherId, Mappers.getTeacherMapper());
        assertNotNull(result, "Teacher should not be null");
        assertEquals(teacherId, result.getId(), "Teacher ID does not match");
    }

    @Test
    void getStudentById() {
        Long studentId = 1L;
        Student result = queryManagerImpl.selectQuery().getStudentById(studentId, Mappers.getStudentMapper());
        assertNotNull(result, "Student should not be null");
        assertEquals(studentId, result.getId(), "Student ID does not match");
    }

    @Test
    void getStudentByUsername() {
        String username = "jane_doe";
        Student result = queryManagerImpl.selectQuery().getStudentByUsername(username, Mappers.getStudentMapper());
        assertNotNull(result, "Student should not be null");
        assertEquals(username, result.getUsername(), "Student username does not match");
    }

    @Test
    void getGradeByName() {
        String name = "Math";
        Grade result = queryManagerImpl.selectQuery().getGradeByName(name, Mappers.getGradeMapper());
        assertNotNull(result, "Grade should not be null");
        assertEquals(name, result.getName(), "Grade name does not match");
    }

    @Test
    void getGradesByStudentName() {
        String studentName = "jane_doe";
        List<Grade> result = queryManagerImpl.selectQuery().getGradesByStudentName(studentName, Mappers.getGradeMapper());
        assertNotNull(result, "Grade list should not be null");
        assertFalse(result.isEmpty(), "Grade list should not be empty for student: " + studentName);

        for (Grade grade : result) {
            assertNotNull(grade.getStudent(), "Grade should have an associated student");
            assertEquals(studentName, grade.getStudent().getUsername(), "Student's name does not match");
        }
    }
}