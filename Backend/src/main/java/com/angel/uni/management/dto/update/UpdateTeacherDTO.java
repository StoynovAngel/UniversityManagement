package com.angel.uni.management.dto.update;

import com.angel.uni.management.interfaces.SimpleDTO;

public record UpdateTeacherDTO(
        String name,
        Long id
) implements SimpleDTO { }
