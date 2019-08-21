package com.zendesk;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.zendesk.module.TodoModule;
import com.zendesk.routes.ApiRouter;

public class Boot {
    public static void main(String[] args) {
	// write your code here
        Injector injector = Guice.createInjector(new TodoModule());
        ApiRouter todo = injector.getInstance(ApiRouter.class);
        todo.route();
    }
}
