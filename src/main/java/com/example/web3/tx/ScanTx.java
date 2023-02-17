package com.example.web3.tx;

import java.util.List;

import org.web3j.crypto.Credentials;
import org.web3j.protocol.core.methods.response.*;

import com.example.web3.Gloab.Constant;
import com.example.web3.model.Rpc;

public class ScanTx {

    // private static final Logger log = LoggerFactory.getLogger(ScanTx.class);

    static Rpc web3j = Constant.HTTPRPC;
    static Credentials wallet = Constant.WALLET;
    static String hash = Constant.TXHASH;

    public static void main(String[] args) {
        BlockchainBlockNumberListener();
    }

    public static void BlockchainBlockNumberListener() {
        web3j.blockFlowable(false).subscribe(block -> {
            List<EthBlock.TransactionResult> transactions = block.getBlock().getTransactions();
            System.out.println("block number :" + block.getBlock().getNumber());
            for (int i = 0; i < transactions.size(); i++) {
                EthBlock.TransactionResult transactionResult = transactions.get(i);
                String transactionHash = (String) transactionResult.get();
                EthGetTransactionReceipt receipt = web3j.ethGetTransactionReceipt(transactionHash).send();
                TransactionReceipt transactionReceipt = receipt.getTransactionReceipt().get();
                //处理TransactionReceipt
                
                List<Log> logs = transactionReceipt.getLogs();
                for (int j = 0; j < logs.size(); j++) {
                    Log log = logs.get(j);
                    // 处理日志
                    System.out.println(log);
                }
//                System.out.println(transactionReceipt.toString());
            }
        });
    }


}
