package utils.queries.insert;

public class InsertStatements {
    public static String insertTeacher() {
        return "INSERT INTO public.teacher(name) VALUES (?)";
    }

    public static String insertSubject() {
        return "INSERT INTO public.subject(name, hours_per_week, teacher_id, description) VALUES (?, ?, (SELECT id FROM public.teacher WHERE name = ? LIMIT 1), ?)";
    }

    public static String insertStudent() {
        return "INSERT INTO public.student(username)VALUES (?)";
    }

    public static String insertGroup() {
        return "INSERT INTO public.university_group(name) VALUES (?)";
    }

    public static String insertGrade() {
        return "INSERT INTO public.grade(name, mark, teacher_id, student_id, grade_type) " +
                "VALUES (?, ?, " +
                "(SELECT id FROM public.teacher WHERE name = ? LIMIT 1), " +
                "(SELECT id FROM public.student WHERE username = ? LIMIT 1), " +
                "?)";
    }

    public static String insertStudentIntoGroup() {
        return "INSERT INTO public.group_student(group_id, student_id) " +
                "VALUES (" +
                "(SELECT id FROM public.university_group WHERE name = ? LIMIT 1), " +
                "(SELECT id FROM public.student WHERE username = ? LIMIT 1)" +
                ")";
    }

    public static String insertStudentIntoSubject() {
        return "INSERT INTO public.subject_student(subject_id, student_id) VALUES " +
                "((SELECT id FROM public.subject WHERE name = ? LIMIT 1), " +
                "(SELECT id FROM public.student WHERE username = ? LIMIT 1))";
    }
}
