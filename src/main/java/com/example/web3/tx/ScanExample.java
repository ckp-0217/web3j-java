package com.example.web3.tx;

import io.reactivex.functions.Consumer;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.Request;
import org.web3j.protocol.core.Response;
import org.web3j.protocol.core.methods.request.EthFilter;

import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.Transaction;

import org.web3j.protocol.websocket.WebSocketService;

import java.io.IOException;
import java.net.ConnectException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;

public class ScanExample {
    private static HashMap<String, Transaction> map=new HashMap<>();
    private static EthFilter filter = new EthFilter(DefaultBlockParameterName.EARLIEST,
            DefaultBlockParameterName.LATEST, "0xBC593fDb62EE1c1dF173Ad695F05689Db60C28f8");
    public static void main(String[] args) throws ExecutionException, InterruptedException, IOException {


        getPending();
    }

    public static void getPending(Consumer<? super Transaction> comparable) throws ExecutionException, InterruptedException, ConnectException {
        Web3j client = ScanService.createWss();
        client.pendingTransactionFlowable().subscribe(comparable);

    }
    public static void getComplete(Consumer<? super Transaction> comparable) throws ExecutionException, InterruptedException, ConnectException {
        Web3j client = ScanService.createWss();
        client.transactionFlowable().subscribe(comparable);

    }
    public static  void getFilterLog(EthFilter filter,Consumer<? super Log> comparable) throws ExecutionException, InterruptedException, ConnectException {
        Web3j client = ScanService.createWss();
        client.ethLogFlowable(filter).subscribe(comparable);
    }
    public static void getPendingHash(Consumer<? super String> comparable) throws ExecutionException, InterruptedException, ConnectException {
        Web3j client = ScanService.createWss();
        client.ethPendingTransactionHashFlowable().subscribe(comparable);
    }
    public static void getCompleteBlockHash(Consumer<? super String> comparable) throws ExecutionException, InterruptedException, ConnectException {
        Web3j client = ScanService.createWss();
        client.ethBlockHashFlowable().subscribe(comparable);
    }
    public static void getPending() throws ExecutionException, InterruptedException, IOException {
        WebSocketService wsSer = ScanService.createWsSer();
        while(true){
            Thread.sleep(1000);
            Response response = new Request<>("eth_gasPrice", Arrays.asList(), wsSer, Response.class).send();
            Response response2 = new Request<>("txpool_content", Arrays.asList(), wsSer, Response.class).send();
        }
    }


}

