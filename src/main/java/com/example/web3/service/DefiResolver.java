package com.example.web3.service;

import com.example.web3.config.DefiConfig;
import com.example.web3.parser.DefiParser;

import org.web3j.protocol.core.methods.response.Log;

import java.util.List;

public class DefiResolver {
    private DefiConfig defiConfig;

    public DefiResolver(DefiConfig defiConfig) {
        this.defiConfig = defiConfig;
    }

    public void resolveEvent(Log log) {
        List<String> topic0List = defiConfig.getTopic0List(log.getAddress());
        System.out.println(log.getAddress());
        if (topic0List != null) {
            String topic0 = log.getTopics().get(0);
            if (topic0List.contains(topic0)) {
                // 根据地址和topic0解析对应的事件
                DefiParser parser = defiConfig.getParser(log.getAddress());

                parser.parseEventByTopic0(log, topic0);

            }
        }
    }
}
