package utils.queries.update;

public class UpdateStatements {
    public static String updateTeacherNameById() {
        return "UPDATE public.teacher SET name = ? WHERE id = ?";
    }

    public static String updateSubjectDescriptionBySubjectName() {
        return "UPDATE public.subject SET description = ? WHERE name = ?";
    }

    public static String updateGradeByStudentId() {
        return "UPDATE public.grade SET mark = ? WHERE student_id = ?";
    }

    public static String updateStudentUsernameById() {
        return "UPDATE public.student SET username = ? WHERE id = ?";
    }

    public static String updateGradeNameById() {
        return "UPDATE public.university_group SET name = ? WHERE id = ?";
    }
}
