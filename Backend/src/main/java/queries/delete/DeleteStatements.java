package queries.delete;

import interfaces.ITables;

public class DeleteStatements {
    public static String deleteStudentSql() {
        return "DELETE FROM "+ ITables.STUDENT_TABLE + " WHERE student.id = ?";
    }
}
