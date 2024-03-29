package com.example.web3.tx;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;


import com.example.web3.contract.Ballot;
import com.example.web3.contract.Dai;
import com.example.web3.contract.LendingPool;
import com.example.web3.util.Constant;
import com.example.web3.util.Rpc;

import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.web3j.abi.FunctionEncoder;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.Hash;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.response.*;
import org.web3j.protocol.core.methods.request.Transaction;

import org.web3j.tx.FastRawTransactionManager;
import org.web3j.tx.gas.ContractGasProvider;
import org.web3j.tx.gas.DefaultGasProvider;
import org.web3j.tx.gas.StaticEIP1559GasProvider;
import org.web3j.utils.Convert;
import org.web3j.utils.Numeric;


public class SendTx {
    private static final Logger log = LoggerFactory.getLogger(SendTx.class);

    static Rpc web3j = Constant.HTTPRPC_GOERLI;
    static Credentials wallet = Constant.WALLET;
    static String hash = Constant.TXHASH;

    public static void main(String[] args) throws Exception {
        // getValue();
        // getTransaction();
        // getReceipt();
        // keccak256("Permit(address,address,address,uint160,uint48,uint48)");
        // deploy();
//        loadAndSendTx();
//        asyncSendTx();
        EstimateGasSendTx();
//        EstimateGas();
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

    public static ContractGasProvider getGasProvider() throws Exception {
        // 创建ContractGasProvider

        BigInteger chainId = web3j.ethChainId().send().getChainId();
        BigInteger eth_maxPriorityFeePerGas= web3j.ethMaxPriorityFeePerGas().send().getMaxPriorityFeePerGas();

        EthBlock block = web3j.ethGetBlockByNumber(DefaultBlockParameterName.LATEST, false).send();
        BigInteger maxPreGas = block.getBlock().getBaseFeePerGas().multiply(new BigInteger("2")).add(eth_maxPriorityFeePerGas);
        return new StaticEIP1559GasProvider(chainId.longValue(), maxPreGas, eth_maxPriorityFeePerGas, new BigInteger("1000000"));
    }

    public static ContractGasProvider getGasProvider(BigInteger gasUsed) throws Exception {
        // 创建ContractGasProvider
        BigInteger gasLimit = gasUsed.multiply(new BigInteger("12")).divide(new BigInteger("10"));
        BigInteger chainId = web3j.ethChainId().send().getChainId();
        BigInteger eth_maxPriorityFeePerGas= web3j.ethMaxPriorityFeePerGas().send().getMaxPriorityFeePerGas();

        EthBlock block = web3j.ethGetBlockByNumber(DefaultBlockParameterName.LATEST, false).send();
        BigInteger maxPreGas = block.getBlock().getBaseFeePerGas().multiply(new BigInteger("2")).add(eth_maxPriorityFeePerGas);
        return new StaticEIP1559GasProvider(chainId.longValue(), maxPreGas, eth_maxPriorityFeePerGas, gasLimit);
    }

    // 部署合约
    public static void deploy() throws Exception {
        // 创建ContractGasProvider
        Ballot ballot = Ballot.deploy(
                web3j,
                wallet,
                getGasProvider()).send();
        Optional<TransactionReceipt> transactionReceipt = ballot.getTransactionReceipt();
        String ballotAddress = ballot.getContractAddress();
        log.info("Smart contract deployed to address " + ballotAddress);

    }

    // 载入合约 并交易
    public static void loadAndSendTx() throws Exception {
        // 玩一下测试网的AAVE
        LendingPool lendingPool = LendingPool.load(
                "0x4bd5643ac6f66a5237E18bfA7d47cF22f1c9F210",
                web3j,
                wallet,
                getGasProvider());
        Dai dai = Dai.load(
                "0x75Ab5AB1Eef154C0352Fc31D2428Cef80C7F8B33",
                web3j,
                wallet,
                getGasProvider());
        TransactionReceipt approve = dai.approve(
                "0x4bd5643ac6f66a5237E18bfA7d47cF22f1c9F210",
                new BigInteger("100000000000000000000")).send();
        log.info("DAI approve : " + approve.getTransactionHash());
        ethScan(approve.getTransactionHash());

        TransactionReceipt deposit = lendingPool.deposit(
                "0x75Ab5AB1Eef154C0352Fc31D2428Cef80C7F8B33",
                new BigInteger("1000000000000000000"),
                "0xbc593fdb62ee1c1df173ad695f05689db60c28f8",
                new BigInteger("0")).send();
        log.info("lendingPool deposit : " + deposit.getTransactionHash());
        ethScan(deposit.getTransactionHash());
    }

    // 估算交易
    public static void EstimateGas() throws Exception {
        Dai dai = Dai.load(
                "0x75Ab5AB1Eef154C0352Fc31D2428Cef80C7F8B33",
                web3j,
                wallet,
                getGasProvider());
        //false
        org.web3j.abi.datatypes.Function function = dai.transferFunction("0x4bd5643ac6f66a5237E18bfA7d47cF22f1c9F210", new BigInteger("1000000000000000000000"));
        String encodedFunction = FunctionEncoder.encode(function);

        EthEstimateGas ethEstimateGas = web3j.ethEstimateGas(
                        Transaction.createEthCallTransaction(
                                wallet.getAddress(),
                                "0x75Ab5AB1Eef154C0352Fc31D2428Cef80C7F8B33",
                                encodedFunction))
                .send();
        if (null != ethEstimateGas.getError()) {
            log.info("ethEstimateGas :" + ethEstimateGas.getId());
            log.info("ethEstimateGas :" + ethEstimateGas.getError().getData());
            log.info("ethEstimateGas :" + ethEstimateGas.getError().getMessage());
        } else {
            log.info("ethEstimateGas :" + ethEstimateGas.getAmountUsed());

        }

        //true
        function = dai.transferFunction("0x4bd5643ac6f66a5237E18bfA7d47cF22f1c9F210", new BigInteger("100000000"));
        encodedFunction = FunctionEncoder.encode(function);
        ethEstimateGas = web3j.ethEstimateGas(
                        Transaction.createEthCallTransaction(
                                wallet.getAddress(),
                                "0x75Ab5AB1Eef154C0352Fc31D2428Cef80C7F8B33",
                                encodedFunction))
                .send();
        if (null != ethEstimateGas.getError()) {
            log.info("ethEstimateGas :" + ethEstimateGas.getError().getCode());
            log.info("ethEstimateGas :" + ethEstimateGas.getError().getData());
            log.info("ethEstimateGas :" + ethEstimateGas.getError().getMessage());
        } else {
            log.info("ethEstimateGas :" + ethEstimateGas.getAmountUsed());
            log.info("ethEstimateGas :" + ethEstimateGas.getId());

        }
    }

    // 使用预估gas进行交易
    public static void EstimateGasSendTx() throws Exception {
        Dai dai = Dai.load(
                "0x75Ab5AB1Eef154C0352Fc31D2428Cef80C7F8B33",
                web3j,
                wallet,
                getGasProvider());
        //true
        org.web3j.abi.datatypes.Function function = dai.transferFunction("0x4bd5643ac6f66a5237E18bfA7d47cF22f1c9F210", new BigInteger("10"));
        String encodedFunction = FunctionEncoder.encode(function);

        EthEstimateGas ethEstimateGas = web3j.ethEstimateGas(
                        Transaction.createEthCallTransaction(
                                wallet.getAddress(),
                                "0x75Ab5AB1Eef154C0352Fc31D2428Cef80C7F8B33",
                                encodedFunction))
                .send();
        if (null != ethEstimateGas.getError()) {
            log.info("ethEstimateGas :" + ethEstimateGas.getError().getMessage());
        } else {
            log.info("ethEstimateGas :" + ethEstimateGas.getAmountUsed());
            dai.setGasProvider(getGasProvider(ethEstimateGas.getAmountUsed()));
            TransactionReceipt transfer = dai.transfer("0x4bd5643ac6f66a5237E18bfA7d47cF22f1c9F210", new BigInteger("10")).send();
            ethScan(transfer.getTransactionHash());
            

        }
    }

    public static void ethScan(String hash) {
        log.info("https://goerli.etherscan.io/tx/" + hash);
    }

    // 异步发送交易
    public static void asyncSendTx() throws Exception {

        FastRawTransactionManager fastRawTransactionManager = new FastRawTransactionManager(web3j, wallet);

        LendingPool lendingPool = LendingPool.load(
                "0x4bd5643ac6f66a5237E18bfA7d47cF22f1c9F210",
                web3j,
                fastRawTransactionManager,
                getGasProvider());
        Dai dai = Dai.load(
                "0x75Ab5AB1Eef154C0352Fc31D2428Cef80C7F8B33",
                web3j,
                fastRawTransactionManager,
                getGasProvider());
       CompletableFuture<TransactionReceipt> approve = dai
               .approve("0x4bd5643ac6f66a5237E18bfA7d47cF22f1c9F210", new BigInteger("100000000000000000000"))
               .sendAsync();
       CompletableFuture<TransactionReceipt> deposit = lendingPool.deposit(
               "0x75Ab5AB1Eef154C0352Fc31D2428Cef80C7F8B33",
               new BigInteger("1000000000000000000"),
               "0xbc593fdb62ee1c1df173ad695f05689db60c28f8",
               new BigInteger("0")).sendAsync();
       approve.thenAccept((app) -> log.info("DAI approve : " + app.getTransactionHash()));
       deposit.thenAccept((dep) -> log.info("lendingPool deposit : " + dep.getTransactionHash()));


    }


}
