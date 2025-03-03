package com.group13.cryptocurrencywebapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ResponseStatusException;

import com.group13.cryptocurrencywebapp.web_entity.etherscan.EthAddress;
import com.group13.cryptocurrencywebapp.web_entity.etherscan.Price;
import com.group13.cryptocurrencywebapp.web_entity.etherscan.transaction.Result;
import com.group13.cryptocurrencywebapp.web_entity.etherscan.transaction.txcheck.CheckResult;
import com.group13.cryptocurrencywebapp.web_entity.etherscan.transaction.EthTransactionList;

/**
 * <pre>
 * Class Name: EtherscanService
 * 
 * Date Created: March 10, 2023
 * Company: Benevity
 * </pre>
 * 
 * <p>
 * Service class that is defined with all of the functionality necessary to
 * interact with etherscan
 * 
 * @author U of C ENSF609 Capstone 2023 (Alex K, Felipe G, Mike, M)
 * 
 * @Since April 09, 2023
 * 
 */
@Service
@ConfigurationProperties(prefix = "etherscan.api")
public class EtherscanService {

    @Autowired
    @Qualifier("etherscanClient")
    private WebClient webclient = WebClient.create();

    private String key;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    /**
     * Check that an address is valid
     * 
     * @param address String holding the address to check
     * @return A string containing the result of the validation
     */

    public String validateAddress(String address) {
        String uri = "?module=account&action=balance&address=" + address + "&tag=latest&apikey=" + key;

        ResponseEntity<EthAddress> response = webclient.get()
                .uri(uri)
                .retrieve()
                .toEntity(EthAddress.class)
                .block();

        EthAddress respAddress = response.getBody();
        if (respAddress != null) {
            if (checkMessage(respAddress.getMessage()) == true) {
                return "Valid Ethereum Address";
            } else {
                return "Invalid Address";
            }

        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Request Failed");
        }

    }

    /**
     * Check that a message is OK
     * 
     * @param message String holding the message to check
     * @return A boolean containing if the message is okay or not
     */
    public boolean checkMessage(String message) {

        return message.equals("OK");
    }

    /**
     * Get the price of ethereum right now
     * 
     * @return A Price object containing the price of ethereum
     */
    public Price getEthPrice() {
        String uri = "?module=stats&action=ethprice&apikey=" + key;

        ResponseEntity<Price> response = webclient.get()
                .uri(uri)
                .retrieve()
                .toEntity(Price.class)
                .block();
        Price price = response.getBody();
        if (price != null) {
            if (checkMessage(price.getMessage()) == true) {
                return price;
            } else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Request Failed");
            }

        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Request Failed");
        }

    }

    /**
     * Get all the transactions created by one address
     * 
     * @param message String holding the address of the wallet in question
     * @return A list of Result objects holding the transactions created
     */
    public List<Result> getTransactions(String address) {

        String uri = "?module=account&action=txlist&address=" + address
                + "&startblock=0&endblock=99999999&page=1&offset=1000&sort=desc&apikey=" + key;

        ResponseEntity<EthTransactionList> response = webclient.get()
                .uri(uri)
                .retrieve()
                .toEntity(EthTransactionList.class)
                .block();

        EthTransactionList responseList = response.getBody();
        if (responseList != null) {
            if (checkMessage(responseList.getMessage()) == true) {
                return responseList.getResult();
            } else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Request Failed");
            }

        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Request Failed");
        }

    }

    /**
     * Check Transaction Status in the BlockChain
     * 
     * @param hash String transaction hash to be queried in the BlockChain
     * @return 0 if the transaction is not processed and 1 if the transaction is
     *         processed
     */
    public int checkTransactionStatus(String hash) {
        String uri = "?module=transaction&action=gettxreceiptstatus&txhash=" + hash + "&apikey=" + key;

        ResponseEntity<CheckResult> response = webclient.get()
                .uri(uri)
                .retrieve()
                .toEntity(CheckResult.class)
                .block();

        CheckResult respTx = response.getBody();
        if (respTx != null) {
            return Integer.parseInt(respTx.getStatus());
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Request Failed");
        }

    }

}
