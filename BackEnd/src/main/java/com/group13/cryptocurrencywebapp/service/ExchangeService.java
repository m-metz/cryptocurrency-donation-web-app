package com.group13.cryptocurrencywebapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.group13.cryptocurrencywebapp.config.Signature;
import com.group13.cryptocurrencywebapp.web_entity.exchange.binance.ExchangeAccount;
import com.group13.cryptocurrencywebapp.web_entity.exchange.binance.ExchangeTradeResponse;

/** 
 * <pre>
 * Class Name: ExchangeService
 * 
 * Date Created: March 10, 2023
 * Company: Benevity
 * </pre>
 * 
 * <p>Service class that is defined with all of the functionality necessary to interact with the Binance exchange
 * 
 * @author U of C ENSF609 Capstone 2023 (Alex K, Felipe G, Mike, M)
 * 
 * @Since April 09, 2023
 * 
 */
@Service
@ConfigurationProperties(prefix = "binance.api")
public class ExchangeService {

    private Signature sign = new Signature();

    private String secret;

    public String getSecret() {
        return secret;
    }
    
    public void setSecret(String secret) {
        this.secret = secret;
    }

    @Autowired
    @Qualifier("binanceClient")
    private WebClient webclient = WebClient.create();

    /**
     * Get information about the account
     * @return An ExchangeAccount object containing the information about the account
     */
    public ExchangeAccount getAccountInfo() throws Exception {
        String apiEndpoint = "/v3/account?";
        String queryParams = "timestamp=" + System.currentTimeMillis();
        String signature = sign.getSignature(queryParams, secret);
        String payload = apiEndpoint + queryParams + "&signature=" + signature;

        ResponseEntity<ExchangeAccount> response =
                webclient.get().uri(payload).retrieve().toEntity(ExchangeAccount.class).block();

        return response.getBody();
    }

    /**
     * Executes a trade of the specified ETH amount to ETHBUSD
     * @param value Float holding the amount of ETH to trade
     * @return 
     */
    public ExchangeTradeResponse executeNewTrade(float value) throws InterruptedException {
        String apiEndpoint = "/v3/order?";
        String queryParams = "symbol=ETHBUSD" + "&side=SELL" + "&type=MARKET" + "&quantity=" + value
                + "&timestamp=" + System.currentTimeMillis();
        String signature = sign.getSignature(queryParams, secret);
        String payload = apiEndpoint + queryParams + "&signature=" + signature;

        ResponseEntity<ExchangeTradeResponse> response = webclient.post().uri(payload).retrieve()
                .toEntity(ExchangeTradeResponse.class).block();

        ExchangeTradeResponse trade = response.getBody();

        if (trade != null) {
            while (trade.getStatus().equals("FILLED") == false) {
                int retryCount = 1;
                Thread.sleep(60000 * retryCount);
                ExchangeTradeResponse query = queryTrade(trade.getClientOrderId());
                if (query != null) {
                    trade = query;
                }
                if (retryCount > 10) {
                    ExchangeTradeResponse cancel = cancelTrade(trade.getClientOrderId());
                    if (cancel != null) {
                        // throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        // "Order id " + trade.getClientOrderId() + "cancelled in the Exchange
                        // System!");
                        System.out.println("Trade Order id " + trade.getClientOrderId()
                                + "cancelled in the Exchange System!");
                        return null;
                    }

                }
                retryCount++;

            }
            return trade;
        } else {
            return null;
        }
    }

    /**
     * Get the status of a trade
     
     */
    public ExchangeTradeResponse queryTrade(String clientOrderId) {
        String apiEndpoint = "/v3/order?";
        String queryParams = "symbol=ETHBUSD&origClientOrderId=" + clientOrderId + "&timestamp="
                + System.currentTimeMillis();
        String signature = sign.getSignature(queryParams, secret);
        String payload = apiEndpoint + queryParams + "&signature=" + signature;

        ResponseEntity<ExchangeTradeResponse> response = webclient.get().uri(payload).retrieve()
                .toEntity(ExchangeTradeResponse.class).block();

        ExchangeTradeResponse trade = response.getBody();
        if (trade != null) {
            return trade;
        } else {
            return null;
        }
    }

    /**
     * Cancel and existing trade
     * @param clientOrderId String holding the order id of the trade
     * @return An ExchangeTradeResponse object containing the response from the exchange
     */
    public ExchangeTradeResponse cancelTrade(String clientOrderId) {
        String apiEndpoint = "/v3/order?";
        String queryParams = "symbol=ETHBUSD&origClientOrderId=" + clientOrderId + "&timestamp="
                + System.currentTimeMillis();
        String signature = sign.getSignature(queryParams, secret);
        String payload = apiEndpoint + queryParams + "&signature=" + signature;

        ResponseEntity<ExchangeTradeResponse> response = webclient.delete().uri(payload).retrieve()
                .toEntity(ExchangeTradeResponse.class).block();

        ExchangeTradeResponse trade = response.getBody();
        if (trade != null) {
            return trade;
        } else {
            return null;
        }
    }

}
