package com.lianweiq.merkle;


import com.lianweiq.util.Sha256Hash;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lianweiq
 * @Date 2018/3/29,
 * @Time 20:39
 * @Description 创建merkle树代码
 */
public class MerkleTrees implements Serializable {

    private List<String> txHashes;

    private String rootHash;

    public MerkleTrees(List<String> txHashes) {
        this.txHashes = txHashes;
        this.rootHash = "";
    }

    public void build(){
        List<String> tempHashList = getNewTxList(txHashes);

        while (tempHashList.size() != 1){
            tempHashList = getNewTxList(tempHashList);
        }

        rootHash = tempHashList.get(0);
    }

    private List<String> getNewTxList(List<String> txHashes){
        List<String> newTxHashes = new ArrayList<>();

        int index = 0;

        while (index < txHashes.size()){
            //left

            String left = txHashes.get(index);
            index ++;

            //right
            //进行这一步初始化的原因是不计算奇数交易的最后一个交易复制的hash
            String right = "";
            if (index != txHashes.size()){
                right = txHashes.get(index);
            }

            //sha256 sum
            String shaHexValue = Sha256Hash.sha256Hash((left + right).getBytes());
            newTxHashes.add(shaHexValue);
            index ++ ;
        }
        return newTxHashes;
    }

    public List<String> getTxHashes() {
        return txHashes;
    }

    public void setTxHashes(List<String> txHashes) {
        this.txHashes = txHashes;
    }

    public String getRootHash() {
        return rootHash;
    }

    public void setRootHash(String rootHash) {
        this.rootHash = rootHash;
    }
}
