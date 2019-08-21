package com.zendesk.dao;

import com.zendesk.model.Todo;

import java.util.List;
import java.util.Optional;

public interface Dao<T> {
    Optional<Todo> get(String id);

    List<T> getAll();

    void save(T t);

    void update(T t);

    void delete(T t);
}
