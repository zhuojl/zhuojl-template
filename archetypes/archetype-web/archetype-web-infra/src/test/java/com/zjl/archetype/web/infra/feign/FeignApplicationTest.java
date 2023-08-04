package com.zjl.archetype.web.infra.feign;

import com.google.common.collect.Maps;
import feign.Client;
import feign.Request;
import feign.Response;
import org.junit.Ignore;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;

@SpringBootTest(classes = FeignApplicationTest.FeignApplication.class, properties = {
    "spring.config.location:classpath:/application-test.properties"
})
@ExtendWith(SpringExtension.class)
@Ignore // 默认不进行容器启动的测试，infra层常年不会更改。除了dao
public interface FeignApplicationTest {

    @SpringBootApplication
    @EnableFeignClients(basePackages = "com.zjl.archetype.web.infra.feign")
    class FeignApplication {
        @Bean
        public Client client() {
            return new FeignApplicationTest.DummyFeignClient();
        }
    }

    /**
     * 为了减少对feign的mock，也测试feign能够别容器正常加载，
     * 用于测试常见问题：
     * 1. feign client name 一致，导致容器不能启动
     */
    class DummyFeignClient implements Client {
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
