package com.zendesk.dao;

import com.google.inject.Inject;
import com.zendesk.model.Todo;
import io.jsondb.JsonDBTemplate;

import java.util.List;
import java.util.Optional;

public class TodoDaoImpl implements TodoDao<Todo> {
    @Override
    public List<Todo> getAll() {
        throw new UnsupportedOperationException("Get all not supported");
    }

    @Inject
    private JsonDBTemplate db;

    public Optional<Todo> get(String id) {
        return Optional.ofNullable(db.findById(id,Todo.class));
    }

    public List<Todo> findTodo(String username) {
        String jxQuery = String.format("/.[username='%s']", username);
        return db.find(jxQuery, Todo.class);
    }

    public List<Todo> findTodoByState(String username, int state) {
        String jxQuery = String.format("/.[username='%s' and state=%d]", username, state);
        return db.find(jxQuery, Todo.class);
    }

    public void save(Todo todo) {
        db.insert(todo);
    }

    public void update(Todo todo) {
        db.save(todo,Todo.class);
    }

    public void delete(Todo todo) {
        db.remove(todo,Todo.class);
    }
}
