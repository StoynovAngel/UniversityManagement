package utils.queries.insert;

import utils.mappers.TableMapperConstants;

public class InsertStatements {
    public static String insertTeacher() {
        return "INSERT INTO " + TableMapperConstants.TEACHER_TABLE + "(name) VALUES (?)";
    }

    public static String insertSubject() {
        return "INSERT INTO " + TableMapperConstants.SUBJECT_TABLE + "(name, hours_per_week, teacher_id, description) VALUES (?, ?, (SELECT id FROM public.teacher WHERE name = ? LIMIT 1), ?)";
    }

    public static String insertStudent() {
        return "INSERT INTO " + TableMapperConstants.STUDENT_TABLE + "(username) VALUES (?)";
    }

    public static String insertGroup() {
        return "INSERT INTO " + TableMapperConstants.GROUP_TABLE + "(name) VALUES (?)";
    }

    public static String insertGrade() {
        return "INSERT INTO " + TableMapperConstants.GRADE_TABLE + "(name, mark, teacher_id, student_id, grade_type) " +
                "VALUES (?, ?, " +
                "(SELECT id FROM public.teacher WHERE name = ? LIMIT 1), " +
                "(SELECT id FROM public.student WHERE username = ? LIMIT 1), " +
                "?)";
    }

    public static String insertStudentIntoGroup() {
        return "INSERT INTO " + TableMapperConstants.GROUP_STUDENT_TABLE + "(group_id, student_id) " +
                "VALUES (" +
                "(SELECT id FROM public.university_group WHERE name = ? LIMIT 1), " +
                "(SELECT id FROM public.student WHERE username = ? LIMIT 1)" +
                ")";
    }

    public static String insertStudentIntoSubject() {
        return "INSERT INTO " + TableMapperConstants.SUBJECT_STUDENT_TABLE + "(subject_id, student_id) VALUES " +
                "((SELECT id FROM public.subject WHERE name = ? LIMIT 1), " +
                "(SELECT id FROM public.student WHERE username = ? LIMIT 1))";
    }
}
