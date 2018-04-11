package com.lianweiq.Block;


import com.lianweiq.Block.Block;
import com.lianweiq.Block.BlockHead;
import com.lianweiq.Tx.Transaction;
import com.lianweiq.Tx.Vin;
import com.lianweiq.Tx.Vout;
import com.lianweiq.Tx.TransactionService;
import com.lianweiq.merkle.MerkleService;
import com.lianweiq.util.ByteUtil;
import com.lianweiq.util.Sha256Hash;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author lianweiq
 * @Date 2018/3/29,
 * @Time 17:12
 * @Description 区块服务类
 */
public class BlockService {

    private static TransactionService transactionService = new TransactionService();

    private static MerkleService merkleService = new MerkleService();

    public static Block buildBlock(List<Transaction> transactions,String parentHash){

        Block block = new Block();
        BlockHead blockHead = new BlockHead();
        blockHead.setDate(new Date());
        blockHead.setVersion("1.0");
        blockHead.setParentHash(parentHash);
        blockHead.setMerkleRootHash(merkleService.buildMerkleRoot(transactions));
        block.setBlockHead(blockHead);
        List<String> txHashes = new ArrayList<>();
        block.setTxHashs(txHashes);
        block.setTxCount(transactions.size());
        for (Transaction tx : transactions){
            Transaction tempTx = transactionService.txCopy(tx);
            byte[] data = ByteUtil.objectToBytes(tempTx);
            String txHash = Sha256Hash.sha256Hash(data);
            txHashes.add(txHash);
        }
        String currentHash = Sha256Hash.sha256Hash(ByteUtil.objectToBytes(block));
        blockHead.setCurrentHash(currentHash);
        return block;
    }


    public static void main(String[] args){
        Transaction transaction = new Transaction();
        Vin vin = new Vin();
        vin.setSignature("bbbbbbbbbbbbbbbb");
        vin.setPubKey("sssssssssssssssss");
        vin.setSigDate(new Date());
        Vout vout = new Vout();
        vout.setDonateTime(1024);
        vout.setPubHash("hhhhhhhhhhhhhhhh");
        transaction.setVin(vin);
        transaction.setVout(vout);
        transaction.setTxId("No.1");

        List<Transaction> transactions = new ArrayList<>();
        transactions.add(transaction);

        Block block = buildBlock(transactions,"agscygsc");

        MerkleService merkleService = new MerkleService();
        String merkleRoot = merkleService.buildMerkleRoot(transactions);
        System.out.println("currentHash = " + block.getBlockHead().getCurrentHash());
        System.out.println("merkleRoot = " +merkleRoot);
    }

}
