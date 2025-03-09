package com.angel.uni.management.dto.update;

import com.angel.uni.management.interfaces.SimpleDTO;

public record UpdateSubjectDTO(
        String description, String name
) implements SimpleDTO { }