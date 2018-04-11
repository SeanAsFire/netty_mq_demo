package com.lianweiq.util;


import javafx.util.Pair;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;

/**
 * @author lianweiq
 * @Date 2018/3/30,
 * @Time 10:43
 * @Description 椭圆曲线算法
 */
public class EccUtil {

    private static final String ALGORITHM = "EC";
    /**
     * 初始化公钥，私钥
     * @return
     */
    private static KeyPair getKeyPair() {
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(ALGORITHM);
            keyPairGenerator.initialize(256);
            KeyPair keyPair = keyPairGenerator.generateKeyPair();
            return keyPair;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static Pair<ECPublicKey,ECPrivateKey> init(){
        KeyPair keyPair = getKeyPair();
        return new Pair<>((ECPublicKey)keyPair.getPublic(),(ECPrivateKey)keyPair.getPrivate());

    }

}
