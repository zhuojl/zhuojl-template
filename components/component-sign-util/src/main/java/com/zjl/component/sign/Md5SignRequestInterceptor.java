package com.zjl.component.sign;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import com.zjl.component.exception.SysException;
import feign.Request.HttpMethod;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class Md5SignRequestInterceptor implements RequestInterceptor {
    private static final String DEFAULT_EMPTY_BODY = "Binary data";
    private static final String FORM_CONTENT_TYPE = "application/x-www-form-urlencoded";
    public static final String KEY_HEADER_SIGN = "_sign";

    @Autowired
    private Md5Signer md5Signer;

    @Override
    public void apply(RequestTemplate template) {

        RequestSignEntity requestSignEntity = new RequestSignEntity();
        requestSignEntity.setPath(template.path());
        Map<String, String> queryMap = getQueryMap(template);
        requestSignEntity.setRequestParams(queryMap);

        // 因为tomcat对 application/x-www-form-urlencoded 参数做了特殊处理，不能使用通用验签方式，或者需要更通用的方式
        if (isFormPost(template)) {
            fillQueryMap(queryMap, template.requestBody().asString());
            requestSignEntity.setBody("");
        } else {
            String body = template.requestBody().asString();
            // @see feign.Request.Body.asString
            requestSignEntity.setBody(DEFAULT_EMPTY_BODY.equals(body) ? "" : body);
        }

        try {
            String sign = md5Signer.sign(requestSignEntity);
            template.header(KEY_HEADER_SIGN, sign);
        } catch (Exception e) {
            throw new SysException("sign error");
        }
    }

    private void fillQueryMap(Map<String, String> queryMap, String body) {
        if (Strings.isEmpty(body)) {
            return;
        }

        String[] pairs = body.split("&");
        for (String pair : pairs) {
            int idx = pair.indexOf("=");
            String key = pair.substring(0, idx);
            String value = "";
            if (idx + 1 < pair.length()) {
                value = pair.substring(idx + 1);
            }
            queryMap.put(key, value);
        }
    }

    private Map<String, String> getQueryMap(RequestTemplate template) {
        Map<String, String> queryMap = new HashMap<>();
        String url = template.request().url();
        String[] url_split = url.split("\\?");
        if (url_split.length > 1) {
            String[] pairs = url_split[1].split("&");
            for (String pair : pairs) {
                int idx = pair.indexOf("=");
                String key;
                try {
                    key = URLDecoder.decode(pair.substring(0, idx), StandardCharsets.UTF_8.name());
                } catch (UnsupportedEncodingException e) {
                    throw new SysException("");
                }
                String value = "";
                if (idx + 1 < pair.length()) {
                    try {
                        value = URLDecoder.decode(pair.substring(idx + 1), StandardCharsets.UTF_8.name());
                    } catch (UnsupportedEncodingException e) {
                        throw new SysException("");
                    }
                }
                queryMap.put(key, value);
            }
        }
        return queryMap;
    }

    private boolean isFormPost(RequestTemplate template) {
        Collection<String> contentTypes = template.request().headers().get("Content-Type");

        return Objects.nonNull(contentTypes)
            && contentTypes.contains(FORM_CONTENT_TYPE) &&
            HttpMethod.POST.name().equals(template.request().httpMethod().name());
    }
}
