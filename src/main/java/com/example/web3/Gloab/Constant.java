package com.example.web3.Gloab;

import org.web3j.crypto.Credentials;
import org.web3j.protocol.http.HttpService;

import com.example.web3.tx.Rpc;

public class Constant {
    static public Rpc HTTPRPC = new Rpc(
            new HttpService("https://eth-goerli.g.alchemy.com/v2/shdu4odG9tIK91rz0J9cXOcJZYM5tg06"));
    static public String PRIVATEKEY = "0db17be65966994238e5defdc9f679626a9c0f63904e51b7e80da228c5676833";
    static public Credentials WALLET = Credentials.create(PRIVATEKEY);
    static public String TXHASH = "0x5013d0d693ef99bfbc312955760787511516b7a25a6e566fd01190b5629a65cb";

}