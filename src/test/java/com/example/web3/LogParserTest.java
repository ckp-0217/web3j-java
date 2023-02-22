package com.example.web3;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.web3.parser.ERC20Parser;
import com.example.web3.util.Constant;
import com.example.web3.util.Rpc;

@SpringBootTest
class LogParserTest {
    static Rpc web3j = Constant.HTTPRPC_MAIN;
	@Test
	void ERC20TransferParser() {

		new ERC20Parser().startListening(web3j);
	}

}
