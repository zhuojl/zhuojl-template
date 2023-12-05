package com.zjl.component.common.context;

import java.util.Objects;


public class RequestContextHolder {

    private static final ThreadLocal<RequestContext> requestContextLocal = new ThreadLocal<>();

    public static String getUserId() {
        return requestContextLocal.get().getUserId();
    }

    public static String getTraceId() {
        return requestContextLocal.get().getTraceId();
    }

    public static String getRealIp() {
        return requestContextLocal.get().getRealIp();
    }


    public static void setRequestContextHolder(RequestContext requestContext) {
        Objects.requireNonNull(requestContext);
        requestContextLocal.set(requestContext);
    }

    public static void clear() {
        requestContextLocal.remove();
    }

}
