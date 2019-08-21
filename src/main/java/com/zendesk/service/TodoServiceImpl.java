package com.zendesk.service;

import com.google.inject.Inject;
import com.zendesk.dao.TodoDao;
import com.zendesk.model.Todo;
import com.zendesk.module.TodoModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class TodoServiceImpl implements TodoService {

    private static final Logger log
            = LoggerFactory.getLogger(TodoModule.class);

    @Inject
    private TodoDao<Todo> dao;

    @Override
    public void createTodo(Todo todo) {
        todo.setId(UUID.randomUUID().toString());
        dao.save(todo);
    }

    @Override
    public void deleteTodo(String id) {
        dao.delete(getTodo(id));
    }

    @Override
    public void saveTodo(Todo todo) {
        dao.update(todo);
    }

    @Override
    public Todo updateTodo(Todo patch) {
        Todo todo = getTodo(patch.getId());
        todo.setState(patch.getState());

        saveTodo(todo);
        return todo;
    }

    @Override
    public List<Todo> findTodo(String username) {
        return dao.findTodo(username);
    }

    @Override
    public List<Todo> findTodoByState(String username, int state) {
        return dao.findTodoByState(username, state);
    }


    @Override
    public Todo getTodo(String id) {
        Optional<Todo> td = dao.get(id);
        return td.orElseThrow(() -> new IllegalArgumentException("Id " + id + " not Found"));
    }
}
