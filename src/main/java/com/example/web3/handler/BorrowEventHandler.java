package com.example.web3.handler;

import java.util.List;
import java.math.BigInteger;
import org.web3j.abi.EventValues;
import org.web3j.abi.datatypes.Event;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.tx.Contract;
import org.web3j.abi.datatypes.Type;

import com.example.web3.contract.LendingPool.BorrowEventResponse;
import com.example.web3.contract.LendingPool.DepositEventResponse;
import com.example.web3.util.function;

public class BorrowEventHandler implements EventHandler {
    private static final BorrowEventHandler instance = new BorrowEventHandler();

    private BorrowEventHandler() {
    }

    public static BorrowEventHandler getInstance() {
        return instance;
    }

    @Override
    public void handle(Event event, Log log) {
        // TODO Auto-generated method stub

        EventValues eventValues = function.staticExtractEventParameters(event, log);
        List<Type> indexedValues = eventValues.getIndexedValues();
        List<Type> nonIndexedValues = eventValues.getNonIndexedValues();
        BorrowEventResponse typedResponse = new BorrowEventResponse();
        typedResponse.log = log;
        typedResponse.reserve = (String) indexedValues.get(0).getValue();
        typedResponse.onBehalfOf = (String) indexedValues.get(1).getValue();
        typedResponse.referral = (BigInteger) indexedValues.get(2).getValue();

        typedResponse.user = (String) nonIndexedValues.get(0).getValue();
        typedResponse.amount = (BigInteger) nonIndexedValues.get(1).getValue();
        typedResponse.borrowRateMode = (BigInteger) nonIndexedValues.get(2).getValue();
        typedResponse.borrowRate = (BigInteger) nonIndexedValues.get(3).getValue();

        System.out.println(typedResponse);

    }

}
