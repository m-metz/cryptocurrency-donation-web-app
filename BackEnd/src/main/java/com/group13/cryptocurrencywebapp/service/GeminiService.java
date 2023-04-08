package com.group13.cryptocurrencywebapp.service;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.group13.cryptocurrencywebapp.config.Signature;
import com.group13.cryptocurrencywebapp.web_entity.exchange.gemini.Balance;
import com.group13.cryptocurrencywebapp.web_entity.exchange.gemini.InstantOrder;
import com.group13.cryptocurrencywebapp.web_entity.exchange.gemini.Order;
import com.group13.cryptocurrencywebapp.web_entity.exchange.gemini.Price;
import com.group13.cryptocurrencywebapp.web_entity.exchange.gemini.Quote;

import net.minidev.json.JSONObject;

@Service
public class GeminiService {

    Signature sign = new Signature();

    @Value("${gemini.api.secret}")
    String geminiSecret;

    @Autowired
    @Qualifier("geminiClient")
    private WebClient webclient = WebClient.create();

    public List<Balance> getBalancesInfo() throws UnsupportedEncodingException {
        String apiEndpoint = "/v1/balances";
        // Has to be Unix Epoch time (needs to be in seconds)
        long nonce = System.currentTimeMillis() / 1000;

        JSONObject payload = new JSONObject();
        payload.appendField("request", apiEndpoint);
        payload.appendField("nonce", nonce);
        payload.appendField("account", "primary");
        byte[] encondedPayload = sign.encodeBase64(payload.toJSONString());
        String signature = sign.signHmacSha384(encondedPayload, geminiSecret);

        ResponseEntity<List<Balance>> response = webclient.post()
                .uri(apiEndpoint)
                .headers(httpHeaders -> {
                    httpHeaders.set("X-GEMINI-PAYLOAD", new String(encondedPayload));
                    httpHeaders.set("X-GEMINI-SIGNATURE", signature);
                })
                .retrieve()
                .toEntityList(Balance.class)
                .block();

        return response.getBody();
    }

    public Order createNewOrder(int amount) throws UnsupportedEncodingException {
        String apiEndpoint = "/v1/order/new";

        String symbol = "ETHUSD";
        String price = getEthPrice(symbol);
        if (price.equals("not found")) {
            price = "1700.00";
        }
        symbol.toLowerCase();
        // Has to be Unix Epoch time (needs to be in seconds)
        long nonce = System.currentTimeMillis() / 1000;
        JSONObject payload = new JSONObject();
        payload.appendField("request", apiEndpoint);
        payload.appendField("nonce", nonce);
        payload.appendField("symbol", symbol);
        payload.appendField("amount", String.valueOf(amount));
        payload.appendField("price", price);
        payload.appendField("side", "sell");
        payload.appendField("type", "exchange limit");
        byte[] encondedPayload = sign.encodeBase64(payload.toJSONString());
        String signature = sign.signHmacSha384(encondedPayload, geminiSecret);

        ResponseEntity<Order> response = webclient.post()
                .uri(apiEndpoint)
                .headers(httpHeaders -> {
                    httpHeaders.set("X-GEMINI-PAYLOAD", new String(encondedPayload));
                    httpHeaders.set("X-GEMINI-SIGNATURE", signature);
                })
                .retrieve()
                .toEntity(Order.class)
                .block();

        return response.getBody();

    }

    public String getEthPrice(String pair) {

        String apiEndpoint = "https://api.sandbox.gemini.com/v1/pricefeed";
        ResponseEntity<List<Price>> response = webclient.get()
                .uri(apiEndpoint)
                .retrieve()
                .toEntityList(Price.class)
                .block();
        List<Price> prices = response.getBody();
        if (prices != null) {
            for (Price p : prices) {
                if (p.getPair().equals(pair) == true) {
                    return p.getPrice();
                }
            }
        }
        return "not found";

    }

    // Instant Order Services
    public InstantOrder createNewInstantOrder(float amount) throws UnsupportedEncodingException {
        String apiEndpoint = "/v1/instant/execute";

        String symbol = "ethusd";
        Quote quote = getQuote(symbol, amount);
        // Has to be Unix Epoch time (needs to be in seconds)
        long nonce = System.currentTimeMillis() / 1000;
        JSONObject payload = new JSONObject();
        payload.appendField("request", apiEndpoint);
        payload.appendField("nonce", nonce);
        payload.appendField("symbol", symbol);
        payload.appendField("quantity", quote.getQuantity());
        payload.appendField("price", quote.getPrice());
        payload.appendField("side", "sell");
        payload.appendField("fee", quote.getFee());
        payload.appendField("quoteId", quote.getQuoteId());
        byte[] encondedPayload = sign.encodeBase64(payload.toJSONString());
        String signature = sign.signHmacSha384(encondedPayload, geminiSecret);

        ResponseEntity<InstantOrder> response = webclient.post()
                .uri(apiEndpoint)
                .headers(httpHeaders -> {
                    httpHeaders.set("X-GEMINI-PAYLOAD", new String(encondedPayload));
                    httpHeaders.set("X-GEMINI-SIGNATURE", signature);
                })
                .retrieve()
                .toEntity(InstantOrder.class)
                .block();

        return response.getBody();
    }

    public Quote getQuote(String symbol, float amount) throws UnsupportedEncodingException {

        String apiEndpoint = "/v1/instant/quote";
        long nonce = System.currentTimeMillis() / 1000;
        JSONObject payload = new JSONObject();
        payload.appendField("request", apiEndpoint);
        payload.appendField("nonce", nonce);
        payload.appendField("symbol", symbol);
        payload.appendField("totalSpend", String.valueOf(amount));
        payload.appendField("side", "sell");
        byte[] encondedPayload = sign.encodeBase64(payload.toJSONString());
        String signature = sign.signHmacSha384(encondedPayload, geminiSecret);

        ResponseEntity<Quote> response = webclient.post()
                .uri(apiEndpoint)
                .headers(httpHeaders -> {
                    httpHeaders.set("X-GEMINI-PAYLOAD", new String(encondedPayload));
                    httpHeaders.set("X-GEMINI-SIGNATURE", signature);
                })
                .retrieve()
                .toEntity(Quote.class)
                .block();

        return response.getBody();

    }

}
