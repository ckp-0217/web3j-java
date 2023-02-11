package com.example.web3.tx;

import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.EthBlockNumber;
import org.web3j.protocol.core.methods.response.EthGasPrice;
import org.web3j.protocol.http.HttpService;
import org.web3j.protocol.websocket.WebSocketService;

import java.net.ConnectException;
import java.util.concurrent.ExecutionException;

public class ScanService {
    public static void main(String[] args) throws ExecutionException, InterruptedException, ConnectException {
        createHttp();
        createWss();
    }

    // 订阅节点 http 查询gas
    public static Web3j createHttp() throws InterruptedException, ExecutionException {

        return Web3j.build(new HttpService("https://eth-goerli.g.alchemy.com/v2/shdu4odG9tIK91rz0J9cXOcJZYM5tg06"));
    }

    // 订阅节点 wss 查询区块号
    public static Web3j createWss() throws InterruptedException, ExecutionException, ConnectException {

        WebSocketService webSocketService = new WebSocketService("ws://172.16.2.157:33334", false);
        webSocketService.connect();

        return Web3j.build(webSocketService, 2000L, org.web3j.utils.Async.defaultExecutorService());

    }

    public static WebSocketService createWsSer() throws InterruptedException, ExecutionException, ConnectException {

        WebSocketService webSocketService = new WebSocketService("ws://172.16.2.157:3334", false);
        webSocketService.connect();

        return webSocketService;

    }

    public static void subscribe() throws ConnectException, InterruptedException, ExecutionException {

        EthGasPrice ethGasPrice = createHttp().ethGasPrice().sendAsync().get();
        System.out.println("http ethGasPrice: " + ethGasPrice.getGasPrice());

        EthBlockNumber ethBlockNumber = createWss().ethBlockNumber().sendAsync().get();
        System.out.println("wss ethBlockNumber: " + ethBlockNumber.getBlockNumber());
    }

    // http创建交易
    public static void creatWsInTx() throws ConnectException, InterruptedException, ExecutionException {
        Web3j client = createHttp();

    }

    // ws创建交易
    public static void creatWsOnBlock() throws ConnectException {
        WebSocketService webSocketService = new WebSocketService(
                "wss://eth-goerli.g.alchemy.com/v2/shdu4odG9tIK91rz0J9cXOcJZYM5tg06", true);
        webSocketService.connect();
        Web3j client = Web3j.build(webSocketService);
    }
}
