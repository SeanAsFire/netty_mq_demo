package com.lianweiq.Tx;


import java.io.Serializable;

/**
 * @author lianweiq
 * @Date 2018/3/29,
 * @Time 16:07
 * @Description 交易结构
 */
public class Transaction implements Serializable {

    /**
     * 交易id
     */
    private String txId;

    /**
     * 输入
     */
    private Vin vin;
    /**
     * 输出
     */
    private Vout vout;


    public Vin getVin() {
        return vin;
    }

    public void setVin(Vin vin) {
        this.vin = vin;
    }

    public Vout getVout() {
        return vout;
    }

    public void setVout(Vout vout) {
        this.vout = vout;
    }

    public String getTxId() {
        return txId;
    }

    public void setTxId(String txId) {
        this.txId = txId;
    }
}
