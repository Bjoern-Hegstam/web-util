package com.bhegstam.webutil.webapp;

import spark.Service;

public interface Controller {
    void configureRoutes(Service http);
}
