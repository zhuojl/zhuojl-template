package com.zjl.archetype.web.infra.feign;

import com.zjl.archetype.web.infra.BaseApplicationTest;
import com.zjl.archetype.web.infra.dao.CustomerDO;
import org.junit.Ignore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 测试：
 * 1. 容器启动时，feign client顺利加载到容器中
 * 2. 方法的参数定义符合feign规则，常见问题：get
 */

@Ignore
public class FeignTestTest extends BaseApplicationTest {

    @Autowired
    private FeignTest feignTest;

    @BeforeEach
    void setUp() {

    }

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
