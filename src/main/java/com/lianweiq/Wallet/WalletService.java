package com.lianweiq.Wallet;


import com.lianweiq.Tx.Transaction;
import com.lianweiq.Tx.Vin;
import com.lianweiq.Tx.Vout;
import com.lianweiq.util.Base64Util;
import com.lianweiq.util.ByteUtil;
import com.lianweiq.util.EccUtil;
import com.lianweiq.util.SignUtil;
import javafx.util.Pair;

import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;
import java.util.Date;

/**
 * @author lianweiq
 * @Date 2018/3/30,
 * @Time 11:46
 * @Description 钱包服务类
 */
public class WalletService {


    public static Wallet initWallet(){
        Wallet wallet = new Wallet();
        Pair<ECPublicKey,ECPrivateKey> key = EccUtil.init();
        wallet.setPrivateKey(key.getValue());
        wallet.setPublicKey(key.getKey());
        return wallet;
    }



    public static void main(String[] args){
        Wallet wallet = initWallet();

        String priKey = SignUtil.getKeyStr(wallet.getPrivateKey());
        String pubKey = SignUtil.getKeyStr(wallet.getPublicKey());

        Transaction transaction = new Transaction();
        Vin vin = new Vin();
        vin.setSigDate(new Date());
        Vout vout = new Vout();
        vout.setDonateTime(1024);
        vout.setPubHash("hhhhhhhhhhhhhhhh");
        transaction.setVin(vin);
        transaction.setVout(vout);
        transaction.setTxId("No.1");

        byte[] data = ByteUtil.objectToBytes(transaction);

        byte[] signature = SignUtil.sign(data,wallet.getPrivateKey());

        vin.setSignature(Base64Util.encryptBASE64(signature));
        vin.setPubKey(pubKey);

        boolean isValid = SignUtil.verify(data,wallet.getPublicKey(),signature);

        System.out.println(isValid);

    }

}
