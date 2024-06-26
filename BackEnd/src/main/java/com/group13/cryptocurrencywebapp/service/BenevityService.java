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

import net.minidev.json.JSONObject;
import reactor.core.publisher.Mono;

/** 
 * <pre>
 * Class Name: BenevityService
 * 
 * Date Created: March 10, 2023
 * Company: Benevity
 * </pre>
 * 
 * <p>Service class that is defined with all of the functionality necessary to interact with Benevity's API
 * 
 * @author U of C ENSF609 Capstone 2023 (Alex K, Felipe G, Mike, M)
 * 
 * @Since April 09, 2023
 * 
 */

@Service
public class BenevityService {

    @Autowired
    @Qualifier("benevityClient")
    private WebClient webclient = WebClient.create();

    /**
     * Create a benevity donation, calling Benevity's API
     * @param benevityDonationStr A string containing the JSON body required to post to the Benevity API to create a Benevity donation 
     * @return BenevityDonation object containing the values sent in the JSON body response after creating a Benevity donation with the benevityDonationStr param
     */
    public BenevityDonation createDonation(String benevityDonationStr) {

        BenevityDonation response = webclient.post()

                .uri("https://api.benevity-staging.org/donations")
                .body(Mono.just(benevityDonationStr), String.class)
                .retrieve()
                .bodyToMono(BenevityDonation.class)
                .block();
        return response;
    }

    
    /**
     * Get the JSON body returned from retrieving a Benevity donation from their API
     * @param id A string containing the id of the Benevity donation
     * @return BenevityDonation object containing the values set in the JSON body contained in benevityDonationStr String parameter
     */
    public BenevityDonation getDonationStatus(String id) {
        ResponseEntity<BenevityDonation> response = webclient.get()
                .uri("/donations/" + id)
                .retrieve()
                .toEntity(BenevityDonation.class)
                .block();

        BenevityDonation donation = response.getBody();

        return donation;
    }

    /**
     * Send an email containing the tax receipt. Information is retrieved from the specified Benevity donation
     * @param receiptId A string containing the id of the Benevity donation's receipt id
     * @param email A string containing the email address you would like to send the donation to
     */
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

    /**
     *  Retrieve a single cause from Benevity's cause database
     * @param id A string containing the id of the Benevity cause
     * @return Cause object containing the information returned regarding that cause
     */
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

    /**
     * Get the JSON body returned from retrieving a Benevity cause's information
     * @param id A string containing the id of the Benevity cause
     * @return CauseDetail object containing the values returned from Benevity when requesting information regarding the specified cause
     */
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
