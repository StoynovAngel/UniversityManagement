package com.angel.uni.management.dto.update;

import com.angel.uni.management.interfaces.SimpleDTO;

public record UpdateGradeDTO(
        double mark,
        Long id
) implements SimpleDTO { }
