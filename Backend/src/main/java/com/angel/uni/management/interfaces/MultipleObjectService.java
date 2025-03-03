package com.angel.uni.management.interfaces;

import java.util.List;
import java.util.Optional;

public interface MultipleObjectService<T> {
    Optional<List<T>> readMultiple(String name);
}
