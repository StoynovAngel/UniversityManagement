package dto;

import java.util.List;

public record SubjectDTO(String name, int hoursPerWeek, String description, TeacherDTO teacher, List<StudentDTO> students) {
}
