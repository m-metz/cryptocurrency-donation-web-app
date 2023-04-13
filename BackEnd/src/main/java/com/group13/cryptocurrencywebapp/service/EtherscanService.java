package com.group13.cryptocurrencywebapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ResponseStatusException;

import com.group13.cryptocurrencywebapp.web_entity.etherscan.EthAddress;
import com.group13.cryptocurrencywebapp.web_entity.etherscan.Price;
import com.group13.cryptocurrencywebapp.web_entity.etherscan.transaction.EthTransactionList;
import com.group13.cryptocurrencywebapp.web_entity.etherscan.transaction.Result;

/** 
 * <pre>
 * Class Name: EtherscanService
 * 
 * Date Created: March 10, 2023
 * Company: Benevity
 * </pre>
 * 
 * <p>Service class that is defined with all of the functionality necessary to interact with etherscan
 * 
 * @author U of C ENSF609 Capstone 2023 (Alex K, Felipe G, Mike, M)
 * 
 * @Since April 09, 2023
 * 
 */
@Service
public class EtherscanService {

    @Autowired
    @Qualifier("etherscanClient")
    private WebClient webclient = WebClient.create();

    @Value("${etherscan.api.key}")
    String apiKey;

    /**
     * Check that an address is valid
     * @param address String holding the address to check
     * @return A string containing the result of the validation
     */

    public String validateAddress(String address) {
        String uri = "?module=account&action=balance&address=" + address + "&tag=latest&apikey=" + apiKey;

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
     * @param message String holding the message to check
     * @return A boolean containing if the message is okay or not
     */
    public boolean checkMessage(String message) {

        return message.equals("OK");
    }


    /**
     * Get the price of ethereum right now
     * @return A Price object containing the price of ethereum 
     */
    public Price getEthPrice() {
        String uri = "?module=stats&action=ethprice&apikey=" + apiKey;

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
     * @param message String holding the address of the wallet in question
     * @return A list of Result objects holding the transactions created
     */
    public List<Result> getTransactions(String address) {

        String uri = "?module=account&action=txlist&address=" + address
                + "&startblock=0&endblock=99999999&page=1&offset=1000&sort=desc&apikey=" + apiKey;

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

}
