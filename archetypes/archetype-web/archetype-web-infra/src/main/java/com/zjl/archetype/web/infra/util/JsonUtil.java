package com.zjl.archetype.web.infra.util;

import com.alibaba.fastjson.JSONObject;
import java.io.IOException;
import java.io.InputStream;
import org.springframework.core.io.ClassPathResource;

public class JsonUtil {


    public static <T> T parseJsonFromClassPathFile(String path, Class<T> cls) throws IOException {
        InputStream inputStream = new ClassPathResource(path).getInputStream();
        return JSONObject.parseObject(inputStream, cls);
    }
}
