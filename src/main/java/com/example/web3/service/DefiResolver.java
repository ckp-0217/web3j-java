package com.example.web3.service;

import com.example.web3.config.DefiConfig;
import org.web3j.protocol.core.methods.response.Log;

import java.util.List;

public class DefiResolver {
    private DefiConfig defiConfig;

    public DefiResolver(DefiConfig defiConfig) {
        this.defiConfig = defiConfig;
    }

    public void resolveEvent(Log log) {
        List<String> topic0s = defiConfig.getTopic0s(log.getAddress());
        if (topic0s != null && topic0s.contains(log.getTopics().get(0))) {
            // 根据地址和topic0解析对应的事件
            defiConfig.getParser(log.getAddress());

        }
    }
}
