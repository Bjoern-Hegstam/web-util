package com.bhegstam.webutil.webapp;

import com.bhegstam.webutil.ViewUtil;
import com.bhegstam.webutil.webapp.sparkwrapper.SparkRequest;
import spark.*;

public class SparkWrappers {
    public static spark.Route asSparkRoute(Route route) {
        return (request, response) -> {
            Result result = route.handle(new SparkRequest(request));

            return parseResult(request, response, result);
        };
    }

    private static Object parseResult(spark.Request request, Response response, Result result) {
        result.getContentType().ifPresent(response::type);
        result.getStatusCode().ifPresent(response::status);
        result.getHeaders().forEach(response::header);

        if (result.isRedirect()) {
            response.redirect(result.redirectPath);
            return null;
        }

        if (result.isPayloadResponse()) {
            return result.responsePayload;
        }

        if (result.isRender()) {
            return ViewUtil.render(new SparkRequest(request), result.renderModel, result.renderTemplatePath);
        }

        return null;
    }
}
