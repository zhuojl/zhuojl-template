package com.zjl.archetype.web.infra.config;

import com.google.common.collect.Maps;
import feign.Client;
import feign.Request;
import feign.Response;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;


@Configuration
public class FeignTestConfig {
    @Bean
    public Client client() {
        return new FeignStubClient();
    }

    /**
     * 为了减少对feign的mock，也测试feign能够别容器正常加载，
     * 用于测试常见问题：
     * 1. feign client name 一致，导致容器不能启动
     */
    public static class FeignStubClient implements Client {
        @Override
        public Response execute(Request request, Request.Options options) throws IOException {
            Map<String, Collection<String>> responseHeaderMap = Maps.newHashMap();
            responseHeaderMap.put("content-type", Arrays.asList("application/json"));
            return Response.builder().request(request)
                .headers(responseHeaderMap)
                .status(HttpStatus.OK.value())
                .body("{}", StandardCharsets.UTF_8)
                .build();
        }
    }

}
