package com.zjl.archetype.web.infra.util;

import com.zjl.archetype.web.infra.dao.CustomerDO;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class JsonUtilTest {

    @Test
    public void testParseJsonFromClassPathFile() throws IOException {
        CustomerDO customerDO = JsonUtil.parseJsonFromClassPathFile("test/util/test.json", CustomerDO.class);
        Assert.assertEquals("zjlId", customerDO.getCustomerId());
    }
}
