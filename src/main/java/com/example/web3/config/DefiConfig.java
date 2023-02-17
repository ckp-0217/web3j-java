package com.example.web3.config;

import com.example.web3.model.AaveParser;
import com.example.web3.model.DefiParser;
import org.web3j.abi.EventEncoder;

import java.util.*;

import static com.example.web3.contract.LendingPool.BORROW_EVENT;
import static com.example.web3.contract.LendingPool.DEPOSIT_EVENT;

public class DefiConfig {
    private Map<String, List<String>> defiEventTopics;
    private Map<String, DefiParser> defiParserMap;

    public DefiConfig() {
        // 初始化 defiEventTopics map
        defiEventTopics = new HashMap<>();
        //AAVE
        defiEventTopics.put("0x4bd5643ac6f66a5237E18bfA7d47cF22f1c9F210",
                Arrays.asList(
                        EventEncoder.encode(DEPOSIT_EVENT),
                        EventEncoder.encode(BORROW_EVENT)
                ));
        defiParserMap.put("0x4bd5643ac6f66a5237E18bfA7d47cF22f1c9F210", new AaveParser());

        // 其他defi项目的topic0列表
    }

    public List<String> getEventTopicsForDefi(String defiAddress) {
        return defiEventTopics.getOrDefault(defiAddress, Collections.emptyList());
    }

    public List<String> getTopic0s(String address) {
        return defiEventTopics.get(address);
    }

    public DefiParser getParser(String address) {
        return defiParserMap.get(address);
    }
}
