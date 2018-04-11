package com.lianweiq.util;


import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;
import java.security.*;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * @author lianweiq
 * @Date 2018/3/30,
 * @Time 12:07
 * @Description
 */
public class SignUtil {


    private static final String ALGORITHM = "EC";

    private static final String SIGN_ALGORITHM = "SHA1withECDSA";
    /**
     * 私钥对数据进行签名
     * @param data
     * @param privateKey
     * @return
     */
    public static byte[] sign(byte[] data, ECPrivateKey privateKey){

        //解码私钥
       // byte[] keyBytes = decryptBASE64(privateKey);

        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(privateKey.getEncoded());

        try {
            //获取加密工厂
            KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);

            //获取私钥对象
            PrivateKey priKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);

            //用私钥对信息生成数字签名,注册加密算法
            Signature signature = Signature.getInstance(SIGN_ALGORITHM);

            //初始化私钥
            signature.initSign(priKey);

            //数据签名
            signature.update(data);

            //获取数字签名
            return signature.sign();

        } catch (NoSuchAlgorithmException | InvalidKeySpecException | SignatureException | InvalidKeyException e) {
            e.printStackTrace();
        }

        return null;

    }

    /**
     * 签名验证
     * @param data 签名数据
     * @param publicKey 公钥
     * @param sign 签名
     * @return 验证结果
     */
    public static boolean verify(byte[] data, ECPublicKey publicKey, byte[] sign){

        //解码公钥
       // byte[] keyBytes = decryptBASE64(publicKey.get);

        //构造验证对象
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicKey.getEncoded());
        //获取加密工厂
        try {
            KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);

            //获取公钥
            PublicKey pubKey = keyFactory.generatePublic(keySpec);

            //注册签名加密算法

            Signature signature = Signature.getInstance(SIGN_ALGORITHM);
            signature.initVerify(pubKey);

            //执行验证过程
            signature.update(data);
            //验证签名是否正常
            return signature.verify(sign);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException | InvalidKeyException | SignatureException e) {
            e.printStackTrace();
        }
        return false;

    }



    public static String getKeyStr(Key key){
        return Base64Util.encryptBASE64(key.getEncoded());
    }

}
