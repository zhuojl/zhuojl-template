package com.zjl.component.web.support.sign;

import com.zjl.component.exception.SysException;
import com.zjl.component.secure.sign.Md5Signer;
import com.zjl.component.secure.sign.RequestSignEntity;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.util.StreamUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 验签
 */
@Order(Ordered.HIGHEST_PRECEDENCE + 100)        //设置优先级，数值越低优先级越高
// WebFilter过滤路径设置无效，通过FilterRegistrationBean配置
@Slf4j
public class Md5SignValidateFilter implements SignValidateFilter {

    private static final String FORM_CONTENT_TYPE = "application/x-www-form-urlencoded";


    public Md5SignValidateFilter() {
    }

    private Md5Signer md5Signer = new Md5Signer("test");

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
        throws IOException, ServletException {

        try {
            signValidate(request);
        } catch (Exception e) {
            throw new UnsupportedOperationException("403");
        }

        chain.doFilter(request, response);
    }

    private void signValidate(ServletRequest request) throws IOException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        if (httpServletRequest.getRequestURI().contains("test")) {
            return;
        }


        String sign = httpServletRequest.getHeader(Md5Signer.KEY_HEADER_SIGN);
        if (Strings.isEmpty(sign)) {
            throw new SysException("sign not be empty");
        }
        RequestSignEntity requestSignEntity = buildSignRequest(httpServletRequest);
        String signResult = md5Signer.sign(requestSignEntity);
        if (!sign.equals(signResult)) {
            throw new SysException("asdfadf");
        }
    }

    private RequestSignEntity buildSignRequest(HttpServletRequest httpServletRequest) throws IOException {
        RequestSignEntity requestSignEntity = new RequestSignEntity();
        Map<String, String> queryMap = getQueryMap(httpServletRequest.getParameterMap());
        requestSignEntity.setPath(httpServletRequest.getRequestURI());
        requestSignEntity.setRequestParams(queryMap);

        // 因为tomcat对 application/x-www-form-urlencoded 参数做了特殊处理，不能使用通用验签方式，或者需要更通用的方式
        if (isFormPost(httpServletRequest)) {
            requestSignEntity.setBody("");
        } else {
            requestSignEntity.setBody(
                StreamUtils.copyToString(httpServletRequest.getInputStream(), StandardCharsets.UTF_8));
        }
        return requestSignEntity;
    }

    private Map<String, String> getQueryMap(Map<String, String[]> parameterMap) {
        Map<String, String> queryMap = new HashMap<>();
        if (Objects.nonNull(parameterMap)) {
            parameterMap.entrySet().forEach(entry -> {
                String key = null;
                try {
                    key = URLDecoder.decode(entry.getKey(), StandardCharsets.UTF_8.name());
                } catch (UnsupportedEncodingException e) {
                    throw new SysException("UnsupportedEncodingException");
                }
                String value = "";
                if (Objects.nonNull(entry.getValue()) && entry.getValue().length != 0) {
                    try {
                        value = URLDecoder.decode(entry.getValue()[0], StandardCharsets.UTF_8.name());
                    } catch (UnsupportedEncodingException e) {
                        throw new SysException("UnsupportedEncodingException");
                    }
                }
                queryMap.put(key, value);
            });
        }
        return queryMap;
    }

    @Override
    public void destroy() {

    }

    private boolean isFormPost(HttpServletRequest httpServletRequest) {

        return Objects.nonNull(httpServletRequest.getContentType())
            && httpServletRequest.getContentType().contains(FORM_CONTENT_TYPE) &&
            HttpMethod.POST.matches(httpServletRequest.getMethod());
    }
}

