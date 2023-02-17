package com.example.web3.model;

import org.web3j.abi.datatypes.Event;
import org.web3j.protocol.core.methods.response.Log;

public interface DefiParser {
    void parseEvent(Log log, Event event);

    Event getEventByTopic0(String topic0);

    default void parseEventByTopic0(Log log, String topic0) {
        parseEvent(log, getEventByTopic0(topic0));
    }
}