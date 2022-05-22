package com.zjl.component.sign;

import java.util.Objects;

import com.zjl.component.secure.util.SignatureUtils;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;

public class Md5Signer implements InitializingBean {

    @Value("component.web.sign.md5.secretKey:test")
    private String secretKey;

    public String sign(RequestSignEntity requestSignEntity) {
        Objects.requireNonNull(requestSignEntity);
        String signStr = requestSignEntity.signStr();
        signStr = signStr + requestSignEntity.getSplit() + secretKey;
        return SignatureUtils.getSignMD5(signStr);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if (Strings.isEmpty(secretKey)) {
            throw new Exception("secretKey can not be null");
        }
    }
}
