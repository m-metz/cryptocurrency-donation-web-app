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

}
