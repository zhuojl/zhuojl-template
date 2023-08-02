package com.zjl.archetype.web.infra.feign;

import com.zjl.archetype.web.infra.SpringApplicationTest;
import com.zjl.archetype.web.infra.dao.CustomerDO;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;

/**
 * 测试：
 * 1. 容器启动时，feign client顺利加载到容器中
 * 2. 方法的参数定义符合feign规则，常见问题：get
 */

@Ignore // 默认不进行容器启动的测试，infra层常年不会更改。除了dao
public class FeignTestTest implements SpringApplicationTest {

    @Autowired
    private FeignTest feignTest;

    @Test
    void test() {
//        Mockito.when(feignTest.test(Mockito.any())).thenReturn(Mockito.any());
        feignTest.test(new HashMap());
    }

    @Test
    void test2() {
        feignTest.test2("");
    }

    @Test
    void test3() {
        feignTest.test3(new CustomerDO());
    }
}
