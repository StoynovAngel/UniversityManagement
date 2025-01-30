package dto;

import java.util.List;

public record StudentDTO(String username, List<GradeDTO> grades, double averageGradeOverall) {
}
