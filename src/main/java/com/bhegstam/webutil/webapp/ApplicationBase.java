package com.bhegstam.webutil.webapp;

import com.bhegstam.webutil.Filters;
import spark.Service;

import java.util.List;

import static spark.Service.ignite;

public abstract class ApplicationBase {
    private final List<Controller> appControllers;
    private final List<Controller> apiControllers;
    private final boolean apiRequiresUserLogin;
    private final boolean appRoutesRegisteredAfterApiRoutes;

    public ApplicationBase(List<Controller> appControllers, List<Controller> apiControllers, boolean apiRequiresUserLogin) {
        this(appControllers, apiControllers, apiRequiresUserLogin, false);
    }

    public ApplicationBase(List<Controller> appControllers, List<Controller> apiControllers, boolean apiRequiresUserLogin, boolean appRoutesRegisteredAfterApiRoutes) {
        this.appControllers = appControllers;
        this.apiControllers = apiControllers;
        this.apiRequiresUserLogin = apiRequiresUserLogin;
        this.appRoutesRegisteredAfterApiRoutes = appRoutesRegisteredAfterApiRoutes;
    }

    public void init() {
        Service http = ignite();
        configureServer(http);
        configureRoutes(http);
    }

    protected abstract void configureServer(Service http);

    private void configureRoutes(Service http) {
        if (!appRoutesRegisteredAfterApiRoutes) {
            appControllers.forEach(c -> c.configureRoutes(http));
        }

        http.path("/api", () -> {
            if (apiRequiresUserLogin) {
                http.before("/*", Filters.userIsLoggedIn(Filters.Actions.haltNotAuthorized));
            }
            apiControllers.forEach(c -> c.configureRoutes(http));
        });

        if (appRoutesRegisteredAfterApiRoutes) {
            appControllers.forEach(c -> c.configureRoutes(http));
        }
    }
}
