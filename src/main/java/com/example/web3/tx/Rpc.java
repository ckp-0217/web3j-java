package com.example.web3.tx;

import org.web3j.protocol.Web3jService;
import org.web3j.protocol.core.JsonRpc2_0Web3j;
import org.web3j.protocol.core.Request;

import com.example.web3.response.EthTraceTransaction;

import java.util.Arrays;
import java.util.concurrent.ScheduledExecutorService;

public class Rpc extends JsonRpc2_0Web3j {
    public Rpc(Web3jService web3jService) {
        super(web3jService);
    }

    public Rpc(Web3jService web3jService, long pollingInterval, ScheduledExecutorService scheduledExecutorService) {
        super(web3jService, pollingInterval, scheduledExecutorService);
    }
    public Request<?, EthTraceTransaction> debug_trace(String transactionHash) {
        return new Request<>(
                "debug_traceTransaction",
                Arrays.asList(transactionHash),
                web3jService,
                EthTraceTransaction.class);
    }


}
