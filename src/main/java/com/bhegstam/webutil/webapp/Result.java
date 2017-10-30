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

    Result(String redirectPath, Object responsePayload, String renderTemplatePath, Map<String, Object> renderModel, String contentType, Integer statusCode) {
        this.redirectPath = redirectPath;
        this.responsePayload = responsePayload;
        this.renderTemplatePath = renderTemplatePath;
        this.renderModel = renderModel != null ? renderModel : new HashMap<>();
        this.contentType = contentType;
        this.statusCode = statusCode;
    }

    Optional<String> getContentType() {
        return Optional.ofNullable(contentType);
    }

    Optional<Integer> getStatusCode() {
        return Optional.ofNullable(statusCode);
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
