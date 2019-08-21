package com.z.service;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import com.z.model.Todo;
import com.z.module.TodoModule;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Guice(modules = TodoModule.class)
public class TodoServiceTest {
    private static final Logger log
            = LoggerFactory.getLogger(TodoServiceTest.class);

    @Inject
    private TodoService todoService;

    @Inject
    @Named("db.files.location")
    private String dbFilesLocation;

    private Todo myTodo;

    @BeforeClass
    public void oneTimeSetUp() {
        myTodo = new Todo();
        myTodo.setUsername("test");
        myTodo.setBody("body");
        todoService.createTodo(myTodo);
    }

    @AfterClass
    public void oneTimeTeardown() {
        todoService.deleteTodo(myTodo.getId());
    }

    @AfterSuite
    public void afterSuite() {
        File dbFilesFolder = new File(dbFilesLocation);
        try {
            FileUtils.deleteDirectory(dbFilesFolder);
        } catch (IOException ioe) {
            log.error("Exception deleting directory", ioe);
        }
    }

    @Test
    public void findTodoByState() {
        List<Todo> list = todoService.findTodoByState(myTodo.getUsername(), myTodo.getState());
        Assert.assertEquals(list.size(), 1);
        Assert.assertEquals(list.get(0).getState(), 0);
    }

    @Test
    public void findTodo() {
        List<Todo> list = todoService.findTodo(myTodo.getUsername());
        Assert.assertEquals(list.size(), 1);
        Assert.assertEquals(list.get(0).getState(), 0);
    }

    @Test
    public void updateTodo() {
        Todo todo = new Todo();
        todo.setId(myTodo.getId());
        todo.setBody("hello");
        todo.setState(1);

        Todo todo1 = todoService.updateTodo(todo);
        Assert.assertEquals(todo1.getBody(), "body");
        Assert.assertEquals(todo1.getState(), 1);

        todo.setState(0);
        Todo todo2 = todoService.updateTodo(todo);
        Assert.assertEquals(todo2.getState(), 0);
    }
}
