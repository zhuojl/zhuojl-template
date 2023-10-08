package com.zjl.archetype.web.infra.util;

public class StringUtils {
    public static String subStringByByteLength(String str, String charset, int subSLength) {
        String subStr = "";
        try {
            if (str == null) return "";
            else {
                int tempSubLength = subSLength;//截取字节数
                //截取的子串
                subStr = str.substring(0, Math.min(str.length(), subSLength));
                //截取子串的字节长度
                int subStrByetsL = subStr.getBytes(charset).length;
                // 说明截取的字符串中包含有汉字
                while (subStrByetsL > tempSubLength) {
                    int subSLengthTemp = --subSLength;
                    subStr = str.substring(0, Math.min(subSLengthTemp, str.length()));
                    subStrByetsL = subStr.getBytes(charset).length;
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return subStr;
    }
}
