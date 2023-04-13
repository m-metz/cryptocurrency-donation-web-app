package com.group13.cryptocurrencywebapp.service;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
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

/** 
 * <pre>
 * Class Name: GeminiService
 * 
 * Date Created: March 10, 2023
 * Company: Benevity
 * </pre>
 * 
 * <p>Service class that is defined with all of the functionality necessary to interact with the Gemini exchange
 * 
 * @author U of C ENSF609 Capstone 2023 (Alex K, Felipe G, Mike, M)
 * 
 * @Since April 09, 2023
 * 
 */
@Service
public class GeminiService {

    Signature sign = new Signature();

    @Value("${gemini.api.secret}")
    String geminiSecret;

    @Autowired
    @Qualifier("geminiClient")
    private WebClient webclient = WebClient.create();

    /**
     * Returns the balances
     * @return A list of Balance objects holding the current balances
     */
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

    /**
     * Create a new trade order
     * @param amount Integer holding the amount ETH (in Wei) you would like to convert to ETHUSD
     * @return An Order object holding the inofrmation about the newly created order
     */
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

    /**
     * Gets the exchange rate of the specified currency pair
     * @param pair String holding the pair of currencies that the exchange will occur between
     * @return String holding the exchange rate
     */
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

    /**
     * Creates a new instant order of the specified amount from ETH to USD
     * @param amount The amount of ETH to convert to USD
     * @return An InstantOrder object holding the information about the instant order created
     */
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

    /**
     * Gets a quote of a trade of teh specified currency symbol
     * @param symbol String holding the currency symbol of the proposed trade
     * @param amount Float holding the amount you wish to convert
     * @return Quote object holding the result of the quote
     */
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
