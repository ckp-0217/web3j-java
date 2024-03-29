package com.example.web3;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.web3.service.TransactionProcessorService;

@SpringBootTest
class AAVEEventResolution {

    public static final String AAVE_DEPOSIT_TXHASH = "0x979d4d6cb5373095f3b141f2a3ae34be1a9418a48b09665fd673c2147d78c715";
    public static final String AAVE_BORROW_TXHASH = "0xaac9cbfb290ba5899a254ba43b73fb0a2baa139ba86dbbbdf994be47778a54e6";
    public static final String AAVE_REPAY_TXHASH = "0x8587ef4cfb5886c8323fea0de04fd3a5781c601c9412a75ef584fc94a32d1fb0";
    public static final String AAVE_WITHDRAW_TXHASH = "0x7db41da03ee9bd44fda6f070cc65a1d77ee06e7e544abb88829c98ccb332ee0b";

    static TransactionProcessorService service = new TransactionProcessorService();

    @Test
    void getLogs() throws IOException {
        service.processTransactionByHash(AAVE_DEPOSIT_TXHASH);
        service.processTransactionByHash(AAVE_BORROW_TXHASH);
        service.processTransactionByHash(AAVE_REPAY_TXHASH);
        service.processTransactionByHash(AAVE_WITHDRAW_TXHASH);

    }
}
