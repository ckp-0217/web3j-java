package com.example.web3.response;

public class structLog {
    private String depth;
    private String error;
    private String gas;
    private String gasCost;
    private String memory;
    private String op;
    private String pc;
    private String stack;
    private String storage;
    public String getDepth() {
        return depth;
    }
    public void setDepth(String depth) {
        this.depth = depth;
    }
    public String getError() {
        return error;
    }
    public void setError(String error) {
        this.error = error;
    }
    public String getGas() {
        return gas;
    }
    public void setGas(String gas) {
        this.gas = gas;
    }
    public String getGasCost() {
        return gasCost;
    }
    public void setGasCost(String gasCost) {
        this.gasCost = gasCost;
    }
    public String getMemory() {
        return memory;
    }
    public void setMemory(String memory) {
        this.memory = memory;
    }
    public String getOp() {
        return op;
    }
    public void setOp(String op) {
        this.op = op;
    }
    public String getPc() {
        return pc;
    }
    public void setPc(String pc) {
        this.pc = pc;
    }
    public String getStack() {
        return stack;
    }
    public void setStack(String stack) {
        this.stack = stack;
    }
    public String getStorage() {
        return storage;
    }
    public void setStorage(String storage) {
        this.storage = storage;
    }
    @Override
    public String toString() {
        return "structLog [depth=" + depth + ", error=" + error + ", gas=" + gas + ", gasCost=" + gasCost + ", memory="
                + memory + ", op=" + op + ", pc=" + pc + ", stack=" + stack + ", storage=" + storage + "]";
    }
    

}
