package com.example.web3.tx;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import com.example.web3.Gloab.Constant;
import com.example.web3.contract.Ballot;
import com.example.web3.contract.Dai;
import com.example.web3.contract.LendingPool;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.web3j.abi.FunctionEncoder;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.Hash;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.response.*;
import org.web3j.protocol.core.methods.request.Transaction;

import org.web3j.tx.FastRawTransactionManager;
import org.web3j.tx.gas.ContractGasProvider;
import org.web3j.tx.gas.DefaultGasProvider;
import org.web3j.tx.gas.StaticEIP1559GasProvider;
import org.web3j.utils.Convert;
import org.web3j.utils.Numeric;

public class ScanTx {
    
    private static final Logger log = LoggerFactory.getLogger(ScanTx.class);

    static Rpc web3j = Constant.HTTPRPC;
    static Credentials wallet = Constant.WALLET;
    static String hash = Constant.TXHASH;

    public static void main(String[] args) {
        web3j.blockFlowable(false).subscribe(block -> {
            System.out.println("New block received: " + block.getBlock().getNumber());
        });
    }
}
