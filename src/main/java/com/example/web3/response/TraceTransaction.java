package com.example.web3.response;



import java.math.BigInteger;
import java.util.List;

import org.web3j.crypto.TransactionUtils;
import org.web3j.utils.Numeric;

public class TraceTransaction {
    private String gas;
    private String returnValue;
    private String blockHash;
    private List<structLog> structLog;
    public String getGas() {
        return gas;
    }
    public void setGas(String gas) {
        this.gas = gas;
    }
    public String getReturnValue() {
        return returnValue;
    }
    public void setReturnValue(String returnValue) {
        this.returnValue = returnValue;
    }
    public String getBlockHash() {
        return blockHash;
    }
    public void setBlockHash(String blockHash) {
        this.blockHash = blockHash;
    }
    public List<structLog> getStructLog() {
        return structLog;
    }
    public void setStructLog(List<structLog> structLog) {
        this.structLog = structLog;
    }
    @Override
    public String toString() {
        return "TraceTransaction [gas=" + gas + ", returnValue=" + returnValue + ", blockHash=" + blockHash
                + ", structLog=" + structLog + "]";
    }
    
    
    
}
