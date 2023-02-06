/*
 * Copyright 2019 Web3 Labs Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */

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
