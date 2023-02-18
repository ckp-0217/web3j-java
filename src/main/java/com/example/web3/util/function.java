package com.example.web3.util;

import java.util.ArrayList;
import java.util.List;

import org.web3j.abi.EventEncoder;
import org.web3j.abi.EventValues;
import org.web3j.abi.FunctionReturnDecoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Type;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.tx.Contract;
import org.web3j.tx.Contract.EventValuesWithLog;

public class function {

    public static EventValues staticExtractEventParameters(Event event, Log log) {
        // TODO Auto-generated method stub
        final List<String> topics = log.getTopics();
        String encodedEventSignature = EventEncoder.encode(event);
        List<Type> indexedValues = new ArrayList<>();
        List<Type> nonIndexedValues = FunctionReturnDecoder.decode(log.getData(), event.getNonIndexedParameters());

        List<TypeReference<Type>> indexedParameters = event.getIndexedParameters();
        for (int i = 0; i < indexedParameters.size(); i++) {
            Type value = FunctionReturnDecoder.decodeIndexedValue(
                    topics.get(i + 1), indexedParameters.get(i));
            indexedValues.add(value);
        }

        return new EventValues(indexedValues, nonIndexedValues);
    }

}
