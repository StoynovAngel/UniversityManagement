package com.angel.uni.management.utils.queries.delete;

import com.angel.uni.management.utils.mappers.TableMapperConstants;

/**
 *  Contains all deleteQueries.
 *  This class uses private constructor to prevent initialization.
 */

public class DeleteStatements {

    private DeleteStatements() {
        throw new UnsupportedOperationException("Should not instantiate " + getClass().getSimpleName());
    }

    public static String deleteSubjectById() {
        return "DELETE FROM " + TableMapperConstants.SUBJECT_TABLE + " WHERE id = ?";
    }
}
