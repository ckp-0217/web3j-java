package com.example.web3.config;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.web3.parser.AaveParser;
import com.example.web3.parser.DefiParser;

public class DefiConfig {
    private Map<String, List<String>> defiEventTopics = new HashMap<>();
    private Map<String, DefiParser> defiParserMap = new HashMap<>();

    public DefiConfig() {
        // 初始化 defiEventTopics map

        // AAVE
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
