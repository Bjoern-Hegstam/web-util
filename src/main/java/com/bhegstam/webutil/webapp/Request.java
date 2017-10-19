package com.bhegstam.webutil.webapp;

public interface Request {

    Session session();

    String params(String key);

    String queryParams(String key);

    String body();
}
