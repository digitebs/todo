package com.z.routes;

import com.google.inject.Inject;
import com.z.model.Todo;
import com.z.serde.GsonSerde;
import com.z.service.TodoService;

import java.util.List;
import java.util.Objects;

import static spark.Spark.*;

public class TodoRouter implements Router, GsonSerde {
    @Inject
    private TodoService todoService;

    @Override
    public void route() {
        get("/todo/:id", (request, response) -> {
            // Show something
            Todo td = todoService.getTodo(request.params(":id"));
            return gson.toJson(td);
        });

        get("/todo", (request, response) -> {
            // Show something
            List<Todo> td;
            String state = request.queryParams("state");
            if (Objects.isNull(state))
                td = todoService.findTodo(
                        request.attribute("username"));
            else
                td = todoService.findTodoByState(
                        request.attribute("username"),
                        Integer.parseInt(state));
            return gson.toJson(td);
        });

        post("/todo", (request, response) -> {
            Todo todo = gson.fromJson(request.body(), Todo.class);
            todo.setUsername(request.attribute("username"));
            todoService.createTodo(todo);
            // Create something
            return gson.toJson(todo);
        });

        put("/todo/:id", (request, response) -> {
            Todo todo = gson.fromJson(request.body(), Todo.class);
            todo.setId(request.params(":id"));
            todoService.saveTodo(todo);
            return gson.toJson(todo);
        });

        patch("/todo/:id", (request, response) -> {
            Todo todo = gson.fromJson(request.body(), Todo.class);
            todo.setId(request.params(":id"));
            return gson.toJson(todoService.updateTodo(todo));
        });

        delete("/todo/:id", (request, response) -> {
            todoService.deleteTodo(request.params(":id"));
            // Annihilate something
            response.status(204);
            return "";
        });
    }
}
