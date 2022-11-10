package com.example.web3;

import java.net.ConnectException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.websocket.WebSocketService;

import io.reactivex.disposables.Disposable;

@SpringBootApplication
public class Web3Application {

	public static void main(String[] args) throws ConnectException {
		SpringApplication.run(Web3Application.class, args);

	}

}
