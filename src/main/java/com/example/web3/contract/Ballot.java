package com.example.web3.contract;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;

import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Bytes32;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple2;
import org.web3j.tuples.generated.Tuple4;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 1.4.2.
 */
@SuppressWarnings("rawtypes")
public class Ballot extends Contract {
    public static final String BINARY = "608060405234801561001057600080fd5b50336000806101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff160217905550610fe6806100606000396000f3fe608060405234801561001057600080fd5b50600436106100885760003560e01c8063609ff1bd1161005b578063609ff1bd146101145780639e7b8d6114610132578063a3ec138d1461014e578063e2ba53f01461018157610088565b80630121b93f1461008d578063013cf08b146100a95780632e4176cf146100da5780635c19a95c146100f8575b600080fd5b6100a760048036038101906100a291906109e2565b61019f565b005b6100c360048036038101906100be91906109e2565b6102e5565b6040516100d1929190610a37565b60405180910390f35b6100e2610319565b6040516100ef9190610aa1565b60405180910390f35b610112600480360381019061010d9190610ae8565b61033d565b005b61011c6106d7565b6040516101299190610b15565b60405180910390f35b61014c60048036038101906101479190610ae8565b61075f565b005b61016860048036038101906101639190610ae8565b610916565b6040516101789493929190610b4b565b60405180910390f35b610189610973565b6040516101969190610b90565b60405180910390f35b6000600160003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002090506000816000015403610229576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040161022090610c08565b60405180910390fd5b8060010160009054906101000a900460ff161561027b576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040161027290610c74565b60405180910390fd5b60018160010160006101000a81548160ff0219169083151502179055508181600201819055508060000154600283815481106102ba576102b9610c94565b5b906000526020600020906002020160010160008282546102da9190610cf2565b925050819055505050565b600281815481106102f557600080fd5b90600052602060002090600202016000915090508060000154908060010154905082565b60008054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681565b6000600160003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002090508060010160009054906101000a900460ff16156103d2576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004016103c990610d72565b60405180910390fd5b3373ffffffffffffffffffffffffffffffffffffffff168273ffffffffffffffffffffffffffffffffffffffff1603610440576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040161043790610dde565b60405180910390fd5b5b600073ffffffffffffffffffffffffffffffffffffffff16600160008473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060010160019054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16146105af57600160008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060010160019054906101000a900473ffffffffffffffffffffffffffffffffffffffff1691503373ffffffffffffffffffffffffffffffffffffffff168273ffffffffffffffffffffffffffffffffffffffff16036105aa576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004016105a190610e4a565b60405180910390fd5b610441565b60018160010160006101000a81548160ff021916908315150217905550818160010160016101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055506000600160008473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002090508060010160009054906101000a900460ff16156106b2578160000154600282600201548154811061068657610685610c94565b5b906000526020600020906002020160010160008282546106a69190610cf2565b925050819055506106d2565b81600001548160000160008282546106ca9190610cf2565b925050819055505b505050565b6000806000905060005b60028054905081101561075a57816002828154811061070357610702610c94565b5b9060005260206000209060020201600101541115610747576002818154811061072f5761072e610c94565b5b90600052602060002090600202016001015491508092505b808061075290610e6a565b9150506106e1565b505090565b60008054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff16146107ed576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004016107e490610f24565b60405180910390fd5b600160008273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060010160009054906101000a900460ff161561087d576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040161087490610f90565b60405180910390fd5b6000600160008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060000154146108cc57600080fd5b60018060008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000206000018190555050565b60016020528060005260406000206000915090508060000154908060010160009054906101000a900460ff16908060010160019054906101000a900473ffffffffffffffffffffffffffffffffffffffff16908060020154905084565b6000600261097f6106d7565b815481106109905761098f610c94565b5b906000526020600020906002020160000154905090565b600080fd5b6000819050919050565b6109bf816109ac565b81146109ca57600080fd5b50565b6000813590506109dc816109b6565b92915050565b6000602082840312156109f8576109f76109a7565b5b6000610a06848285016109cd565b91505092915050565b6000819050919050565b610a2281610a0f565b82525050565b610a31816109ac565b82525050565b6000604082019050610a4c6000830185610a19565b610a596020830184610a28565b9392505050565b600073ffffffffffffffffffffffffffffffffffffffff82169050919050565b6000610a8b82610a60565b9050919050565b610a9b81610a80565b82525050565b6000602082019050610ab66000830184610a92565b92915050565b610ac581610a80565b8114610ad057600080fd5b50565b600081359050610ae281610abc565b92915050565b600060208284031215610afe57610afd6109a7565b5b6000610b0c84828501610ad3565b91505092915050565b6000602082019050610b2a6000830184610a28565b92915050565b60008115159050919050565b610b4581610b30565b82525050565b6000608082019050610b606000830187610a28565b610b6d6020830186610b3c565b610b7a6040830185610a92565b610b876060830184610a28565b95945050505050565b6000602082019050610ba56000830184610a19565b92915050565b600082825260208201905092915050565b7f486173206e6f20726967687420746f20766f7465000000000000000000000000600082015250565b6000610bf2601483610bab565b9150610bfd82610bbc565b602082019050919050565b60006020820190508181036000830152610c2181610be5565b9050919050565b7f416c726561647920766f7465642e000000000000000000000000000000000000600082015250565b6000610c5e600e83610bab565b9150610c6982610c28565b602082019050919050565b60006020820190508181036000830152610c8d81610c51565b9050919050565b7f4e487b7100000000000000000000000000000000000000000000000000000000600052603260045260246000fd5b7f4e487b7100000000000000000000000000000000000000000000000000000000600052601160045260246000fd5b6000610cfd826109ac565b9150610d08836109ac565b9250828201905080821115610d2057610d1f610cc3565b5b92915050565b7f596f7520616c726561647920766f7465642e0000000000000000000000000000600082015250565b6000610d5c601283610bab565b9150610d6782610d26565b602082019050919050565b60006020820190508181036000830152610d8b81610d4f565b9050919050565b7f53656c662d64656c65676174696f6e20697320646973616c6c6f7765642e0000600082015250565b6000610dc8601e83610bab565b9150610dd382610d92565b602082019050919050565b60006020820190508181036000830152610df781610dbb565b9050919050565b7f466f756e64206c6f6f7020696e2064656c65676174696f6e2e00000000000000600082015250565b6000610e34601983610bab565b9150610e3f82610dfe565b602082019050919050565b60006020820190508181036000830152610e6381610e27565b9050919050565b6000610e75826109ac565b91507fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff8203610ea757610ea6610cc3565b5b600182019050919050565b7f4f6e6c79206368616972706572736f6e2063616e20676976652072696768742060008201527f746f20766f74652e000000000000000000000000000000000000000000000000602082015250565b6000610f0e602883610bab565b9150610f1982610eb2565b604082019050919050565b60006020820190508181036000830152610f3d81610f01565b9050919050565b7f54686520766f74657220616c726561647920766f7465642e0000000000000000600082015250565b6000610f7a601883610bab565b9150610f8582610f44565b602082019050919050565b60006020820190508181036000830152610fa981610f6d565b905091905056fea264697066735822122092f67200d6b546bc01b78e348463f8e208f37f18ed93e36c2a488a764eb6971e64736f6c63430008110033";

    public static final String FUNC_CHAIRPERSON = "chairperson";

    public static final String FUNC_DELEGATE = "delegate";

    public static final String FUNC_GIVERIGHTTOVOTE = "giveRightToVote";

    public static final String FUNC_PROPOSALS = "proposals";

    public static final String FUNC_VOTE = "vote";

    public static final String FUNC_VOTERS = "voters";

    public static final String FUNC_WINNERNAME = "winnerName";

    public static final String FUNC_WINNINGPROPOSAL = "winningProposal";

    @Deprecated
    protected Ballot(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected Ballot(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected Ballot(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected Ballot(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    @Deprecated
    public static Ballot load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new Ballot(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static Ballot load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Ballot(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static Ballot load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new Ballot(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static Ballot load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new Ballot(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<Ballot> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(Ballot.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    public static RemoteCall<Ballot> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(Ballot.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<Ballot> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Ballot.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<Ballot> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Ballot.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    public RemoteFunctionCall<String> chairperson() {
        final Function function = new Function(FUNC_CHAIRPERSON,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {
                }));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<TransactionReceipt> delegate(String to) {
        final Function function = new Function(
                FUNC_DELEGATE,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, to)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> giveRightToVote(String voter) {
        final Function function = new Function(
                FUNC_GIVERIGHTTOVOTE,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, voter)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<Tuple2<byte[], BigInteger>> proposals(BigInteger param0) {
        final Function function = new Function(FUNC_PROPOSALS,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(param0)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {
                }, new TypeReference<Uint256>() {
                }));
        return new RemoteFunctionCall<Tuple2<byte[], BigInteger>>(function,
                new Callable<Tuple2<byte[], BigInteger>>() {
                    @Override
                    public Tuple2<byte[], BigInteger> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple2<byte[], BigInteger>(
                                (byte[]) results.get(0).getValue(),
                                (BigInteger) results.get(1).getValue());
                    }
                });
    }

    public RemoteFunctionCall<TransactionReceipt> vote(BigInteger proposal) {
        final Function function = new Function(
                FUNC_VOTE,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(proposal)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<Tuple4<BigInteger, Boolean, String, BigInteger>> voters(String param0) {
        final Function function = new Function(FUNC_VOTERS,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, param0)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {
                }, new TypeReference<Bool>() {
                }, new TypeReference<Address>() {
                }, new TypeReference<Uint256>() {
                }));
        return new RemoteFunctionCall<Tuple4<BigInteger, Boolean, String, BigInteger>>(function,
                new Callable<Tuple4<BigInteger, Boolean, String, BigInteger>>() {
                    @Override
                    public Tuple4<BigInteger, Boolean, String, BigInteger> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple4<BigInteger, Boolean, String, BigInteger>(
                                (BigInteger) results.get(0).getValue(),
                                (Boolean) results.get(1).getValue(),
                                (String) results.get(2).getValue(),
                                (BigInteger) results.get(3).getValue());
                    }
                });
    }

    public RemoteFunctionCall<byte[]> winnerName() {
        final Function function = new Function(FUNC_WINNERNAME,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {
                }));
        return executeRemoteCallSingleValueReturn(function, byte[].class);
    }

    public RemoteFunctionCall<BigInteger> winningProposal() {
        final Function function = new Function(FUNC_WINNINGPROPOSAL,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {
                }));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }
}
