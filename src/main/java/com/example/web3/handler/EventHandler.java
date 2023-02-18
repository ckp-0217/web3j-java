package com.example.web3.handler;

import org.web3j.abi.datatypes.Event;
import org.web3j.protocol.core.methods.response.Log;

public interface EventHandler {
    void handle(Event event, Log log);
}
