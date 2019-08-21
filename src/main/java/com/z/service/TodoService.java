package com.z.service;

import com.z.model.Todo;

import java.util.List;

public interface TodoService {
    void createTodo(Todo todo);
    void deleteTodo(String id);
    void saveTodo(Todo todo);
    Todo updateTodo(Todo todo);
    Todo getTodo(String id);
    List<Todo> findTodoByState(String username, int state);
    List<Todo> findTodo(String username);
}
