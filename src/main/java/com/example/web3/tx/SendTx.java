package com.example.web3.tx;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.web3j.abi.datatypes.Address;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.Hash;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthGetBalance;
import org.web3j.protocol.core.methods.response.EthGetTransactionReceipt;
import org.web3j.protocol.core.methods.response.EthTransaction;
import org.web3j.utils.Convert;
import org.web3j.utils.Numeric;
import org.xmlunit.builder.Input;

import com.example.web3.Gloab.Constant;

public class SendTx {
    private static final Logger log = LoggerFactory.getLogger(SendTx.class);

    static Rpc webj = Constant.HTTPRPC;
    static Credentials wallet = Constant.WALLET;
    static String hash = Constant.TXHASH;

    public static void main(String[] args) throws IOException {
        // getValue();
        // getTransaction();
        // getReceipt();
        keccak256("Permit(address,address,address,uint160,uint48,uint48)");
    }

    // 查询余额
    public static void getValue() throws IOException {
        EthGetBalance ethGetBalance = webj.ethGetBalance(wallet.getAddress(), DefaultBlockParameterName.LATEST)
                .send();
        BigDecimal balance = Convert.fromWei(ethGetBalance.getBalance().toString(), Convert.Unit.ETHER);
        log.info(wallet.getAddress() + " Balance: " + balance + " ETH");
    }

    // 查询交易
    public static void getTransaction() throws IOException {
        EthTransaction transaction = webj.ethGetTransactionByHash(hash).send();
        ;

        log.info(transaction.getTransaction().toString());
    }

    // 获取交易收据
    public static void getReceipt() throws IOException {
        EthGetTransactionReceipt receipt = webj.ethGetTransactionReceipt(hash).send();

        log.info(receipt.getTransactionReceipt().toString());
    }

    public static void keccak256(String s) {

        System.out.println(Numeric.toHexString(
                Hash.sha3(s.getBytes(StandardCharsets.UTF_8))));
        // Hash.sha3("Permit(address,address,address,uint160,uint48,uint48)".getBytes(StandardCharsets.UTF_8))));

    }
}
