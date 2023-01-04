package com.example.web3.tx;

import org.web3j.protocol.Web3j;
import org.web3j.protocol.websocket.WebSocketService;

import java.net.ConnectException;

public class ScanFilterService {
    private ScanConfig config;
    private Web3j client;


    public ScanFilterService(ScanConfig config) {
        this.config = config;
    }

    public void init() throws ConnectException {
        //初始化
        WebSocketService webSocketService = new WebSocketService(config.getUrl(), false);
        webSocketService.connect();
        client=Web3j.build(webSocketService,2000L, org.web3j.utils.Async.defaultExecutorService());

    }
}
