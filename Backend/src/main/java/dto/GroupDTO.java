package dto;

import java.util.List;

public record GroupDTO(String groupName, List<StudentDTO> studentsAssignedToGroup) {
}
