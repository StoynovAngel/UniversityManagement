package utils.queries.select;

public class SelectStatements {
    public static String selectTeacherByIdSql() {
        return "SELECT teacher.id AS teacher_id, teacher.name AS teacher_name " +
                "FROM public.teacher " +
                "WHERE teacher.id = ?";
    }

    public static String selectStudentByIdSql() {
        return "SELECT student.id, student.username, student.average_grade_per_subject, student.average_grade_overall, " +
                "grade.id AS grade_id, " +
                "grade.name AS grade_name, " +
                "grade.mark AS grade_mark, " +
                "grade.grade_type, " +
                "grade.date_of_grading " +
                "FROM public.student JOIN grade ON grade.student_id = student.id WHERE student.id = ?";
    }

    public static String selectGradeByGradeNameSql() {
        return "SELECT " +
                "grade.id AS grade_id, grade.name, grade.mark, grade.date_of_grading, grade.grade_type, " +
                "teacher.id AS teacher_id, teacher.name AS teacher_name, " +
                "student.id AS student_id, student.username AS student_username " +
                "FROM public.grade " +
                "JOIN teacher ON teacher.id = grade.teacher_id " +
                "JOIN student ON student.id = grade.student_id " +
                "WHERE grade.name = ?";
    }

    public static String selectGradesByStudentNameSql() {
        return "SELECT " +
                "grade.id AS grade_id, grade.name, grade.mark, grade.date_of_grading, grade.grade_type, " +
                "teacher.id AS teacher_id, teacher.name AS teacher_name, " +
                "student.id AS student_id, student.username AS student_username " +
                "FROM public.grade " +
                "JOIN teacher ON teacher.id = grade.teacher_id " +
                "JOIN student ON student.id = grade.student_id " +
                "WHERE student.username = ?";
    }

    public static String selectSubjectByIdSql() {
        return "SELECT " +
                "subject.id AS subject_id, " +
                "subject.name AS subject_name, " +
                "subject.hours_per_week, " +
                "subject.description, " +
                "teacher.id AS teacher_id, " +
                "teacher.name AS teacher_name, " +
                "student.id AS student_id, " +
                "student.username AS student_username, " +
                "student.average_grade_per_subject, " +
                "student.average_grade_overall, " +
                "grade.id AS grade_id, " +
                "grade.name AS grade_name, " +
                "grade.mark AS grade_mark, " +
                "grade.grade_type, " +
                "grade.date_of_grading " +
                "FROM public.subject " +
                "LEFT JOIN teacher ON teacher.id = subject.teacher_id " +
                "LEFT JOIN subject_student ON subject.id = subject_student.subject_id " +
                "LEFT JOIN student ON student.id = subject_student.student_id " +
                "LEFT JOIN grade ON grade.student_id = student.id " +
                "WHERE subject.id = ? AND subject.name = grade.name";
    }

    public static String selectSubjectByNameSql() {
        return "SELECT " +
                "subject.id AS subject_id, " +
                "subject.name AS subject_name, " +
                "subject.hours_per_week, " +
                "subject.description, " +
                "teacher.id AS teacher_id, " +
                "teacher.name AS teacher_name, " +
                "student.id AS student_id, " +
                "student.username AS student_username, " +
                "student.average_grade_per_subject, " +
                "student.average_grade_overall, " +
                "grade.id AS grade_id, " +
                "grade.name AS grade_name, " +
                "grade.mark AS grade_mark, " +
                "grade.grade_type, " +
                "grade.date_of_grading " +
                "FROM public.subject " +
                "LEFT JOIN teacher ON teacher.id = subject.teacher_id " +
                "LEFT JOIN subject_student ON subject.id = subject_student.subject_id " +
                "LEFT JOIN student ON student.id = subject_student.student_id " +
                "LEFT JOIN grade ON grade.student_id = student.id " +
                "WHERE subject.name = ? AND subject.name = grade.name";
    }
}