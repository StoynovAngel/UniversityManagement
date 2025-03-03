package com.angel.uni.management.dto.simple;

public record SimpleGradeDTO(
        String name,
        double mark,
        String teacherName,
        String studentUsername,
        String gradeType) {
}
