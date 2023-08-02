package com.zjl.component.feign.log;


import feign.Logger;
import feign.Request;
import feign.Response;
import feign.Util;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Collection;
import java.util.Map;

import static feign.Util.*;


public final class FeignLogger extends Logger {

    private final org.slf4j.Logger log;

    public FeignLogger() {
        this(feign.Logger.class);
    }

    public FeignLogger(Class<?> clazz) {
        this(LoggerFactory.getLogger(clazz));
    }

    public FeignLogger(String name) {
        this(LoggerFactory.getLogger(name));
    }

    FeignLogger(org.slf4j.Logger logger) {
        this.log = logger;
    }

    private String headers(Map<String, Collection<String>> headerMap) {
        StringBuilder headers = new StringBuilder();
        for (String field : headerMap.keySet()) {
            for (String value : valuesOrEmpty(headerMap, field)) {
                headers.append("\n").append(field).append(": ").append(value);
            }
        }
        return headers.toString();
    }

    @Override
    protected void logRequest(String configKey, Level logLevel, Request request) {
        String url = request.url();
        String body = "";
        if (request.requestBody() != null) {
            body = request.requestBody().asString();
        }
        log.info("{} : {}\n body: {}\n header: {}", configKey, url, body, headers(request.headers()));
    }

    @Override
    protected Response logAndRebufferResponse(String configKey, Level logLevel, Response response,
                                              long elapsedTime) throws IOException {
        int status = response.status();
        String url = response.request().url();

        String content = "";
        if (response.body() != null && !(status == 204 || status == 205)) {
            byte[] bodyData = Util.toByteArray(response.body().asInputStream());
            if (bodyData.length > 0) {
                content = decodeOrDefault(bodyData, UTF_8, "Binary data");
            }
            response = response.toBuilder().body(bodyData).build();
        }

        log.info("{} : Received response code: {} {}ms for {} \nbody: {}", configKey, status, elapsedTime, url, content);
        return response;
    }

    @Override
    protected IOException logIOException(String configKey, Level logLevel, IOException ioe, long elapsedTime) {
        log(configKey, "<--- ERROR %s: %s (%sms)", ioe.getClass().getSimpleName(), ioe.getMessage(),
            elapsedTime);
        if (logLevel.ordinal() >= Level.FULL.ordinal()) {
            StringWriter sw = new StringWriter();
            ioe.printStackTrace(new PrintWriter(sw));
            log(configKey, "%s", sw.toString());
            log(configKey, "<--- END ERROR");
        }
        return ioe;
    }

    @Override
    protected void log(String configKey, String format, Object... args) {
        // feign中太多地方使用
    }
}
