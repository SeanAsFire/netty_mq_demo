package com.lianweiq.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Hex;

/**
 * @author lianweiq
 * @Date 2018/3/29,
 * @Time 16:41
 * @Description
 */
public class Sha256Hash {

    public static String sha256Hash(byte[] bytes) {
        MessageDigest messageDigest;
        String encodeStr = "";
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
            byte[] hash = messageDigest.digest(bytes);
            encodeStr = Hex.encodeHexString(hash);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return encodeStr;
    }

    public static void main(String[] args) {
        byte[] bytes = "helo".getBytes();
        System.out.println(sha256Hash(bytes));
        System.out.println(sha256Hash(bytes).getBytes().length);
    }
}
