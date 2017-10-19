package com.bhegstam.webutil.webapp;

import com.bhegstam.webutil.Filters;
import spark.Service;

import java.util.List;

import static spark.Service.ignite;

public abstract class ApplicationBase {
    private final List<Controller> appControllers;
    private final List<Controller> apiControllers;
    private final boolean apiRequiresUserLogin;

    public ApplicationBase(List<Controller> appControllers, List<Controller> apiControllers, boolean apiRequiresUserLogin) {
        this.appControllers = appControllers;
        this.apiControllers = apiControllers;
        this.apiRequiresUserLogin = apiRequiresUserLogin;
    }

    public void init() {
        Service http = ignite();
        configureServer(http);
        configureRoutes(http);
    }

    protected abstract void configureServer(Service http);

    private void configureRoutes(Service http) {
        appControllers.forEach(c -> c.configureRoutes(http));

        http.path("/api", () -> {
            if (apiRequiresUserLogin) {
                http.before("/*", Filters::userIsLoggedIn);
            }
            apiControllers.forEach(c -> c.configureRoutes(http));
        });
    }
}
