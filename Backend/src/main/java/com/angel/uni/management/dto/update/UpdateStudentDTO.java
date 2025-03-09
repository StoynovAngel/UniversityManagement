package com.angel.uni.management.dto.update;

import com.angel.uni.management.interfaces.SimpleDTO;

public record UpdateStudentDTO(
        String username,
        Long id
) implements SimpleDTO { }
