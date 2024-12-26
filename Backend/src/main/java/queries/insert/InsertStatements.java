package queries.insert;

import interfaces.ITables;

public class InsertStatements {
    public static String insertStudentSql() {
        return "INSERT INTO "+ ITables.STUDENT_TABLE + "(id, username, average_grade_per_subject, average_grade_overall) VALUES (?, ?, ?, ?)";
    }
}
