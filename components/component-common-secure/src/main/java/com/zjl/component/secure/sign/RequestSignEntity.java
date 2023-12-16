package com.zjl.component.secure.sign;

import java.util.Map;
import java.util.Objects;
import java.util.SortedMap;
import java.util.TreeMap;
import lombok.Data;

@Data
public class RequestSignEntity {

    public static final String KEY_HEADER_SIGN = "_sign";
    public static final String SPLIT = "&";
    private static String KEY_PATH = "path";
    private static String KEY_BODY = "body";
    private String path;
    private Map<String, String> requestParams;
    private String body;
    private String split = SPLIT;

    public String signStr() {
        SortedMap<String, String> sortedMap = new TreeMap<>();
        if (Objects.nonNull(requestParams)) {
            sortedMap.putAll(requestParams);
        }
        sortedMap.put(KEY_PATH, path);
        sortedMap.put(KEY_BODY, body);

        StringBuilder sb = new StringBuilder();
        sortedMap.forEach((k, v) -> {
            sb.append(k).append("=").append(v).append(split);
        });
        return sb.substring(0, sb.length() - 1);
    }

}
