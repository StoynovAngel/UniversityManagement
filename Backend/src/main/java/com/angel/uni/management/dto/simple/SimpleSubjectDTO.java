package com.angel.uni.management.dto.simple;

import com.angel.uni.management.interfaces.SimpleDTO;

public record SimpleSubjectDTO(
        String name,
        int hours_per_week,
        String teacherName,
        String description
) implements SimpleDTO { }
