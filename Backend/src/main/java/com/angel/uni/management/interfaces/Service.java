package com.angel.uni.management.interfaces;

import java.util.Optional;

public interface Service<T, U, D> {
    void create(D dto);
    Optional<T> read(Long id);
    Optional<T> read(String name);
    void update(U dto);
    void delete(Long id);
}
