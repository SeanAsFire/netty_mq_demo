package com.lianweiq.util;


import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;

/**
 * @author lianweiq
 * @Date 2018/3/30,
 * @Time 16:01
 * @Description
 */
public class Base64Util {


    /**
     * base64解密
     * @param key
     * @return
     */
    public static byte[] decryptBASE64(String key){
        try {
            return (new BASE64Decoder()).decodeBuffer(key);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * base64加密
     * @param key
     * @return
     */
    public static String encryptBASE64(byte[] key){
        return (new BASE64Encoder()).encode(key);
    }
}
