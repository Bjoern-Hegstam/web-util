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

    Optional<String> getContentType() {
        return Optional.ofNullable(contentType);
    }

    Optional<Integer> getStatusCode() {
        return Optional.ofNullable(statusCode);
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    boolean isRedirect() {
        return redirectPath != null;
    }

    boolean isPayloadResponse() {
        return responsePayload != null;
    }

    boolean isRender() {
        return renderTemplatePath != null;
    }
}
