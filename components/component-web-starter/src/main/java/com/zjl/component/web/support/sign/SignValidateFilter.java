package com.zjl.component.web.support.sign;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import com.zjl.component.exception.SysException;

import org.apache.logging.log4j.util.Strings;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.util.StreamUtils;

/**
 * 验签
 */
@Order(Ordered.HIGHEST_PRECEDENCE + 10)        //设置优先级，数值越低优先级越高
@WebFilter(filterName = "signValidateFilter", urlPatterns = "/internal/*")    //过滤路径
public class SignValidateFilter implements Filter {

    private static final String FORM_CONTENT_TYPE = "application/x-www-form-urlencoded";

    private final String PUBLIC_KEY
        = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCkBsys2ZKFwdgN8cTFgbW8PAUTKNzD2CRPJ8Cvd2HS"
        + "+LvI1qHhSAplzDe08YdgTip6jsnsf2lmDlUy4LFHGlOsnGo821d5RdfgzZE098Oy4PBMDh5a8/5bVsJg3p11Mmnrg"
        + "/tnxgawWoBwyRN6FEbPyliA+QDDIDe3OyIJQSaEdQIDAQAB";

    public SignValidateFilter() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
        throws IOException, ServletException {

        try {
            HttpServletRequest httpServletRequest = (HttpServletRequest)request;
            String sign = httpServletRequest.getHeader(SignRequestInterceptor.KEY_HEADER_SIGN);
            if (Strings.isEmpty(sign)) {
                throw new SysException("sign not be empty");
            }

            RequestSignEntity requestSignEntity = new RequestSignEntity();
            Map<String, String> queryMap = getQueryMap(request.getParameterMap());
            requestSignEntity.setPath(httpServletRequest.getRequestURI());
            requestSignEntity.setRequestParams(queryMap);

            // 因为tomcat对 application/x-www-form-urlencoded 参数做了特殊处理，不能使用通用验签方式，或者需要更通用的方式
            if (isFormPost(httpServletRequest)) {
                requestSignEntity.setBody("");
            } else {
                requestSignEntity.setBody(
                    StreamUtils.copyToString(httpServletRequest.getInputStream(), StandardCharsets.UTF_8));
            }

            String signStr = requestSignEntity.signStr();
            boolean success = RsaSignUtil.verify(signStr, RsaSignUtil.getPublicKey(PUBLIC_KEY), sign);
            if (!success) {
                throw new SysException("asdfadf");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        chain.doFilter(request, response);
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

