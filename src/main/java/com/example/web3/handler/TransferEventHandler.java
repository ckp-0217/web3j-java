package com.example.web3.handler;

import java.util.List;
import java.math.BigInteger;
import org.web3j.abi.EventValues;
import org.web3j.abi.FunctionReturnDecoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Event;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Uint256;

import com.example.web3.contract.Dai.TransferEventResponse;
import com.example.web3.handler.EventHandler;
import com.example.web3.util.function;

public class TransferEventHandler implements EventHandler {
    private static final TransferEventHandler instance = new TransferEventHandler();

    private TransferEventHandler() {
    }

    public static TransferEventHandler getInstance() {
        return instance;
    }

    @Override
    public void handle(Event event, Log log) {

        EventValues eventValues = function.staticExtractEventParameters(event, log);
        List<Type> indexedValues = eventValues.getIndexedValues();
        List<Type> nonIndexedValues = eventValues.getNonIndexedValues();
        TransferEventResponse typedResponse = new TransferEventResponse();
        typedResponse.log = log;
        typedResponse.log = log;
        typedResponse.from = (String) indexedValues.get(0).getValue();
        typedResponse.to = (String) indexedValues.get(1).getValue();

        if (!nonIndexedValues.isEmpty()) {
            typedResponse.value = (BigInteger) nonIndexedValues.get(0).getValue();

        } else {
            // The `value` parameter is indexed, so decode it from the third topic
            TypeReference<Uint256> typeRef = new TypeReference<Uint256>() {
            };
            String topic = log.getTopics().get(2);
            Type value = FunctionReturnDecoder.decodeIndexedValue(topic, typeRef);
            typedResponse.value = (BigInteger) value.getValue();

        }

        System.out
                .println("在区块 " + log.getBlockHash() + " 用户：" + typedResponse.from + "转给" + typedResponse.to + " "
                        + typedResponse.value + " 的 "
                        + log.getAddress());

    }

}
