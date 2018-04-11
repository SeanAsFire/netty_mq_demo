package com.lianweiq.BlockChain;


import com.lianweiq.Block.Block;

import java.io.Serializable;
import java.util.List;

/**
 * @author lianweiq
 * @Date 2018/3/29,
 * @Time 16:21
 * @Description
 */
public class BlockChain  implements Serializable{

    private List<Block> blockList;

    public List<Block> getBlockList() {
        return blockList;
    }

    public void setBlockList(List<Block> blockList) {
        this.blockList = blockList;
    }
}
