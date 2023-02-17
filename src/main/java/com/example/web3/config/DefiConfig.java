package com.example.web3.config;

import com.example.web3.model.AaveParser;
import com.example.web3.model.DefiParser;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DefiConfig {
    private Map<String, List<String>> defiEventTopics;
    private Map<String, DefiParser> defiParserMap;

    public DefiConfig() {
        // 初始化 defiEventTopics map
        defiEventTopics = new HashMap<>();

        //AAVE
        AaveParser aaveParser = new AaveParser();
        defiEventTopics.put(aaveParser.address, aaveParser.topicList);
        defiParserMap.put(aaveParser.address, aaveParser);

        // 其他defi项目的topic0列表

    }


    public List<String> getTopic0List(String address) {
        return defiEventTopics.get(address);
    }

    public DefiParser getParser(String address) {
        return defiParserMap.get(address);
    }
}
