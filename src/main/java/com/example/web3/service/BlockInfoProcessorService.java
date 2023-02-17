package com.example.web3.service;

import java.util.List;

import org.web3j.crypto.Credentials;
import org.web3j.protocol.core.methods.response.*;
import org.web3j.protocol.core.methods.response.EthBlock.TransactionResult;
import org.web3j.protocol.core.methods.response.Transaction;
import com.example.web3.model.Rpc;
import com.example.web3.util.Constant;


public class BlockInfoProcessorService {
    // private static final Logger log = LoggerFactory.getLogger(ScanTx.class);

    static Rpc web3j = Constant.HTTPRPC_GOERLI;
    static Credentials wallet = Constant.WALLET;
    static String hash = Constant.TXHASH;
    static TransactionProcessorService transactionProcessorService=new TransactionProcessorService();



    public static void main(String[] args) {
        start();
    }

    public static void start() {
        web3j.blockFlowable(false).subscribe(block -> {
            List<TransactionResult> transactions = block.getBlock().getTransactions();
            System.out.println("block number :" + block.getBlock().getNumber());
            for (int i = 0; i < transactions.size(); i++) {
                TransactionResult transactionResult = transactions.get(i);
                String transactionHash = (String) transactionResult.get();
                transactionProcessorService.processTransactionByHash(transactionHash);





                //获取到了交易hash
                // EthGetTransactionReceipt receipt = web3j.ethGetTransactionReceipt(transactionHash).send();
                // TransactionReceipt transactionReceipt = receipt.getTransactionReceipt().get();
                // 处理TransactionReceipt


                // List<Log> logs = transactionReceipt.getLogs();
                // for (int j = 0; j < logs.size(); j++) {
                //     Log log = logs.get(j);
                //     // 处理日志
                //     System.out.println(log);
                // }
                // System.out.println(transactionReceipt.toString());
            }
        });
    }

}
