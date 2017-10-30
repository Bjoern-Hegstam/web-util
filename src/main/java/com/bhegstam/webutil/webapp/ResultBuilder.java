package com.bhegstam.webutil.webapp;

import java.util.HashMap;
import java.util.Map;

public class ResultBuilder {
    private String contentType;
    private Integer statusCode;
    private final Map<String, String> headers;

    private String redirectPath;
    private Object responsePayload;
    private String renderTemplatePath;

    private Map<String, Object> renderModel;

    private ResultBuilder() {
        headers = new HashMap<>();
    }

    public static ResultBuilder result() {
        return new ResultBuilder();
    }

    public ResultBuilder type(String contentType) {
        this.contentType = contentType;
        return this;
    }

    public ResultBuilder statusCode(int statusCode) {
        this.statusCode = statusCode;
        return this;
    }

    public ResultBuilder header(String header, String value) {
        headers.put(header, value);
        return this;
    }

    public Result redirectTo(String path) {
        redirectPath = path;
        return build();
    }

    public Result returnPayload(Object payload) {
        responsePayload = payload;
        return build();
    }

    public Result render(String templatePath) {
        return render(templatePath, new HashMap<>());
    }

    public Result render(String templatePath, Map<String, Object> model) {
        this.renderTemplatePath = templatePath;
        this.renderModel = model;
        return build();
    }

    private Result build() {
        return new Result(
                redirectPath,
                responsePayload,
                renderTemplatePath,
                renderModel,
                contentType,
                statusCode,
                headers
        );
    }
}
