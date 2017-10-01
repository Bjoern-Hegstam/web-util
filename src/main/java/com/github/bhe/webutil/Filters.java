package com.github.bhe.webutil;

import com.github.bhe.webutil.webapp.User;
import com.github.bhe.webutil.webapp.sparkwrapper.SparkRequest;
import spark.Request;
import spark.Response;

import static spark.Spark.halt;

public class Filters {
    public static void userIsLoggedIn(Request request, Response response) {
        boolean userIsLoggedIn = new SparkRequest(request).session().isUserLoggedIn();
        if (!userIsLoggedIn) {
            haltNotAuthorized();
        }
    }

    public static void userIsAdmin(Request request, Response response) {
        boolean isAdmin = new SparkRequest(request)
                .session()
                .currentUser()
                .filter(User::isAdmin)
                .isPresent();
        if (!isAdmin) {
            haltNotAuthorized();
        }
    }

    @SuppressWarnings("ThrowableNotThrown")
    private static void haltNotAuthorized() {
        halt(401, "Not authorized");
    }
}
