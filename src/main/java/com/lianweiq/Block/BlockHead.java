package com.lianweiq.Block;


import java.io.Serializable;
import java.util.Date;

/**
 * @author lianweiq
 * @Date 2018/3/29,
 * @Time 17:35
 * @Description 区块头
 */
public class BlockHead implements Serializable{
    /**
     * 版本
     */
    private String version;
    /**
     * 父hash
     */
    private String parentHash;
    /**
     * 当前区块hash
     */
    private String currentHash;
    /**
     * 时间
     */
    private Date date;

    /**
     * merkle树根
     */
    private String merkleRootHash;

    public String getMerkleRootHash() {
        return merkleRootHash;
    }

    public void setMerkleRootHash(String merkleRootHash) {
        this.merkleRootHash = merkleRootHash;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getParentHash() {
        return parentHash;
    }

    public void setParentHash(String parentHash) {
        this.parentHash = parentHash;
    }

    public String getCurrentHash() {
        return currentHash;
    }

    public void setCurrentHash(String currentHash) {
        this.currentHash = currentHash;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}