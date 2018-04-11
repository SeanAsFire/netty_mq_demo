package com.lianweiq.Wallet;


import java.io.Serializable;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;

/**
 * @author lianweiq
 * @Date 2018/3/30,
 * @Time 10:39
 * @Description
 */
public class Wallet implements Serializable {

    /**
     * 私钥
     */
    private ECPrivateKey privateKey;

    /**
     * 公钥
     */
    private ECPublicKey publicKey;


    public ECPrivateKey getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(ECPrivateKey privateKey) {
        this.privateKey = privateKey;
    }

    public ECPublicKey getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(ECPublicKey publicKey) {
        this.publicKey = publicKey;
    }
}
