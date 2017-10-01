package com.bhe.webutil.webapp.sparkwrapper;

import spark.Request;

public class SparkRequest implements com.bhe.webutil.webapp.Request {
    private final Request request;

    public SparkRequest(Request request) {
        this.request = request;
    }

    @Override
    public com.bhe.webutil.webapp.Session session() {
        return new SparkSession(request.session());
    }

    @Override
    public String params(String key) {
        return request.params(key);
    }

    @Override
    public String queryParams(String key) {
        return request.queryParams(key);
    }

    @Override
    public String body() {
        return request.body();
    }
}
