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
    public String getDonationStatus(String id) {
        ResponseEntity<BenevityDonation> response = webclient.get()
                .uri("/donations/" + id)
                .retrieve()
                .toEntity(BenevityDonation.class)
                .block();

        BenevityDonation donation = response.getBody();
        String state = null;

        if (!(donation == null)) {
            state = donation.retrieveStatus();
        }
        return state;
    }

    public Cause getOneCause(String id) {
        String uri = "/search/causes?q=" + "\"" + id + "\"";
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

}
