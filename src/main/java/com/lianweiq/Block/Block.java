package com.lianweiq.Block;


import java.io.Serializable;
import java.util.List;

/**
 * @author lianweiq
 * @Date 2018/3/29,
 * @Time 15:58
 * @Description 区块
 */
public class Block implements Serializable {

    /**
     * 区块头
     */
    private BlockHead blockHead;
    /**
     * 交易数量
     */
    private int txCount;
    /**
     * 交易数据hash
     */
    private List<String> txHashs;

    public int getTxCount() {
        return txCount;
    }

    public void setTxCount(int txCount) {
        this.txCount = txCount;
    }

    public List<String> getTxHashs() {
        return txHashs;
    }

    public void setTxHashs(List<String> txHashs) {
        this.txHashs = txHashs;
    }

    public BlockHead getBlockHead() {
        return blockHead;
    }

    public void setBlockHead(BlockHead blockHead) {
        this.blockHead = blockHead;
    }
}

