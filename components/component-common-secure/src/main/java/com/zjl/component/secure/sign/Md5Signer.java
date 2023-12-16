package com.zjl.component.secure.sign;

import com.zjl.component.secure.util.SignatureUtils;
import java.util.Objects;
import lombok.Setter;

@Setter
public class Md5Signer {

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
