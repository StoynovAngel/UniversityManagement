package com.angel.uni.management.dto.simple;

import com.angel.uni.management.interfaces.SimpleDTO;

public record SimpleStudentDTO(
        String username
) implements SimpleDTO { }
