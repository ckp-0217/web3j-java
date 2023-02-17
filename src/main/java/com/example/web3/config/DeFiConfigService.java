package com.example.web3.config;

import java.util.HashMap;
import java.util.Map;

public class DeFiConfigService {

    private static DeFiConfigService instance = null;
    private Map<String, String> addressMap;
    private Map<String, EventInfo> eventMap;

    private DeFiConfigService() {
        addressMap = new HashMap<>();
        eventMap = new HashMap<>();
    }

    public static DeFiConfigService getInstance() {
        if (instance == null) {
            instance = new DeFiConfigService();
        }
        return instance;
    }

    public void addAddress(String name, String address) {
        addressMap.put(name, address);
    }

    public String getAddress(String name) {
        return addressMap.get(name);
    }

    public void addEvent(String name, String address, String topic) {
        EventInfo eventInfo = new EventInfo(address, topic);
        eventMap.put(name, eventInfo);
    }

    public EventInfo getEvent(String name) {
        return eventMap.get(name);
    }

    public static class EventInfo {
        private String address;
        private String topic;

        public EventInfo(String address, String topic) {
            this.address = address;
            this.topic = topic;
        }

        public String getAddress() {
            return address;
        }

        public String getTopic() {
            return topic;
        }
    }
}
