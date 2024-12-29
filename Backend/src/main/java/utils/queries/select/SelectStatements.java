package utils.queries.select;

import interfaces.ITables;

public class SelectStatements {
    public static String selectSubjectByIdSql() {
        return "SELECT * FROM " + ITables.SUBJECT_TABLE + " WHERE id = ?";
    }
}
