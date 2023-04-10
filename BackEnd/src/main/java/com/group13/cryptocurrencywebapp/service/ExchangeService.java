package com.group13.cryptocurrencywebapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.group13.cryptocurrencywebapp.config.Signature;
import com.group13.cryptocurrencywebapp.web_entity.exchange.binance.ExchangeAccount;
import com.group13.cryptocurrencywebapp.web_entity.exchange.binance.ExchangeTradeResponse;

@Service
public class ExchangeService {

    private Signature sign = new Signature();

    @Value("${binance.api.secret}")
    String secret;

    @Autowired
    @Qualifier("binanceClient")
    private WebClient webclient = WebClient.create();

    public ExchangeAccount getAccountInfo() throws Exception {
        String apiEndpoint = "/v3/account?";
        String queryParams = "timestamp=" + System.currentTimeMillis();
        String signature = sign.getSignature(queryParams, secret);
        String payload = apiEndpoint + queryParams + "&signature=" + signature;

        ResponseEntity<ExchangeAccount> response =
                webclient.get().uri(payload).retrieve().toEntity(ExchangeAccount.class).block();

        return response.getBody();
    }

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
