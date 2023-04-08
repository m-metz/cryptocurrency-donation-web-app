package com.group13.cryptocurrencywebapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ResponseStatusException;

import com.group13.cryptocurrencywebapp.web_entity.benevity.BenevityDonation;
import com.group13.cryptocurrencywebapp.web_entity.benevity.causes.Cause;
import com.group13.cryptocurrencywebapp.web_entity.benevity.causes.detail.CauseDetail;
import com.nimbusds.oauth2.sdk.Response;
import net.minidev.json.JSONObject;
import reactor.core.publisher.Mono;

@Service
public class BenevityService {

    @Autowired
    @Qualifier("benevityClient")
    private WebClient webclient = WebClient.create();

    // Create a new donation
    public BenevityDonation createDonation(String benevityDonationStr) {

        BenevityDonation response = webclient.post()

                .uri("https://api.benevity-staging.org/donations")
                .body(Mono.just(benevityDonationStr), String.class)
                .retrieve()
                .bodyToMono(BenevityDonation.class)
                .block();
        return response;
    }

    // Get the status of an existing donation
    public BenevityDonation getDonationStatus(String id) {
        ResponseEntity<BenevityDonation> response = webclient.get()
                .uri("/donations/" + id)
                .retrieve()
                .toEntity(BenevityDonation.class)
                .block();

        BenevityDonation donation = response.getBody();
        // String state = null;

        // if (!(donation == null)) {
        //     state = donation.retrieveStatus();
        // }
        return donation;
    }

    public void sendReceiptEmail(String receiptId, String email){

        JSONObject payload = new JSONObject();

            JSONObject data = new JSONObject();

            data.appendField("type", "emails");
                JSONObject attributes = new JSONObject();
                attributes.appendField("to",email);

            data.appendField("attributes", attributes);

        payload.appendField("data", data);

        ResponseEntity<String> response = webclient.post()

                .uri("/receipts/" + receiptId + "/email")
                .body(Mono.just(payload.toJSONString()), String.class)
                .header("Content-type","application/vnd.api+json")
                .retrieve()
                .toEntity(String.class)
                .block();
        System.out.println("\n\n\n~~~~~~~RESPONSE");
        System.out.println(response.getStatusCode());

        if(response.getStatusCode().is2xxSuccessful()){
            System.out.println("\n\nTRANSACTION COMPLETE!");
        }
    }

    public Cause getOneCause(String id) {
        String uri = "/search/causes?q=id:" + id;
        ResponseEntity<Cause> response = webclient.get()
                .uri(uri)
                .retrieve()
                .toEntity(Cause.class)
                .block();

        Cause cause = response.getBody();

        if (cause != null) {
            return cause;
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Cause id=" + id + "not found!");
        }
    }

    public CauseDetail getCauseDetails(String id) {
        String uri = "/causes/" + id;
        ResponseEntity<CauseDetail> response = webclient.get()
                .uri(uri)
                .retrieve()
                .toEntity(CauseDetail.class)
                .block();

        CauseDetail cause = response.getBody();

        if (cause != null) {
            return cause;
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Cause id=" + id + "not found!");
        }
    }

}
