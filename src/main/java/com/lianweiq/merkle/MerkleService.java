package com.lianweiq.merkle;


import com.lianweiq.Tx.Transaction;
import com.lianweiq.util.ByteUtil;
import com.lianweiq.util.Sha256Hash;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lianweiq
 * @Date 2018/3/29,
 * @Time 17:47
 * @Description 获取交易merkle树根hash
 */
public class MerkleService {


    /**
     * 创建merkle树
     * @param transactions
     * @return  merkle树根
     */
    public String buildMerkleRoot(List<Transaction> transactions){

        if (transactions == null || transactions.size() <= 0){
            return "";
        }
        List<String> txHashes = new ArrayList<>();
        for (Transaction transaction:transactions){
            byte[] data = ByteUtil.objectToBytes(transaction);
            String txHash = Sha256Hash.sha256Hash(data);
            txHashes.add(txHash);
        }
        MerkleTrees merkleTrees = new MerkleTrees(txHashes);
        merkleTrees.build();
        return merkleTrees.getRootHash();
    }
}
