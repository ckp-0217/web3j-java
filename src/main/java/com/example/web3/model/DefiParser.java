package com.example.web3.model;

import org.web3j.abi.datatypes.Event;

public interface DefiParser {
    void parseEvent(Event event);
}