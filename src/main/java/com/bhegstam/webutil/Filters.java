package com.bhegstam.webutil;

import com.bhegstam.webutil.webapp.User;
import com.bhegstam.webutil.webapp.sparkwrapper.SparkRequest;
import org.eclipse.jetty.http.HttpStatus;
import spark.Filter;
import spark.Request;
import spark.Response;

import static spark.Spark.halt;

public class Filters {
    public static Filter userIsLoggedIn(Action actionIfNotLoggedIn) {
        return (request, response) -> {
            boolean userIsLoggedIn = new SparkRequest(request).session().isUserLoggedIn();
            if (!userIsLoggedIn) {
                actionIfNotLoggedIn.execute(request, response);
            }
        };
    }

    public static Filter userIsAdmin(Action actionIfNotAdmin) {
        return (request, response) -> {
            boolean isAdmin = new SparkRequest(request)
                    .session()
                    .currentUser()
                    .filter(User::isAdmin)
                    .isPresent();
            if (!isAdmin) {
                actionIfNotAdmin.execute(request, response);
            }
        };
    }

    private interface Action {
        void execute(Request request, Response response);
    }

    public static class Actions {
        public static Action haltNotAuthorized = (req, resp) -> halt(401, "Not authorized");
        public static Action redirectNotAuthorized(String redirectPath) {
            return (req, resp) -> {
                resp.redirect(redirectPath);
                halt(HttpStatus.FOUND_302);
            };
        }
    }
}
