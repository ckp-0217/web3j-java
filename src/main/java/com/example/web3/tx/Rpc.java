package com.example.web3.tx;

import org.web3j.protocol.Web3jService;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.JsonRpc2_0Web3j;
import org.web3j.protocol.core.Request;
import org.web3j.protocol.core.methods.response.EthBlock;

import java.util.Arrays;
import java.util.concurrent.ScheduledExecutorService;

public class Rpc extends JsonRpc2_0Web3j {
    public Rpc(Web3jService web3jService) {
        super(web3jService);
    }

    public Rpc(Web3jService web3jService, long pollingInterval, ScheduledExecutorService scheduledExecutorService) {
        super(web3jService, pollingInterval, scheduledExecutorService);
    }
    public Request<?, EthBlock> ethGetPending() {
        return new Request<>(
                "txpool_content",
                null,
                web3jService,
                EthBlock.class);
    }
}
