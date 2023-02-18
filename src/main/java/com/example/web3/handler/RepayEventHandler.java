package com.example.web3.handler;

import org.web3j.abi.datatypes.Event;
import org.web3j.protocol.core.methods.response.Log;

public class RepayEventHandler implements EventHandler {
    private static final RepayEventHandler instance = new RepayEventHandler();

    private RepayEventHandler() {
    }

    public static RepayEventHandler getInstance() {
        return instance;
    }

    @Override
    public void handle(Event event, Log log) {
        // TODO Auto-generated method stub

    }

}
