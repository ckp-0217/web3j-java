package com.example.web3.handler;

import org.web3j.abi.datatypes.Event;
import org.web3j.protocol.core.methods.response.Log;

public class DepositEventHandler implements EventHandler {
    private static final DepositEventHandler instance = new DepositEventHandler();

    private DepositEventHandler() {
    }

    public static DepositEventHandler getInstance() {
        return instance;
    }

    @Override
    public void handle(Event event, Log log) {
        // TODO Auto-generated method stub

    }

}
