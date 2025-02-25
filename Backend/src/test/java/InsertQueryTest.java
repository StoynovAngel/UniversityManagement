import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.angel.uni.management.utils.queries.insert.InsertStatements;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class InsertQueryTest {
    private Connection mockConnection;

    @BeforeEach
    void setUp() throws SQLException {
        mockConnection = mock(Connection.class);
        PreparedStatement mockPreparedStatement = mock(PreparedStatement.class);
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
    }

    @Test
    void insertTeacher() throws SQLException {
        String sql = InsertStatements.insertTeacher();
        PreparedStatement preparedStatement = mockConnection.prepareStatement(sql);
        preparedStatement.setString(1, "John Doe");
        preparedStatement.executeUpdate();

        verify(mockConnection).prepareStatement(sql);
        verify(preparedStatement).setString(1, "John Doe");
        verify(preparedStatement).executeUpdate();
    }

    @Test
    void insertSubject() throws SQLException {
        String sql = InsertStatements.insertSubject();
        PreparedStatement preparedStatement = mockConnection.prepareStatement(sql);

        preparedStatement.setString(1, "Mathematics");
        preparedStatement.setInt(2, 5);
        preparedStatement.setString(3, "John Doe");
        preparedStatement.setString(4, "Basic Math Course");

        preparedStatement.executeUpdate();

        verify(mockConnection).prepareStatement(sql);
        verify(preparedStatement).setString(1, "Mathematics");
        verify(preparedStatement).setInt(2, 5);
        verify(preparedStatement).setString(3, "John Doe");
        verify(preparedStatement).setString(4, "Basic Math Course");
        verify(preparedStatement).executeUpdate();
    }

    @Test
    void insertStudent() throws SQLException {
        String sql = InsertStatements.insertStudent();
        PreparedStatement preparedStatement = mockConnection.prepareStatement(sql);

        preparedStatement.setString(1, "student123");
        preparedStatement.executeUpdate();

        verify(mockConnection).prepareStatement(sql);
        verify(preparedStatement).setString(1, "student123");
        verify(preparedStatement).executeUpdate();
    }

    @Test
    void insertGroup() throws SQLException {
        String sql = InsertStatements.insertGroup();
        PreparedStatement preparedStatement = mockConnection.prepareStatement(sql);

        preparedStatement.setString(1, "Group A");
        preparedStatement.executeUpdate();

        verify(mockConnection).prepareStatement(sql);
        verify(preparedStatement).setString(1, "Group A");
        verify(preparedStatement).executeUpdate();
    }

    @Test
    void insertGrade() throws SQLException {
        String sql = InsertStatements.insertGrade();
        PreparedStatement preparedStatement = mockConnection.prepareStatement(sql);

        preparedStatement.setString(1, "Math Exam");
        preparedStatement.setDouble(2, 95.5);
        preparedStatement.setString(3, "John Doe");
        preparedStatement.setString(4, "student123");
        preparedStatement.setString(5, "FINAL");

        preparedStatement.executeUpdate();

        verify(mockConnection).prepareStatement(sql);
        verify(preparedStatement).setString(1, "Math Exam");
        verify(preparedStatement).setDouble(2, 95.5);
        verify(preparedStatement).setString(3, "John Doe");
        verify(preparedStatement).setString(4, "student123");
        verify(preparedStatement).setString(5, "FINAL");
        verify(preparedStatement).executeUpdate();
    }

    @Test
    void insertStudentIntoGroup() throws SQLException {
        String sql = InsertStatements.insertStudentIntoGroup();
        PreparedStatement preparedStatement = mockConnection.prepareStatement(sql);

        preparedStatement.setString(1, "Group A");
        preparedStatement.setString(2, "student123");

        preparedStatement.executeUpdate();

        verify(mockConnection).prepareStatement(sql);
        verify(preparedStatement).setString(1, "Group A");
        verify(preparedStatement).setString(2, "student123");
        verify(preparedStatement).executeUpdate();
    }

    @Test
    void insertStudentIntoSubject() throws SQLException {
        String sql = InsertStatements.insertStudentIntoSubject();
        PreparedStatement preparedStatement = mockConnection.prepareStatement(sql);

        preparedStatement.setString(1, "Mathematics");
        preparedStatement.setString(2, "student123");

        preparedStatement.executeUpdate();

        verify(mockConnection).prepareStatement(sql);
        verify(preparedStatement).setString(1, "Mathematics");
        verify(preparedStatement).setString(2, "student123");
        verify(preparedStatement).executeUpdate();
    }
}