//package com.example.web3.service;
//
//import org.web3j.protocol.Web3j;
//import org.web3j.protocol.core.DefaultBlockParameter;
//import org.web3j.protocol.core.methods.response.EthBlock;
//
//import javax.sql.DataSource;
//import javax.xml.stream.EventFilter;
//import java.math.BigInteger;
//import java.util.List;
//import java.util.concurrent.Executors;
//
//public class TransactionListener {
//    private static final long POLLING_INTERVAL = 1000; // 轮询间隔1秒
//    private static final int MAX_RETRY_COUNT = 3; // 最大重试次数
//    private static final int RETRY_INTERVAL = 5000; // 重试间隔5秒
//    private final Web3j web3j;
//    private final List<EventFilter> eventFilters;
//    private final DataSource dataSource;
//    private final int chainId;
//    private final long startBlockNumber;
//    private long lastScannedBlockNumber;
//
//    public TransactionListener(Web3j web3j, List<EventFilter> eventFilters, DataSource dataSource, int chainId, long startBlockNumber) {
//        this.web3j = web3j;
//        this.eventFilters = eventFilters;
//        this.dataSource = dataSource;
//        this.chainId = chainId;
//        this.startBlockNumber = startBlockNumber;
//        this.lastScannedBlockNumber = getLastScannedBlockNumber();
//    }
//
//    public void start() {
//        Executors.newSingleThreadScheduledExecutor().scheduleWithFixedDelay(() -> {
//            try {
//                // 获取当前最新块高度
//                long latestBlockNumber = web3j.ethBlockNumber().send().getBlockNumber().longValue();
//                // 从上次扫描到的最新块开始轮询
//                for (long i = lastScannedBlockNumber + 1; i <= latestBlockNumber; i++) {
//                    EthBlock.Block block = web3j.ethGetBlockByNumber(DefaultBlockParameter.valueOf(BigInteger.valueOf(i)), true).send().getBlock();
//                    if (block != null) {
//                        // 处理块中的交易
//                        handleBlock(block);
//                        // 更新已扫描的最新块高度
//                        lastScannedBlockNumber = block.getNumber().longValue();
//                        saveLastScannedBlockNumber(lastScannedBlockNumber);
//                    }
//                }
//            } catch (IOException e) {
//                // 处理异常，重试
//                handleException(e);
//            }
//        }, 0, POLLING_INTERVAL, TimeUnit.MILLISECONDS);
//    }
//
//    private void saveLastScannedBlockNumber(long lastScannedBlockNumber) {
//    }
//
//    private void handleBlock(EthBlock.Block block) {
//        List<EthBlock.TransactionResult> transactions = block.getTransactions();
//        if (transactions == null) {
//            return;
//        }
//        for (EthBlock.TransactionResult result : transactions) {
//            if (result instanceof EthBlock.TransactionObject) {
//                EthBlock.TransactionObject tx = (EthBlock.TransactionObject) result;
//                String txHash = tx.getHash();
//                String from = tx.getFrom();
//                String to = tx.getTo();
//                BigInteger value = tx.getValue();
//                // 处理交易过滤器中需要的事件
//                for (EventFilter eventFilter : eventFilters) {
////                    processEventFilter(eventFilter, block, txHash, from, to, value);
//                }
//                // 存储交易信息到数据库
////                saveTransaction(txHash, from, to, value);
//            }
//        }
//    }
//
////    private void processEventFilter(EventFilter eventFilter
