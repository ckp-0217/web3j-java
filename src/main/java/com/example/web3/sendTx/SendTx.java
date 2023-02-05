package com.example.web3.sendTx;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthBlockNumber;
import org.web3j.protocol.core.methods.response.EthGasPrice;
import org.web3j.protocol.core.methods.response.EthGetBalance;
import org.web3j.protocol.http.HttpService;
import org.web3j.protocol.websocket.WebSocketService;
import org.web3j.utils.Convert;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.ConnectException;
import java.util.concurrent.ExecutionException;


public class SendTx {
    private static final Logger log = LoggerFactory.getLogger(SendTx.class);

    public static void main(String[] args) throws IOException {
        Web3j client = Web3j.build(new HttpService("https://eth-goerli.g.alchemy.com/v2/shdu4odG9tIK91rz0J9cXOcJZYM5tg06"));
        log.info("Connected to Ethereum client version: "
                + client.web3ClientVersion().send().getWeb3ClientVersion());
    }

    @Test //订阅节点 http 查询gas
    public void subscribeHttp() throws InterruptedException, ExecutionException {

        Web3j client = Web3j.build(new HttpService("https://eth-goerli.g.alchemy.com/v2/shdu4odG9tIK91rz0J9cXOcJZYM5tg06"));
        EthGasPrice ethGasPrice = client.ethGasPrice().sendAsync().get();
        System.out.println("ethGasPrice: " + ethGasPrice.getGasPrice());
    }

    @Test //订阅节点 wss 查询区块号
    public void subscribeWss() throws InterruptedException, ExecutionException, ConnectException {

        WebSocketService webSocketService = new WebSocketService("wss://eth-goerli.g.alchemy.com/v2/shdu4odG9tIK91rz0J9cXOcJZYM5tg06", false);
        webSocketService.connect();
        Web3j client = Web3j.build(webSocketService);
        EthBlockNumber ethBlockNumber = client.ethBlockNumber().sendAsync().get();
        System.out.println("ethBlockNumber: " + ethBlockNumber.getBlockNumber());
    }

    @Test //创建钱包 并且查询余额
    public void creatWallet() throws IOException, InterruptedException, ExecutionException {
        Web3j web3j = Web3j.build(new HttpService("https://eth-goerli.g.alchemy.com/v2/shdu4odG9tIK91rz0J9cXOcJZYM5tg06"));

        Credentials credentials = Credentials.create("6e418775cd69c068e32883e5c9d2a61e84b5a567191a2690cb92c5c6796f2d31");
        EthGetBalance ethGetBalance = web3j.ethGetBalance("0x742d35Cc6634C0532925a3b844Bc454e4438f44e", DefaultBlockParameterName.LATEST).send();
        BigDecimal balanceInEther = Convert.fromWei(ethGetBalance.getBalance().toString(), Convert.Unit.ETHER);

        System.out.println("Address balance: " + balanceInEther + " ETH");
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
