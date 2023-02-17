package com.example.web3.model;

import com.example.web3.contract.LendingPool;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.datatypes.Event;
import org.web3j.protocol.core.methods.response.Log;

import java.util.*;

import static com.example.web3.contract.LendingPool.*;

public class AaveParser implements DefiParser {
    public String address = "0x4bd5643ac6f66a5237E18bfA7d47cF22f1c9F210";
    public List<String> topicList = new ArrayList<String>();
    public List<Event> eventList;
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
        return null;
    }

}