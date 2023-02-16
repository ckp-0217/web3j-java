package com.example.web3.tx;

import java.util.List;

import org.web3j.crypto.Credentials;
import org.web3j.protocol.core.methods.response.EthBlock;
import org.web3j.protocol.core.methods.response.EthGetTransactionReceipt;
import org.web3j.protocol.core.methods.response.Transaction;
import org.web3j.protocol.core.methods.response.TransactionReceipt;

import com.example.web3.Gloab.Constant;


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
            for (EthBlock.TransactionResult transactionResult : transactions) {
                EthBlock.TransactionObject transaction = (EthBlock.TransactionObject) transactionResult.get();
                String txHash = transaction.getHash();
                System.out.println(txHash);
                // EthGetTransactionReceipt receipt = web3j.ethGetTransactionReceipt(txHash).send();
                // if (receipt.getTransactionReceipt().isPresent()) {
                //     TransactionReceipt transactionReceipt = receipt.getTransactionReceipt().get();
                //     // Do something with transaction and transactionReceipt
                // }
            }
        });
    }
    
}
