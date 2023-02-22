package com.example.web3.handler.AAVE;

import java.util.List;
import java.math.BigInteger;

import org.web3j.abi.EventValues;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Type;
import org.web3j.protocol.core.methods.response.Log;

import com.example.web3.contract.LendingPool.RepayEventResponse;
import com.example.web3.handler.EventHandler;
import com.example.web3.util.function;

public class RepayEventHandler implements EventHandler {
    private static final RepayEventHandler instance = new RepayEventHandler();

    private RepayEventHandler() {
    }

    public static RepayEventHandler getInstance() {
        return instance;
    }

    @Override
    public void handle(Event event, Log log) {
        EventValues eventValues = function.staticExtractEventParameters(event, log);
        List<Type> indexedValues = eventValues.getIndexedValues();
        List<Type> nonIndexedValues = eventValues.getNonIndexedValues();
        RepayEventResponse typedResponse = new RepayEventResponse();
        typedResponse.log = log;
        typedResponse.reserve = (String) indexedValues.get(0).getValue();
        typedResponse.user = (String) indexedValues.get(1).getValue();
        typedResponse.repayer = (String) indexedValues.get(2).getValue();
        typedResponse.amount = (BigInteger) nonIndexedValues.get(0).getValue();

        System.out.println("用户："+typedResponse.user+"在AAVE偿还："+typedResponse.reserve+" "+typedResponse.amount);


    }

}
