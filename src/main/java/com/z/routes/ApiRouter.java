package com.z.routes;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Base64;

import static spark.Spark.*;

public class ApiRouter implements Router {

    private static final Logger log
            = LoggerFactory.getLogger(ApiRouter.class);

    @Inject
    @Named("TodoRouter")
    private Router todo;

    private static void authorized() {
        before("/*", (request, response) -> {
            String auth = request.headers("Authorization");
            String base64Credentials = auth.substring("Basic".length()).trim();
            byte[] byteArray = Base64.getDecoder().decode(base64Credentials);
            String[] credentials = new String(byteArray).split(":");
            String username = credentials[0];
            String password = new StringBuffer(credentials[1]).reverse().toString();

            log.debug(String.format("%s:%s", username, password));
            boolean authenticated = username.length() > 0 && username.equals(password);
            // ... check if authenticated
            if (!authenticated) {
                halt(401, "Error 401 - Unauthorized");
            }

            request.attribute("username", username);
        });
    }


    // register your routes here
    private void registry() {
        todo.route();
    }

    public void route() {
        path("/api", () -> {
            authorized();
            registry();
        });
    }
}
