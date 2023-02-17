package com.example.web3.service;

import java.io.IOException;
import java.util.List;

import org.web3j.protocol.core.methods.response.EthGetTransactionReceipt;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.Transaction;
import org.web3j.protocol.core.methods.response.TransactionReceipt;

import com.example.web3.model.Rpc;
import com.example.web3.util.Constant;

public class TransactionProcessorService {
    static Rpc web3j = Constant.HTTPRPC_MAIN;

    public void processTransaction(Transaction tx) throws IOException {
        // 获取交易receipt
        EthGetTransactionReceipt receipt = web3j.ethGetTransactionReceipt(tx.getHash()).send();
        TransactionReceipt transactionReceipt = receipt.getTransactionReceipt().get();
        // 处理TransactionReceipt;
        if (null == transactionReceipt) {
            return;
        }

        List<Log> logs = transactionReceipt.getLogs();
        for (int j = 0; j < logs.size(); j++) {
            Log log = logs.get(j);
            // 处理日志
            System.out.println("tx : " + j + "index: " + log.getLogIndex() + " address: " + log.getAddress()
                    + " topic: " + log.getTopics() + " data: " + log.getData());
        }
    }

    public void processTransactionByHash(String hash) throws IOException {
        processTransaction(web3j.ethGetTransactionByHash(hash).send().getResult());
    }

}
