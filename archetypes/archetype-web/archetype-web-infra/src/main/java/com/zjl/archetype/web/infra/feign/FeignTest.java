package com.zjl.archetype.web.infra.feign;

import java.util.Map;

import com.zjl.component.web.support.sign.FeignSignConfiguration;

import com.zjl.archetype.web.infra.dao.CustomerDO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 更多open feign 使用示例可以看spring cloud open feign 源码中单元测试
 * org.springframework.cloud.openfeign.support.SpringMvcContractTests
 */
@FeignClient(name = "feignTest", url = "http://localhost:8080/", configuration = FeignSignConfiguration.class)
public interface FeignTest {

    @PostMapping(value = "internal/testFeign", consumes = "application/json")
    CustomerDO test(@RequestBody Map param);

    @PostMapping(value = "internal/testFeign2?aa=bbbc", consumes = "application/x-www-form-urlencoded")
    CustomerDO test2(String param);

    /**
     *  form 表单的场景 特殊处理，因为tomcat对form表单有特殊处理，不能用@RequestBody注解（接收参数也不能用这个注解）
     */
    @PostMapping(value = "internal/testFeign2?zjl=new")
    CustomerDO test3(@SpringQueryMap CustomerDO customerDO);

}