package com.example.web3;

import java.io.Console;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.ConnectException;
import java.util.Arrays;
import java.util.concurrent.ExecutionException;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.Request;
import org.web3j.protocol.core.methods.response.EthBlockNumber;
import org.web3j.protocol.core.methods.response.EthCall;
import org.web3j.protocol.core.methods.response.EthGasPrice;
import org.web3j.protocol.core.methods.response.EthGetTransactionReceipt;
import org.web3j.protocol.core.methods.response.EthTransaction;
import org.web3j.protocol.core.methods.response.Transaction;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;
import org.web3j.protocol.websocket.WebSocketService;

import com.example.web3.response.EthTraceTransaction;
import com.example.web3.tx.Rpc;

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
    public void creatWsInTx() throws InterruptedException, ExecutionException, IOException, IllegalArgumentException, IllegalAccessException {
        
        Rpc client=new Rpc(new HttpService("https://ethnode.digifttest.com/"));
        String transactionHash = "0x0154d27d0a8fcd23daa8cfc2f98d60ff8f69db197b35384d96cde638e1ad76a1"; // 交易的 hash
        // EthTransaction transaction = client.ethGetTransactionByHash(transactionHash).send();
        // EthGetTransactionReceipt ethGetTransactionReceipt = client.ethGetTransactionReceipt(transactionHash).send();

        // Transaction result = transaction.getResult(); // 获取交易信息
        // Class<?> cls = result.getClass();
        // Field[] fields = cls.getDeclaredFields();
        // for (Field field : fields) {
        //     field.setAccessible(true);
        //     System.out.println(field.getName() + ": " + field.get(result));
        // }
        // TransactionReceipt receipt = ethGetTransactionReceipt.getResult();
        // Class<?> cls2 = receipt.getClass();
        // Field[] fields2 = cls2.getDeclaredFields();
        // for (Field field : fields2) {
        //     field.setAccessible(true);
        //     System.out.println(field.getName() + ": " + field.get(receipt));
        // }
        // System.out.println(ethGetTransactionReceipt.getResult());

        Request<?, ?> request = new Request<>(
            "debug_traceTransaction",
            Arrays.asList(transactionHash),  // 传入请求参数
            new HttpService("https://ethnode.digifttest.com/"),
            EthTraceTransaction.class
        );
        
     EthTraceTransaction send = (EthTraceTransaction) request.send();
     System.out.println(send);
    }
        

    @Test // ws创建交易
    public void creatWsOnBlock() throws ConnectException {
        WebSocketService webSocketService = new WebSocketService(
                "wss://eth-goerli.g.alchemy.com/v2/shdu4odG9tIK91rz0J9cXOcJZYM5tg06", true);
        webSocketService.connect();
        Web3j client = Web3j.build(webSocketService);

    }
}
