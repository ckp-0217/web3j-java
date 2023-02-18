package com.example.web3.model;

import com.example.web3.contract.LendingPool;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.datatypes.Event;
import org.web3j.protocol.core.methods.response.Log;

import java.util.*;

import static com.example.web3.contract.LendingPool.*;

public class AaveParser implements DefiParser {
    public String address = "0x7d2768de32b0b80b7a3454c06bdac94a69ddc7a9";
    public List<String> topicList = new ArrayList<String>();
    public List<Event> eventList = new ArrayList<>();
    public Map<String, Event> eventMap = new HashMap<>();
    private LendingPool wrapper;//这是合约包装器

    public AaveParser() {
        //初始化需要解析的事件
        eventList = Arrays.asList(
                BORROW_EVENT,
                DEPOSIT_EVENT,
                REPAY_EVENT,
                WITHDRAW_EVENT
        );
        //添加topic0
        for (Event event : eventList) {
            String topic0 = EventEncoder.encode(event);
            System.out.println("topic0: "+topic0);
            topicList.add(topic0);
            eventMap.put(topic0, event);
        }
    }


    @Override
    public void parseEvent(Log log, Event event) {
        System.out.println(event.getName().toString());
    }

    @Override
    public Event getEventByTopic0(String topic0) {
        return eventMap.get(topic0);
    }

}