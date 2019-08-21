package com.z;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.z.module.TodoModule;
import com.z.routes.ApiRouter;

public class Boot {
    public static void main(String[] args) {
	// write your code here
        Injector injector = Guice.createInjector(new TodoModule());
        ApiRouter todo = injector.getInstance(ApiRouter.class);
        todo.route();
    }
}
