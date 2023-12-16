package com.zjl.archetype.web.infra.util;

import com.zjl.archetype.web.infra.dao.customer.CustomerDO;
import java.io.IOException;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class JsonUtilTest {

    @Test
    public void testParseJsonFromClassPathFile() throws IOException {
        CustomerDO customerDO = JsonUtil.parseJsonFromClassPathFile("test/util/test.json",
                CustomerDO.class);
        Assert.assertEquals("zjlId", customerDO.getCustomerId());
    }
}
