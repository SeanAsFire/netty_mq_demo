package com.lianweiq.Tx;


import com.lianweiq.Tx.Transaction;
import com.lianweiq.Tx.Vin;

/**
 * @author lianweiq
 * @Date 2018/3/29,
 * @Time 16:25
 * @Description
 */
public class TransactionService {

    public boolean validTx(Transaction tx){

        return false;
    }

    public Transaction txCopy(Transaction tx){
        Transaction txcopy = new Transaction();
        txcopy.setTxId(tx.getTxId());

        txcopy.setVout(tx.getVout());
        Vin vin = new Vin();
        vin = tx.getVin();
        vin.setSignature(null);

        txcopy.setVin(vin);
        return txcopy;
    }
}
