package utils.queries.insert;

import interfaces.ITables;

public class InsertStatements {
    public static String insertStudentSql() {
        return "INSERT INTO " + ITables.STUDENT_TABLE + "(id, username, average_grade_per_subject, average_grade_overall) VALUES (?, ?, ?, ?)";
    }

    public static String insertSubjectSql() {
        return "INSERT INTO " + ITables.SUBJECT_TABLE + "(id, name, hours_per_week, description) VALUES (?, ?, ?, ?)";
    }
}
