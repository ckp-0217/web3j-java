package com.example.web3;

import java.io.IOException;
import java.net.ConnectException;
import java.util.concurrent.ExecutionException;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthBlockNumber;
import org.web3j.protocol.core.methods.response.EthGasPrice;
import org.web3j.protocol.core.methods.response.EthTransaction;
import org.web3j.protocol.core.methods.response.Transaction;
import org.web3j.protocol.http.HttpService;
import org.web3j.protocol.websocket.WebSocketService;

import io.reactivex.disposables.Disposable;

@SpringBootTest
public class ScanService {
    public static void main(String[] args) {
        Web3j client = Web3j
                .build(new HttpService("https://eth-goerli.g.alchemy.com/v2/shdu4odG9tIK91rz0J9cXOcJZYM5tg06"));
    }

    @Test // 订阅节点 http 查询gas
    public void subscribeHttp() throws InterruptedException, ExecutionException {

        Web3j client = Web3j
                .build(new HttpService("https://eth-goerli.g.alchemy.com/v2/shdu4odG9tIK91rz0J9cXOcJZYM5tg06"));
        EthGasPrice ethGasPrice = client.ethGasPrice().sendAsync().get();
        System.out.println("ethGasPrice: " + ethGasPrice.getGasPrice());
    }

    @Test // 订阅节点 wss 查询区块号
    public void subscribeWss() throws InterruptedException, ExecutionException, ConnectException {

        WebSocketService webSocketService = new WebSocketService(
                "wss://eth-goerli.g.alchemy.com/v2/shdu4odG9tIK91rz0J9cXOcJZYM5tg06", false);
        webSocketService.connect();
        Web3j client = Web3j.build(webSocketService);
        EthBlockNumber ethBlockNumber = client.ethBlockNumber().sendAsync().get();
        System.out.println("ethBlockNumber: " + ethBlockNumber.getBlockNumber());
    }

    @Test // http查询交易
    public void creatWsInTx() throws InterruptedException, ExecutionException, IOException {
        Web3j client = Web3j
                .build(new HttpService("https://eth-goerli.g.alchemy.com/v2/shdu4odG9tIK91rz0J9cXOcJZYM5tg06"));
        String transactionHash = "0x1ba510c7dc00b37abe62d710b9ab8d3d225f56303756e925ab639e0f619d3c5f"; // 交易的 hash
        EthTransaction transaction = client.ethGetTransactionByHash(transactionHash).send();
        Transaction result = transaction.getResult(); // 获取交易信息
        System.out.println(result.toString());
    }

    @Test // ws创建交易
    public void creatWsOnBlock() throws ConnectException {
        WebSocketService webSocketService = new WebSocketService(
                "wss://eth-goerli.g.alchemy.com/v2/shdu4odG9tIK91rz0J9cXOcJZYM5tg06", true);
        webSocketService.connect();
        Web3j client = Web3j.build(webSocketService);

    }
}
