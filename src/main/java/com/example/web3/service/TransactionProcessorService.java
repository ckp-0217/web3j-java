package com.example.web3.service;

import java.io.IOException;
import java.util.List;

import com.example.web3.config.DefiConfig;
import org.web3j.protocol.core.methods.response.EthGetTransactionReceipt;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.Transaction;
import org.web3j.protocol.core.methods.response.TransactionReceipt;

import com.example.web3.model.Rpc;
import com.example.web3.util.Constant;
import org.web3j.tx.Contract;

public class TransactionProcessorService {
    static Rpc web3j = Constant.HTTPRPC_MAIN;
    private DefiResolver defiResolver = new DefiResolver(new DefiConfig());
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
            defiResolver.resolveEvent(log);

        }
    }

    public void processTransactionByHash(String hash) throws IOException {
        processTransaction(web3j.ethGetTransactionByHash(hash).send().getResult());
    }

}
