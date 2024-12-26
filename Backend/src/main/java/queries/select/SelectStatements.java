package queries.select;

import interfaces.ITables;

public class SelectStatements {
    public static String selectAllStudentsSql() {
        return "SELECT * FROM "+ ITables.STUDENT_TABLE;
    }
}
