package com.angel.uni.management.utils.queries.select;

import com.angel.uni.management.utils.mappers.TableMapperConstants;

/**
 * Contains all selectQueries.
 * This class uses private constructor to prevent initialization.
 */

public class SelectStatements {

    private SelectStatements() {
        throw new UnsupportedOperationException("Should not instantiate " + getClass().getSimpleName());
    }

    public static String selectGroupByIdSql() {
        return "SELECT university_group.id AS group_id, university_group.name AS group_name, " +
                "student.id AS " + TableMapperConstants.STUDENT_ID + ", " +
                "student.username AS " + TableMapperConstants.STUDENT_USERNAME + ", student.average_grade_overall, " +
                "grade.id AS " + TableMapperConstants.GRADE_ID + ", " +
                "grade.name AS " + TableMapperConstants.GRADE_NAME + ", " +
                "grade.mark AS " + TableMapperConstants.GRADE_MARK + ", " +
                "grade.grade_type, " +
                "grade.date_of_grading " +
                "FROM " + TableMapperConstants.GROUP_TABLE + " " +
                "LEFT JOIN group_student ON university_group.id = group_student.group_id " +
                "LEFT JOIN student ON student.id = group_student.student_id " +
                "LEFT JOIN grade ON grade.student_id = student.id " +
                "WHERE university_group.id = ? " +
                "ORDER BY student.id, grade.id";
    }

    public static String selectGroupByNameSql() {
        return "SELECT university_group.id AS group_id, university_group.name AS group_name, " +
                "student.id AS " + TableMapperConstants.STUDENT_ID + ", " +
                "student.username AS " + TableMapperConstants.STUDENT_USERNAME + ", student.average_grade_overall, " +
                "grade.id AS " + TableMapperConstants.GRADE_ID + ", " +
                "grade.name AS " + TableMapperConstants.GRADE_NAME + ", " +
                "grade.mark AS " + TableMapperConstants.GRADE_MARK + ", " +
                "grade.grade_type, " +
                "grade.date_of_grading " +
                "FROM " + TableMapperConstants.GROUP_TABLE + " " +
                "LEFT JOIN group_student ON university_group.id = group_student.group_id " +
                "LEFT JOIN student ON student.id = group_student.student_id " +
                "LEFT JOIN grade ON grade.student_id = student.id " +
                "WHERE university_group.name = ? " +
                "ORDER BY student.id, grade.id";
    }

    public static String selectTeacherByIdSql() {
        return "SELECT teacher.id AS " + TableMapperConstants.TEACHER_ID + ", " +
                "teacher.name AS " + TableMapperConstants.TEACHER_NAME + " " +
                "FROM " + TableMapperConstants.TEACHER_TABLE + " " +
                "WHERE teacher.id = ?";
    }

    public static String selectTeacherByNameSql() {
        return "SELECT teacher.id AS " + TableMapperConstants.TEACHER_ID + ", " +
                "teacher.name AS " + TableMapperConstants.TEACHER_NAME + " " +
                "FROM " + TableMapperConstants.TEACHER_TABLE + " " +
                "WHERE teacher.name = ?";
    }

    public static String selectStudentByIdSql() {
        return "SELECT student.id AS " + TableMapperConstants.STUDENT_ID + ", " +
                "student.username AS " + TableMapperConstants.STUDENT_USERNAME + ", " +
                "student.average_grade_overall, " +
                "grade.id AS " + TableMapperConstants.GRADE_ID + ", " +
                "grade.name AS " + TableMapperConstants.GRADE_NAME + ", " +
                "grade.mark AS " + TableMapperConstants.GRADE_MARK + ", " +
                "grade.grade_type, " +
                "grade.date_of_grading " +
                "FROM " + TableMapperConstants.STUDENT_TABLE + " " +
                "LEFT JOIN grade ON grade.student_id = student.id " +
                "WHERE student.id = ?";
    }

    public static String selectStudentByUsernameSql() {
        return "SELECT student.id AS " + TableMapperConstants.STUDENT_ID + ", " +
                "student.username AS " + TableMapperConstants.STUDENT_USERNAME + ", " +
                "student.average_grade_overall, " +
                "grade.id AS " + TableMapperConstants.GRADE_ID + ", " +
                "grade.name AS " + TableMapperConstants.GRADE_NAME + ", " +
                "grade.mark AS " + TableMapperConstants.GRADE_MARK + ", " +
                "grade.grade_type, " +
                "grade.date_of_grading " +
                "FROM " + TableMapperConstants.STUDENT_TABLE + " " +
                "LEFT JOIN grade ON grade.student_id = student.id " +
                "WHERE student.username = ?";
    }

    public static String selectGradeByGradeIdSql() {
        return "SELECT " +
                "grade.id AS " + TableMapperConstants.GRADE_ID + ", " +
                "grade.name AS " + TableMapperConstants.GRADE_NAME + ", " +
                "grade.mark AS " + TableMapperConstants.GRADE_MARK + ", " +
                "grade.date_of_grading, grade.grade_type, " +
                "teacher.id AS " + TableMapperConstants.TEACHER_ID + ", " +
                "teacher.name AS " + TableMapperConstants.TEACHER_NAME + ", " +
                "student.id AS " + TableMapperConstants.STUDENT_ID + ", " +
                "student.username AS " + TableMapperConstants.STUDENT_USERNAME + " " +
                "FROM " + TableMapperConstants.GRADE_TABLE + " " +
                "JOIN teacher ON teacher.id = grade.teacher_id " +
                "JOIN student ON student.id = grade.student_id " +
                "WHERE grade.id = ?";
    }

    public static String selectGradeByGradeNameSql() {
        return "SELECT " +
                "grade.id AS " + TableMapperConstants.GRADE_ID + ", " +
                "grade.name AS " + TableMapperConstants.GRADE_NAME + ", " +
                "grade.mark AS " + TableMapperConstants.GRADE_MARK + ", " +
                "grade.date_of_grading, grade.grade_type, " +
                "teacher.id AS " + TableMapperConstants.TEACHER_ID + ", " +
                "teacher.name AS " + TableMapperConstants.TEACHER_NAME + ", " +
                "student.id AS " + TableMapperConstants.STUDENT_ID + ", " +
                "student.username AS " + TableMapperConstants.STUDENT_USERNAME + " " +
                "FROM " + TableMapperConstants.GRADE_TABLE + " " +
                "JOIN teacher ON teacher.id = grade.teacher_id " +
                "JOIN student ON student.id = grade.student_id " +
                "WHERE grade.name = ?";
    }

    public static String selectGradesByStudentNameSql() {
        return "SELECT " +
                "grade.id AS " + TableMapperConstants.GRADE_ID + ", " +
                "grade.name AS " + TableMapperConstants.GRADE_NAME + ", " +
                "grade.mark AS " + TableMapperConstants.GRADE_MARK + ", " +
                "grade.date_of_grading, grade.grade_type, " +
                "teacher.id AS " + TableMapperConstants.TEACHER_ID + ", " +
                "teacher.name AS " + TableMapperConstants.TEACHER_NAME + ", " +
                "student.id AS " + TableMapperConstants.STUDENT_ID + ", " +
                "student.username AS " + TableMapperConstants.STUDENT_USERNAME + " " +
                "FROM " + TableMapperConstants.GRADE_TABLE + " " +
                "JOIN teacher ON teacher.id = grade.teacher_id " +
                "JOIN student ON student.id = grade.student_id " +
                "WHERE student.username = ?";
    }

    public static String selectSubjectByIdSql() {
        return "SELECT " +
                "subject.id AS " + TableMapperConstants.SUBJECT_ID + ", " +
                "subject.name AS " + TableMapperConstants.SUBJECT_NAME + ", " +
                "subject.hours_per_week, " +
                "subject.description, " +
                "teacher.id AS " + TableMapperConstants.TEACHER_ID + ", " +
                "teacher.name AS " + TableMapperConstants.TEACHER_NAME + ", " +
                "student.id AS " + TableMapperConstants.STUDENT_ID + ", " +
                "student.username AS " + TableMapperConstants.STUDENT_USERNAME + ", " +
                "student.average_grade_overall, " +
                "grade.id AS " + TableMapperConstants.GRADE_ID + ", " +
                "grade.name AS " + TableMapperConstants.GRADE_NAME + ", " +
                "grade.mark AS " + TableMapperConstants.GRADE_MARK + ", " +
                "grade.grade_type, " +
                "grade.date_of_grading " +
                "FROM " + TableMapperConstants.SUBJECT_TABLE + " " +
                "LEFT JOIN teacher ON teacher.id = subject.teacher_id " +
                "LEFT JOIN subject_student ON subject.id = subject_student.subject_id " +
                "LEFT JOIN student ON student.id = subject_student.student_id " +
                "LEFT JOIN grade ON grade.student_id = student.id " +
                "WHERE subject.id = ?";
    }

    public static String selectSubjectByNameSql() {
        return "SELECT " +
                "subject.id AS " + TableMapperConstants.SUBJECT_ID + ", " +
                "subject.name AS " + TableMapperConstants.SUBJECT_NAME + ", " +
                "subject.hours_per_week, " +
                "subject.description, " +
                "teacher.id AS " + TableMapperConstants.TEACHER_ID + ", " +
                "teacher.name AS " + TableMapperConstants.TEACHER_NAME + ", " +
                "student.id AS " + TableMapperConstants.STUDENT_ID + ", " +
                "student.username AS " + TableMapperConstants.STUDENT_USERNAME + ", " +
                "student.average_grade_overall, " +
                "grade.id AS " + TableMapperConstants.GRADE_ID + ", " +
                "grade.name AS " + TableMapperConstants.GRADE_NAME + ", " +
                "grade.mark AS " + TableMapperConstants.GRADE_MARK + ", " +
                "grade.grade_type, " +
                "grade.date_of_grading " +
                "FROM " + TableMapperConstants.SUBJECT_TABLE + " " +
                "LEFT JOIN teacher ON teacher.id = subject.teacher_id " +
                "LEFT JOIN subject_student ON subject.id = subject_student.subject_id " +
                "LEFT JOIN student ON student.id = subject_student.student_id " +
                "LEFT JOIN grade ON grade.student_id = student.id " +
                "WHERE subject.name = ?";
    }
}