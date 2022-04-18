package com.zjl.component.web.support.sign;

import java.util.Map;
import java.util.Objects;
import java.util.SortedMap;
import java.util.TreeMap;

public class RequestSignEntity {
    private static String KEY_PATH = "path";
    private static String KEY_BODY = "body";
    private String path;
    private Map<String, String> requestParams;
    private String body;

    public String signStr() {
        SortedMap<String, String> sortedMap = new TreeMap<>();
        if (Objects.nonNull(requestParams)) {
            sortedMap.putAll(requestParams);
        }
        sortedMap.put(KEY_PATH, path);
        sortedMap.put(KEY_BODY, body);

        StringBuilder sb = new StringBuilder();
        sortedMap.forEach((k, v) -> {
            sb.append(k).append("=").append(v).append("&");
        });
        return sb.substring(0, sb.length() -1);
    }

    //public RequestSignEntity(String path, Map<String, String> params, String body) {
    //    this.path = path;
    //    this.params = params;
    //    this.body = body;
    //}

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Map<String, String> getRequestParams() {
        return requestParams;
    }

    public void setRequestParams(Map<String, String> requestParams) {
        this.requestParams = requestParams;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
