package com.example.web3.parser;

import com.example.web3.contract.Dai;
import com.example.web3.handler.EventHandler;
import com.example.web3.handler.TransferEventHandler;
import com.example.web3.handler.AAVE.BorrowEventHandler;
import com.example.web3.handler.AAVE.DepositEventHandler;
import com.example.web3.handler.AAVE.RepayEventHandler;
import com.example.web3.handler.AAVE.WithdrawEventHandler;
import com.example.web3.util.Constant;
import com.example.web3.util.Rpc;

import io.reactivex.disposables.Disposable;

import org.web3j.abi.EventEncoder;
import org.web3j.abi.datatypes.Event;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.request.*;
import org.web3j.protocol.core.methods.response.Log;

import java.util.*;
import java.util.concurrent.Flow.Subscription;

import static com.example.web3.contract.Dai.*;

public class ERC20Parser implements DefiParser {
    static Rpc web3j = Constant.HTTPRPC_MAIN;

    public String address = "0x7d2768de32b0b80b7a3454c06bdac94a69ddc7a9";
    public List<String> topicList = new ArrayList<>();
    public List<Event> eventList = new ArrayList<>();
    public Map<String, EventHandler> eventHandlerMap = new HashMap<>();
    public Map<String, Event> eventMap = new HashMap<>();
    public Dai wrapper;

    public ERC20Parser() {
        // 初始化需要解析的事件
        eventList = Arrays.asList(
            TRANSFER_EVENT);

        // 添加 topic0
        for (Event event : eventList) {
            String topic0 = EventEncoder.encode(event);
            System.out.println("topic0: " + topic0);
            topicList.add(topic0);
            eventMap.put(topic0, event);

            // 创建事件处理器实例并存储到 map 中
            switch (event.getName()) {
                case "Transfer":
                    eventHandlerMap.put(topic0, TransferEventHandler.getInstance());
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

    public static void main(String[] args) {
        new ERC20Parser().startListening(web3j);
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

    /**
     * Start listening to the event topics.
     * 
     * @param web3j the Web3j instance
     */
    public void startListening(Web3j web3j) {
        // Create an Ethereum filter with the topic list
        EthFilter filter = new EthFilter(address);
        for (String topic : topicList) {
            filter.addSingleTopic(topic);
        }

        // Start listening to the events
        Disposable subscribe = web3j.ethLogFlowable(filter)
                .subscribe(log -> {
                    Event event = eventMap.get(log.getTopics().get(0));
                    parseEvent(log, event);
                });
        System.out.println(subscribe);
        System.out.println("Started listening to ERC20 events.");
    }

    /**
     * Stop listening to the event topics.
     */
    // public void stopListening() {
    //     if (subscribe != null) {
    //         subscribe.cancel();
    //         System.out.println("Stopped listening to ERC20 events.");
    //     }
    // }

}
