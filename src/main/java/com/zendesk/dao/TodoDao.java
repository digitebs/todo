package com.zendesk.dao;

import java.util.List;

public interface TodoDao<T> extends Dao<T>{
    List<T> findTodo(String username);
    List<T> findTodoByState(String username, int state);
}
