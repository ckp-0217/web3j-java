package com.example.web3.tx;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

import com.example.web3.contract.Ballot;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.Hash;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.response.EthGetBalance;
import org.web3j.protocol.core.methods.response.EthGetTransactionReceipt;
import org.web3j.protocol.core.methods.response.EthTransaction;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.gas.ContractGasProvider;
import org.web3j.tx.gas.DefaultGasProvider;
import org.web3j.tx.gas.StaticEIP1559GasProvider;
import org.web3j.utils.Convert;
import org.web3j.utils.Numeric;

import com.example.web3.Gloab.Constant;

public class SendTx {
    private static final Logger log = LoggerFactory.getLogger(SendTx.class);

    static Rpc web3j = Constant.HTTPRPC;
    static Credentials wallet = Constant.WALLET;
    static String hash = Constant.TXHASH;
    static ContractGasProvider contractGasProvider = new StaticEIP1559GasProvider(5, new BigInteger("1000000000"), new BigInteger("500000000"), new BigInteger("10000000"));

    public static void main(String[] args) throws Exception {
//        getValue();
//        getTransaction();
//        getReceipt();
//        keccak256("Permit(address,address,address,uint160,uint48,uint48)");
        deploy();
//        load();
    }

    // 查询余额
    public static void getValue() throws IOException {
        EthGetBalance ethGetBalance = web3j.ethGetBalance(wallet.getAddress(), DefaultBlockParameterName.LATEST)
                .send();
        BigDecimal balance = Convert.fromWei(ethGetBalance.getBalance().toString(), Convert.Unit.ETHER);
        log.info(wallet.getAddress() + " Balance: " + balance + " ETH");
    }

    // 查询交易
    public static void getTransaction() throws IOException {
        EthTransaction transaction = web3j.ethGetTransactionByHash(hash).send();
        ;

        log.info(transaction.getTransaction().toString());
    }

    // 获取交易收据
    public static void getReceipt() throws IOException {
        EthGetTransactionReceipt receipt = web3j.ethGetTransactionReceipt(hash).send();

        log.info(receipt.getTransactionReceipt().toString());
    }

    public static void keccak256(@NotNull String s) {

        System.out.println(Numeric.toHexString(
                Hash.sha3(s.getBytes(StandardCharsets.UTF_8))));
        // Hash.sha3("Permit(address,address,address,uint160,uint48,uint48)".getBytes(StandardCharsets.UTF_8))));

    }

    //获取当前链上gas
    //部署合约
    public static void deploy() throws Exception {
        // 创建ContractGasProvider
        Ballot ballot = Ballot.deploy(
                web3j,
                wallet,
                contractGasProvider
        ).send();
        Optional<TransactionReceipt> transactionReceipt = ballot.getTransactionReceipt();
        String ballotAddress = ballot.getContractAddress();
        log.info("Smart contract deployed to address " + ballotAddress);

    }

    //载入合约
    public static void load() throws Exception {
        Ballot ballot = Ballot.load(
                "0x152ab76c8c00d43039cac7e6ae5953233d631c66",
                web3j,
                wallet,
                contractGasProvider
        );
        TransactionReceipt send = ballot.vote(new BigInteger("10")).send();

    }

}
