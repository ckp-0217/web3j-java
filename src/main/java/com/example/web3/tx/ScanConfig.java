package com.example.web3.tx;

import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Event;


import java.util.List;

public class ScanConfig {
    private String url;
    private List<Address> addressList;
    private List<Event> eventList;
    private int startBlockNumber;
    private int endBlockNumber;
    private int gapTime;

    public String getUrl() {
        return url;
    }

    public ScanConfig setUrl(String url) {
        this.url = url;
        return this;
    }

    public List<Address> getAddressList() {
        return addressList;
    }

    public ScanConfig setAddressList(List<Address> addressList) {
        this.addressList = addressList;
        return this;
    }

    public List<Event> getEventList() {
        return eventList;
    }

    public ScanConfig setEventList(List<Event> eventList) {
        this.eventList = eventList;
        return this;
    }

    public int getStartBlockNumber() {
        return startBlockNumber;
    }

    public ScanConfig setStartBlockNumber(int startBlockNumber) {
        this.startBlockNumber = startBlockNumber;
        return this;
    }

    public int getEndBlockNumber() {
        return endBlockNumber;
    }

    public ScanConfig setEndBlockNumber(int endBlockNumber) {
        this.endBlockNumber = endBlockNumber;
        return this;
    }

    public int getGapTime() {
        return gapTime;
    }

    public ScanConfig setGapTime(int gapTime) {
        this.gapTime = gapTime;
        return this;
    }
}
