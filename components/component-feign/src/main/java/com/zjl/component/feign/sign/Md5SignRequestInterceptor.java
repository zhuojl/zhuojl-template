package com.zjl.component.feign.sign;

import com.zjl.component.common.exception.CommonErrorEnum;
import com.zjl.component.common.exception.ExceptionFactory;
import com.zjl.component.secure.sign.Md5Signer;
import com.zjl.component.secure.sign.RequestSignEntity;
import feign.Request.HttpMethod;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Slf4j
public class Md5SignRequestInterceptor implements SignRequestInterceptor {
    private static final String DEFAULT_EMPTY_BODY = "Binary data";
    private static final String FORM_CONTENT_TYPE = "application/x-www-form-urlencoded";
    private Md5Signer md5Signer = new Md5Signer("test");

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

        String sign = md5Signer.sign(requestSignEntity);
        template.header(Md5Signer.KEY_HEADER_SIGN, sign);
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
                    throw ExceptionFactory.sysException(CommonErrorEnum.INVALID_PARAMETER);
                }
                String value = "";
                if (idx + 1 < pair.length()) {
                    try {
                        value = URLDecoder.decode(pair.substring(idx + 1), StandardCharsets.UTF_8.name());
                    } catch (UnsupportedEncodingException e) {
                        throw ExceptionFactory.sysException(CommonErrorEnum.INVALID_PARAMETER);
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
