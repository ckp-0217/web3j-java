package com.example.web3.model;

import com.example.web3.contract.LendingPool;
import org.web3j.abi.datatypes.Event;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.tx.Contract;
import org.web3j.tx.ManagedTransaction;

public class AaveParser implements DefiParser {
    public String address = "0x4bd5643ac6f66a5237E18bfA7d47cF22f1c9F210";
    private LendingPool wrapper;//这是合约包装器

    @Override
    public void parseEvent(Event event) {
        // 解析 Aave 相关事件
    }

}