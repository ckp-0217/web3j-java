package com.example.web3.service;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.web3j.crypto.Credentials;
import org.web3j.protocol.core.methods.response.*;
import org.web3j.protocol.core.methods.response.EthBlock.TransactionResult;
import org.web3j.protocol.core.methods.response.Transaction;

import com.example.web3.util.Constant;
import com.example.web3.util.Rpc;

public class BlockInfoProcessorService {
    // private static final Logger log = LoggerFactory.getLogger(ScanTx.class);

    static Rpc web3j = Constant.HTTPRPC_MAIN;
    static Credentials wallet = Constant.WALLET;
    static String hash = Constant.TXHASH;
    static TransactionProcessorService transactionProcessorService = new TransactionProcessorService();

    public static void main(String[] args) {
        start();
    }

    public static void start() {
        ExecutorService executorService = Executors.newFixedThreadPool(30);
        web3j.blockFlowable(true).subscribe(block -> {
            List<TransactionResult> transactions = block.getBlock().getTransactions();
            System.out.println("block number :" + block.getBlock().getNumber());
            CountDownLatch countDownLatch = new CountDownLatch(transactions.size()); // 初始化闭锁
            for (int i = 0; i < transactions.size(); i++) {
                TransactionResult transactionResult = transactions.get(i);
                EthBlock.TransactionObject transaction = (EthBlock.TransactionObject) transactionResult.get();
                executorService.submit(() -> {
                    try {
                        transactionProcessorService.processTransaction(transaction);
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        countDownLatch.countDown(); // 子线程执行完毕，闭锁减1
                    }
                });
            }
            countDownLatch.await(); // 阻塞主线程直到闭锁数量减为0
            System.out.println("block number :" + block.getBlock().getNumber() + " 处理完成");
        });

    }

}
