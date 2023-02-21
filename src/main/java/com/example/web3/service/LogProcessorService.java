package com.example.web3.service;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.web3j.crypto.Credentials;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.request.Filter;
import org.web3j.protocol.core.methods.response.*;
import org.web3j.protocol.core.methods.response.EthBlock.TransactionResult;
import org.web3j.protocol.core.methods.response.Transaction;

import com.example.web3.config.DefiConfig;
import com.example.web3.parser.AaveParser;
import com.example.web3.util.Constant;
import com.example.web3.util.Rpc;

public class LogProcessorService {
    // private static final Logger log = LoggerFactory.getLogger(ScanTx.class);

    static Rpc web3j = Constant.HTTPRPC_MAIN;
    static Credentials wallet = Constant.WALLET;
    static String hash = Constant.TXHASH;
    static TransactionProcessorService transactionProcessorService = new TransactionProcessorService();

    public static void main(String[] args) {
        start();
    }

    public static void start() {
        ExecutorService executorService = Executors.newFixedThreadPool(8);

        AaveParser aaveParser = new AaveParser();
        List<String> topicList = aaveParser.topicList;
        DefiResolver defiResolver = new DefiResolver(new DefiConfig());

        for (int i = 0; i < topicList.size(); i++) {
            String topic = topicList.get(i);
            executorService.submit(() -> {
                org.web3j.protocol.core.methods.request.EthFilter filter = new EthFilter().addSingleTopic(topic);
                web3j.ethLogFlowable(filter).subscribe(log -> {
                    defiResolver.resolveEvent(log);
                });
            });

        }

    }

}
