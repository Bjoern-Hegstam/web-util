package com.github.bhe.webutil.webapp;

import java.util.HashMap;
import java.util.Map;

public class Result {
    public final String redirectPath;
    public final Object responsePayload;
    public final String renderTemplatePath;
    public final Map<String, Object> renderModel;

    Result(String redirectPath, Object responsePayload, String renderTemplatePath, Map<String, Object> renderModel) {
        this.redirectPath = redirectPath;
        this.responsePayload = responsePayload;
        this.renderTemplatePath = renderTemplatePath;
        this.renderModel = renderModel != null ? renderModel : new HashMap<>();
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
}
