//package com.example.web3.service;
//
//import org.web3j.protocol.Web3j;
//import org.web3j.protocol.core.methods.response.Transaction;
//
//import java.util.List;
//
//public class ScanTransactionService {
//
//    private Web3j web3j;
//    private TransactionRepository transactionRepository;
//
//    public ScanTransactionService(Web3j web3j, TransactionRepository transactionRepository) {
//        this.web3j = web3j;
//        this.transactionRepository = transactionRepository;
//    }
//
//    public List<Transaction> getAllTransactions() {
//        return transactionRepository.findAll();
//    }
//
//    public List<Transaction> getTransactionsByBlockNumber(long blockNumber) {
//        return transactionRepository.findByBlockNumber(blockNumber);
//    }
//
//    public List<Transaction> getTransactionsByAddress(String address) {
//        return transactionRepository.findByAddress(address);
//    }
//
//    public List<Transaction> getTransactionsByContractAddress(String contractAddress) {
//        return transactionRepository.findByContractAddress(contractAddress);
//    }
//
//    public List<Transaction> getTransactionsByType(TransactionType type) {
//        return transactionRepository.findByType(type);
//    }
//
//    public List<Transaction> getTransactionsByDate(Date startDate, Date endDate) {
//        return transactionRepository.findByDateBetween(startDate, endDate);
//    }
//
//    public void saveTransaction(Transaction transaction) {
//        transactionRepository.save(transaction);
//    }
//
//    public void deleteTransaction(Transaction transaction) {
//        transactionRepository.delete(transaction);
//    }
//
//    public void deleteAllTransactions() {
//        transactionRepository.deleteAll();
//    }
//}
