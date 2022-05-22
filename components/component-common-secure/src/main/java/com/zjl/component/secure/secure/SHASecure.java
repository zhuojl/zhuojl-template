package com.zjl.component.secure.secure;

import java.security.MessageDigest;

import com.zjl.component.secure.common.SecureType;

/**
 * SHA 单向加密
 */
public class SHASecure extends BaseSecure {

    @Override
    public byte[] encrypt(byte[] data) throws Exception {
        MessageDigest messageDigest = MessageDigest.getInstance(SecureType.SHA.getType());
        return messageDigest.digest(data);
    }

    @Override
    public byte[] decrypt(byte[] data) throws Exception {
        return null;
    }
}
