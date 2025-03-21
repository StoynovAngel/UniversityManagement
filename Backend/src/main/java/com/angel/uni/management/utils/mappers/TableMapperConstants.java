package com.angel.uni.management.utils.mappers;


/**
 * The {@code TableMapperConstants} contains all constants used for mapping and queries.
 * This class prevents instantiation by using private constructor.
 */

public class TableMapperConstants {
    public static final String STUDENT_TABLE = "public.student";
    public static final String STUDENT_ID = "student_id";
    public static final String STUDENT_USERNAME = "student_username";
    public static final String STUDENT_AVERAGE_GRADE_OVERALL = "average_grade_overall";

    public static final String GRADE_TABLE = "public.grade";
    public static final String GRADE_ID = "grade_id";
    public static final String GRADE_NAME = "grade_name";
    public static final String GRADE_TYPE = "grade_type";
    public static final String GRADE_MARK = "grade_mark";
    public static final String GRADE_DATE_OF_GRADING = "date_of_grading";

    public static final String GROUP_TABLE = "public.university_group";
    public static final String GROUP_ID = "group_id";
    public static final String GROUP_NAME = "group_name";

    public static final String TEACHER_TABLE = "public.teacher";
    public static final String TEACHER_ID = "teacher_id";
    public static final String TEACHER_NAME = "teacher_name";

    public static final String SUBJECT_TABLE = "public.subject";
    public static final String SUBJECT_ID = "subject_id";
    public static final String SUBJECT_NAME = "subject_name";
    public static final String SUBJECT_HOURS_PER_WEEK = "hours_per_week";
    public static final String SUBJECT_DESCRIPTION = "description";

    public static final String GROUP_STUDENT_TABLE = "group_student";
    public static final String SUBJECT_STUDENT_TABLE = "subject_student";

    private TableMapperConstants() {
        throw new UnsupportedOperationException("Should not instantiate " + getClass().getSimpleName());
    }
}
