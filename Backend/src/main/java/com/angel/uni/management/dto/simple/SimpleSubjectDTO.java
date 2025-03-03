package com.angel.uni.management.dto.simple;

public record SimpleSubjectDTO(
        String name,
        int hours_per_week,
        String teacherName,
        String description
) { }
