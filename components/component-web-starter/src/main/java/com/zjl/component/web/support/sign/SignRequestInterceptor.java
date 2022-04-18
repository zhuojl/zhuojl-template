package com.zjl.component.web.support.sign;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import com.zjl.component.exception.SysException;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;

@Slf4j
public class SignRequestInterceptor implements RequestInterceptor {
    private static final String DEFAULT_EMPTY_BODY = "Binary data";
    private static final String FORM_CONTENT_TYPE = "application/x-www-form-urlencoded";
    private final String PRIVATE_KEY
        =
        "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAKQGzKzZkoXB2A3xxMWBtbw8BRMo3MPYJE8nwK93YdL4u8jWoeFICmXMN7Txh2BOKnqOyex/aWYOVTLgsUcaU6ycajzbV3lF1+DNkTT3w7Lg8EwOHlrz/ltWwmDenXUyaeuD+2fGBrBagHDJE3oURs/KWID5AMMgN7c7IglBJoR1AgMBAAECgYEAj2ImPx7+V5CI1j+2+9QUUpTA9uuseEKUEuG0LW6Vg//M35bH8Y+xDyXCuJi993C871+soeEK+Jyk25HRRk98PidYVXseDBlJYV3fotx5rmYPYR0NGPvbW1RFYMsQUUM3NfJuDRL1AhBjBCYiVM1VKHQO1LLfv8Gkuda0cyAGQKECQQDx2gXA3EifWCDCW6apgIETaEIJcoHaUe9y1VrB5KfskbgjKns7+OPYT0I1kotZu/Iws8VGaeE2CyF96QzwMC49AkEArZ9EJAYSgQBH/7q74VNBUaMD3z3YVhwDpi9XdwyRd7GdJXuAIbZfBJI+8H4Sq8BsI0B7XEW/jwETpe5dngaKmQJBAMmwpmelzTKFjhxHzn9A2WPT6G50ffIRrny3jM5x39Cb3VIGVWs4LtrvjimbIncdE+alpPkJx3UIZ0/XkKClrYkCQDNmdTvc+FlsheQ1mi2pAitzAVBz9Ln5bTMjzNcXx3ESCh3wpAxW+2ZVDYERMeHbA6ikDGFS3NUvUmvLV7fS4/ECQCSwU8WmxMxNdxLwrQk8RBs2e9iJvkH1YGOFUaOSm+hjtitv9aOn7ier2thVwkXwgBM6Xdrb1z13ak7EW+ZGtE4=";
    public static final String KEY_HEADER_SIGN = "_sign";

    @Override
    public void apply(RequestTemplate template) {

        RequestSignEntity requestSignEntity = new RequestSignEntity();
        requestSignEntity.setPath(template.path());
        Map<String, String> queryMap = getQueryMap(template);
        requestSignEntity.setRequestParams(queryMap);

        // 因为tomcat对 application/x-www-form-urlencoded 参数做了特殊处理，不能使用通用验签方式，或者需要更通用的方式
        if (isFormPost(template)) {
            requestSignEntity.setBody("");
        } else {
            // @see feign.Request.Body.asString
            String body = template.requestBody().asString();
            requestSignEntity.setBody(DEFAULT_EMPTY_BODY.equals(body) ? "" : body);
        }

        String signStr = requestSignEntity.signStr();
        try {
            log.info("signStr:{} ", signStr);
            String sign = RsaSignUtil.sign(signStr, RsaSignUtil.getPrivateKey(PRIVATE_KEY));
            template.header(KEY_HEADER_SIGN, sign);
        } catch (Exception e) {
            throw new SysException("sign error");
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
            HttpMethod.POST.equals(template.request().httpMethod());
    }
}
