package com.angel.uni.management.utils.queries.update;

import com.angel.uni.management.utils.mappers.TableMapperConstants;

/**
 *  Contains all updateQueries.
 *  This class uses private constructor to prevent initialization.
 */

public class UpdateStatements {

    private UpdateStatements() {
        throw new UnsupportedOperationException("Should not instantiate " + getClass().getSimpleName());
    }

    public static String updateTeacherNameById() {
        return "UPDATE " + TableMapperConstants.TEACHER_TABLE + " SET name = ? WHERE id = ?";
    }

    public static String updateSubjectDescriptionBySubjectName() {
        return "UPDATE " + TableMapperConstants.SUBJECT_TABLE + " SET description = ? WHERE name = ?";
    }

    public static String updateGradeByStudentId() {
        return "UPDATE " + TableMapperConstants.GRADE_TABLE + " SET mark = ? WHERE student_id = ?";
    }

    public static String updateStudentUsernameById() {
        return "UPDATE " + TableMapperConstants.STUDENT_TABLE + " SET username = ? WHERE id = ?";
    }

    public static String updateGroupNameById() {
        return "UPDATE " + TableMapperConstants.GROUP_TABLE + " SET name = ? WHERE id = ?";
    }
}
