package com.zjl.component.secure.sign;

import com.zjl.component.secure.util.SignatureUtils;
import lombok.Setter;

import java.util.Objects;

@Setter
public class Md5Signer {

    public static final String KEY_HEADER_SIGN = "_sign";

    private String secretKey;

    public Md5Signer(String secretKey) {
        this.secretKey = secretKey;
    }

    public String sign(RequestSignEntity requestSignEntity) {
        Objects.requireNonNull(requestSignEntity);
        String signStr = requestSignEntity.signStr();
        signStr = signStr + requestSignEntity.getSplit() + secretKey;
        return SignatureUtils.getSignMD5(signStr);
    }

}
