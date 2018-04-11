package com.lianweiq.BlockChain;


import com.lianweiq.Block.Block;
import com.lianweiq.BlockChain.BlockChain;
import com.lianweiq.Cache.CacheData;

/**
 * @author lianweiq
 * @Date 2018/3/29,
 * @Time 17:56
 * @Description 区块链服务
 */
public class BlockChainService {

    /**
     * 最简单的实现，内部可以拓展
     * @param blockChain
     * @param block
     * @return  区块链添加区块
     */
    public BlockChain addBlock(BlockChain blockChain,Block block){
        blockChain.getBlockList().add(block);
        return blockChain;
    }


    public CacheData getTimeData(String publicKey){

        return new CacheData();
    }
}
