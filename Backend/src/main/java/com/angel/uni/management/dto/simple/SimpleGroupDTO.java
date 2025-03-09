package com.angel.uni.management.dto.simple;

import com.angel.uni.management.interfaces.SimpleDTO;

public record SimpleGroupDTO(
        String groupName
) implements SimpleDTO { }
