import services.StudentService;
import utils.gson.GsonFormatter;

public class Main {
    public static void main(String[] args) {
        StudentService service = new StudentService();
        GsonFormatter.printObjectToJson(service.getStudentById(1L));
    }
}
