package com.bhegstam.webutil.webapp;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Result {
    final String redirectPath;
    final Object responsePayload;
    final String renderTemplatePath;
    final Map<String, Object> renderModel;

    private final String contentType;
    private final Integer statusCode;
    private final Map<String, String> headers;

    Result(
            String redirectPath,
            Object responsePayload,
            String renderTemplatePath,
            Map<String, Object> renderModel,
            String contentType,
            Integer statusCode,
            Map<String, String> headers
    ) {
        this.redirectPath = redirectPath;
        this.responsePayload = responsePayload;
        this.renderTemplatePath = renderTemplatePath;
        this.renderModel = renderModel != null ? renderModel : new HashMap<>();
        this.contentType = contentType;
        this.statusCode = statusCode;
        this.headers = headers;
    }

    public Optional<String> getContentType() {
        return Optional.ofNullable(contentType);
    }

    public Optional<Integer> getStatusCode() {
        return Optional.ofNullable(statusCode);
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public boolean isRedirect() {
        return redirectPath != null;
    }

    public boolean isPayloadResponse() {
        return responsePayload != null;
    }

    public boolean isRender() {
        return renderTemplatePath != null;
    }

    public String getRedirectPath() {
        return redirectPath;
    }

    public String getRenderTemplatePath() {
        return renderTemplatePath;
    }
}
