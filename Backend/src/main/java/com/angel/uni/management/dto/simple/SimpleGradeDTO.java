package com.angel.uni.management.dto.simple;

import com.angel.uni.management.interfaces.SimpleDTO;

public record SimpleGradeDTO(
        String name,
        double mark,
        String teacherName,
        String studentUsername,
        String gradeType
) implements SimpleDTO { }
