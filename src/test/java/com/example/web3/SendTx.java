package com.example.web3;

import java.net.ConnectException;
import java.util.concurrent.ExecutionException;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthBlockNumber;
import org.web3j.protocol.core.methods.response.EthGasPrice;
import org.web3j.protocol.http.HttpService;
import org.web3j.protocol.websocket.WebSocketService;

import io.reactivex.disposables.Disposable;
@SpringBootTest
public class SendTx {
    public static void main(String[] args) {
        Web3j client = Web3j.build(new HttpService("https://eth-goerli.g.alchemy.com/v2/shdu4odG9tIK91rz0J9cXOcJZYM5tg06"));
    }
    
    @Test //订阅节点 http 查询gas
    public void subscribeHttp() throws InterruptedException, ExecutionException{

        Web3j client = Web3j.build(new HttpService("https://eth-goerli.g.alchemy.com/v2/shdu4odG9tIK91rz0J9cXOcJZYM5tg06"));
        EthGasPrice ethGasPrice = client.ethGasPrice().sendAsync().get();
        System.out.println("ethGasPrice: "+ethGasPrice.getGasPrice());
    } 
    @Test //订阅节点 wss 查询区块号
    public void subscribeWss() throws InterruptedException, ExecutionException, ConnectException{

        WebSocketService webSocketService = new WebSocketService("wss://eth-goerli.g.alchemy.com/v2/shdu4odG9tIK91rz0J9cXOcJZYM5tg06", false);
        webSocketService.connect();
        Web3j client = Web3j.build(webSocketService);
        EthBlockNumber ethBlockNumber = client.ethBlockNumber().sendAsync().get();
        System.out.println("ethBlockNumber: "+ethBlockNumber.getBlockNumber());
    } 
    @Test //http创建交易
    public void creatWsInTx() throws ConnectException, InterruptedException, ExecutionException {
        Web3j client = Web3j.build(new HttpService("https://eth-goerli.g.alchemy.com/v2/shdu4odG9tIK91rz0J9cXOcJZYM5tg06"));
        
    }   
    @Test //ws创建交易
    public void creatWsOnBlock() throws ConnectException {
        WebSocketService webSocketService = new WebSocketService("wss://eth-goerli.g.alchemy.com/v2/shdu4odG9tIK91rz0J9cXOcJZYM5tg06", true);
        webSocketService.connect();
        Web3j client = Web3j.build(webSocketService);

        
    }
}
