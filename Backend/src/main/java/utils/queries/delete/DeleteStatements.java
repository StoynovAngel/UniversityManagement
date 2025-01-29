package utils.queries.delete;

public class DeleteStatements {
    public static String deleteSubjectById() {
        return "DELETE FROM public.subject WHERE id = ?";
    }
}
