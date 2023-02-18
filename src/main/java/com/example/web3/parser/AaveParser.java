package com.example.web3.parser;

import com.example.web3.contract.LendingPool;
import com.example.web3.handler.BorrowEventHandler;
import com.example.web3.handler.DepositEventHandler;
import com.example.web3.handler.EventHandler;
import com.example.web3.handler.RepayEventHandler;
import com.example.web3.handler.WithdrawEventHandler;

import org.web3j.abi.EventEncoder;
import org.web3j.abi.datatypes.Event;
import org.web3j.protocol.core.methods.response.Log;

import java.util.*;

import static com.example.web3.contract.LendingPool.*;

public class AaveParser implements DefiParser {
    public String address = "0x7d2768de32b0b80b7a3454c06bdac94a69ddc7a9";
    public List<String> topicList = new ArrayList<>();
    public List<Event> eventList = new ArrayList<>();
    public Map<String, EventHandler> eventHandlerMap = new HashMap<>();
    public Map<String, Event> eventMap = new HashMap<>();
    public LendingPool wrapper;

    public AaveParser() {
        // 初始化需要解析的事件
        eventList = Arrays.asList(
                BORROW_EVENT,
                DEPOSIT_EVENT,
                REPAY_EVENT,
                WITHDRAW_EVENT);

        // 添加 topic0
        for (Event event : eventList) {
            String topic0 = EventEncoder.encode(event);
            System.out.println("topic0: " + topic0);
            topicList.add(topic0);
            eventMap.put(topic0, event);

            // 创建事件处理器实例并存储到 map 中
            switch (event.getName()) {
                case "Deposit":
                    eventHandlerMap.put(topic0, DepositEventHandler.getInstance());
                    break;
                case "Borrow":
                    eventHandlerMap.put(topic0, BorrowEventHandler.getInstance());
                    break;
                case "Repay":
                    eventHandlerMap.put(topic0, RepayEventHandler.getInstance());
                    break;
                case "Withdraw":
                    eventHandlerMap.put(topic0, WithdrawEventHandler.getInstance());
                    break;
            }
        }
    }

    @Override
    public void parseEvent(Log log, Event event) {
        String topic0 = EventEncoder.encode(event);
        EventHandler handler = eventHandlerMap.get(topic0);
        if (handler != null) {
            handler.handle(event, log);
        } else {
            System.out.println("No event handler found for topic0: " + topic0);
        }
    }

    @Override
    public Event getEventByTopic0(String topic0) {
        return eventMap.get(topic0);
    }
}
