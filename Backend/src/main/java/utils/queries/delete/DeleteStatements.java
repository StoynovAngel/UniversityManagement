package utils.queries.delete;

import utils.mappers.TableMapperConstants;

public class DeleteStatements {
    public static String deleteSubjectById() {
        return "DELETE FROM " + TableMapperConstants.SUBJECT_TABLE + " WHERE id = ?";
    }
}
