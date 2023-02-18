package com.example.web3.handler;

import org.web3j.abi.datatypes.Event;
import org.web3j.protocol.core.methods.response.Log;

public class WithdrawEventHandler implements EventHandler {
    private static final WithdrawEventHandler instance = new WithdrawEventHandler();

    private WithdrawEventHandler() {
    }

    public static WithdrawEventHandler getInstance() {
        return instance;
    }

    @Override
    public void handle(Event event, Log log) {
        // TODO Auto-generated method stub

    }

}
