package com.zjl.archetype.web.infra.util;

import com.alibaba.fastjson.JSONObject;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;

public class JsonUtil {


    public static <T> T parseJsonFromClassPathFile(String path, Class<T> cls) throws IOException {
        InputStream inputStream = new ClassPathResource(path).getInputStream();
        return JSONObject.parseObject(inputStream, cls);
    }
}
