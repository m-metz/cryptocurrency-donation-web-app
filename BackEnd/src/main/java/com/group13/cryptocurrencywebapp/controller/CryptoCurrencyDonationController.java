package com.group13.cryptocurrencywebapp.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.group13.cryptocurrencywebapp.entity.CryptoCurrencyDonation;
import com.group13.cryptocurrencywebapp.entity.CryptoTransfer;
import com.group13.cryptocurrencywebapp.entity.Fee;
import com.group13.cryptocurrencywebapp.entity.Trade;
import com.group13.cryptocurrencywebapp.service.BenevityService;
import com.group13.cryptocurrencywebapp.service.CryptoCurrencyDonationService;
import com.group13.cryptocurrencywebapp.service.EtherscanService;
import com.group13.cryptocurrencywebapp.service.ExchangeService;
import com.group13.cryptocurrencywebapp.service.GeminiService;
import com.group13.cryptocurrencywebapp.web_entity.exchange.binance.ExchangeAccount;
import com.group13.cryptocurrencywebapp.web_entity.exchange.binance.ExchangeTradeResponse;
import com.group13.cryptocurrencywebapp.web_entity.exchange.gemini.Balance;
import com.group13.cryptocurrencywebapp.web_entity.exchange.gemini.InstantOrder;
import com.group13.cryptocurrencywebapp.web_entity.exchange.gemini.Order;
import com.group13.cryptocurrencywebapp.web_entity.benevity.BenevityDonation;
import com.group13.cryptocurrencywebapp.web_entity.benevity.causes.Cause;
import com.group13.cryptocurrencywebapp.web_entity.benevity.causes.detail.CauseDetail;
import com.group13.cryptocurrencywebapp.web_entity.etherscan.Price;

/**
 * <pre>
 * Class Name: CryptoCurrencyDonationController
 * 
 * Date Created: March 10, 2023
 * Company: Benevity
 * </pre>
 * 
 * <p>
 * Class that holds of the endpoint definitions used by the Front End to reach
 * and execute services offered by the CryptoCurrencyDonationApp
 * base URI of the controler "api/v1/cryptocurrencydonation".
 * </p>
 * 
 * @author U of C ENSF609 Capstone 2023 (Alex K, Felipe G, Mike, M)
 * 
 * @Since April 09, 2023
 * 
 */
@Configuration
@EnableAsync
@RestController
@RequestMapping(path = "api/v1/cryptocurrencydonation")
public class CryptoCurrencyDonationController {
    /**
     * cryptoCurrencyDonationService service object that used to reach all service
     * methods related to cryptoCurrencyDonation entity and its other supporting
     * entities
     */
    private final CryptoCurrencyDonationService cryptoCurrencyDonationService;
    /**
     * BenevityService service object that used to reach all service methods related
     * to Benevity API calls
     */
    private final BenevityService benevityService;
    /**
     * ExchangeService service object that used to reach all service methods related
     * to the chosen Exchange (Binance) API calls
     */
    private final ExchangeService exchangeService;
    /**
     * GeminiService service object that used to reach all service methods related
     * to the alternative Exchange (Gemini) API calls
     */
    private final GeminiService geminiService;
    /**
     * EtherscanService service object that used to reach all service methods
     * related to Etherscan service API calls
     */
    private final EtherscanService etherscanService;

    /**
     * Constructor class that aggregates all service objects used by the endpoints
     */
    public CryptoCurrencyDonationController(CryptoCurrencyDonationService cryptoCurrencyDonationService,
            BenevityService benevityService, ExchangeService exchangeService, GeminiService geminiService,
            EtherscanService etherscanService) {
        this.cryptoCurrencyDonationService = cryptoCurrencyDonationService;
        this.benevityService = benevityService;
        this.exchangeService = exchangeService;
        this.geminiService = geminiService;
        this.etherscanService = etherscanService;
    }

    // Benevity Endpoints
    /**
     * Get endpoint used to retrieve a specifc cause's basic information
     * 
     * @param id String id of the Benevity cause to be searched
     * @return Cause web entity object with all JSON fields holding cause's detailed
     *         information
     */
    @GetMapping("/Benevity/causes/id={id}")
    public Cause getOBenevityCause(@PathVariable String id) {
        return benevityService.getOneCause(id);
    }

    /**
     * Get endpoint used to retrieve a specifc cause's detailed information
     * 
     * @param id String id of the Benevity cause to be searched
     * @return Cause web entity object with all JSON fields holding cause's detailed
     *         information
     */
    @GetMapping("/Benevity/causes/{id}")
    public CauseDetail getCauseDetails(@PathVariable String id) {
        return benevityService.getCauseDetails(id);
    }

    /**
     * Get endpoint used to retrieve a specifc Benevity donation from Benevity's
     * internal system (Admin helper endpoint)
     * 
     * @param id String id of the Benevity donation to be searched
     * @return Benevity Donation web entity object with all JSON fields holding
     *         benevity's donation detailed
     *         information
     */
    @GetMapping("/Benevity/id={id}")
    public BenevityDonation getBenevityDonation(@PathVariable String id) {
        return benevityService.getDonationStatus(id);
    }

    /**
     * Post endpoint used to create a new Benevity donation in Benevity's internal
     * system (Admin helper endpoint)
     * 
     * @param benevityDonationStr String body with parameters to create new Benevity
     *                            donation
     * @return Benevity Donation web entity object with all JSON fields holding
     *         benevity's donation detailed
     *         information
     */
    @PostMapping("/Benevity/createDonation")
    public BenevityDonation createBenevityDonation(@RequestBody String benevityDonationStr) {
        return benevityService.createDonation(benevityDonationStr);
    }

    // Crypto Donation Endpoints
    /**
     * Post endpoint used to create a new CryptoCurrencyDonation in the Integration
     * Database and start internal flow of transactions
     * 
     * @param cryptoDonation String body with parameters to create new Benevity
     *                       donation
     */
    @Async
    @PostMapping("/createDonation")
    public void addNewDonation(@RequestBody CryptoCurrencyDonation cryptoDonation) {
        cryptoCurrencyDonationService.createNewDonation(cryptoDonation);
    }

    /**
     * Get endpoint used to get all CryptoCurrencyDonation in the Integration
     * Database (Admin Helper Function)
     * 
     * @return List<CryptoCurrencyDonation> list of all CryptoCurrencyDonation
     *         objects with all JSON fields holding
     *         CryptoCurrencyDonation detailed in the database
     */
    @GetMapping("/getDonation/all")
    public List<CryptoCurrencyDonation> getAllCryptoCurrencyDonations() {
        return cryptoCurrencyDonationService.getAllDonations();
    }

    /**
     * Post endpoint used to create a new CryptoExchange and start transaction flow
     * 
     * @param cryptoDonation String body with parameters to create new Benevity
     *                       donation
     * @return CryptoCurrencyDonation entity object with all JSON fields holding
     *         CryptoCurrencyDonation's detailed
     *         information
     */
    @PostMapping(path = "/flow/createDeposit/DonationId={id}")
    public void createFlowNewDeposit(@PathVariable int id) {
        cryptoCurrencyDonationService.createFlowNewDeposit(id);
    }

    /**
     * Get endpoint used to get all CryptoTransfer objects in the Integration
     * Database (Admin Helper Function)
     * 
     * @return List<CryptoTransfer> list of all CryptoTransfer
     *         objects with all JSON fields holding
     *         CryptoTransfer's detailed information
     */
    @GetMapping("/getDeposit/all")
    public List<CryptoTransfer> getAllDeposits() {
        return cryptoCurrencyDonationService.getAllDeposits();
    }

    /**
     * Get endpoint used to get all Fee objects in the Integration
     * Database (Admin Helper Function)
     * 
     * @return List<Fee> list of all Fee
     *         objects with all JSON fields holding
     *         Fee's detailed information
     */
    @GetMapping("/getFee/all")
    public List<Fee> getAllFee() {
        return cryptoCurrencyDonationService.getAllFees();
    }

    /**
     * Get endpoint used to get all Trade objects in the Integration
     * Database (Admin Helper Function)
     * 
     * @return List<Trade> list of all Fee
     *         objects with all JSON fields holding
     *         Trade's detailed information
     */
    @GetMapping("getTrade/all")
    public List<Trade> getAllTrade() {
        return cryptoCurrencyDonationService.getAllTrades();
    }

    /**
     * Get endpoint used to get all CryptoCurrencyDonation objects in the
     * Integration
     * Database for a specifc user.
     * 
     * @param userid String userid to be user as search argument to retrive
     *               CryptoCurrencyDonation objects in the database
     * 
     * @return List<CryptoCurrencyDonation> list of all CryptoCurrencyDonation
     *         objects found with all JSON fields holding
     *         Fee's detailed information
     */
    @GetMapping("/getdonations/userid={userid}")
    public List<CryptoCurrencyDonation> getAllDonationsForUser(@PathVariable String userid) {
        return cryptoCurrencyDonationService.getAllDonationsForUser(userid);
    }

    // Exchanges Endpoints
    // Gemini trading endpoints
    /**
     * Post endpoint used to create a new Trade order in Gemini's System (Admin
     * Helper Function)
     * 
     * @param amount in value to be traded into USD
     * @return Gemini Order web entity order mapped in JSON detailed fileds
     */
    @PostMapping(path = "exchange/newtrade/amount={amount}")
    public Order executeNewTrade(@PathVariable int amount) throws Exception {
        return geminiService.createNewOrder(amount);
    }

    /**
     * Post endpoint used to create a new Instant Trade order in Gemini's System
     * (Admin Helper Function)
     * 
     * @param amount in value to be traded into USD
     * @return Gemini InstantOrder web entity order mapped in JSON detailed fileds
     */
    @PostMapping(path = "exchange/newinstanttrade/amount={amount}")
    public InstantOrder createNewInstantOrder(@PathVariable float amount) throws Exception {
        return geminiService.createNewInstantOrder(amount);
    }

    /**
     * Get endpoint used to account balances in Gemini's System (Admin Helper
     * Function)
     * 
     * @return ExchangeAccount web entity object mapped in JSON detailed fields
     */
    @GetMapping(path = "gemini/balances")
    public List<Balance> getGeminiBalancesInfo() throws UnsupportedEncodingException {
        return geminiService.getBalancesInfo();
    }

    // Binance Endpoints
    /**
     * Get endpoint used to get account balances from Binance (Admin Helper
     * Function)
     * 
     * @return ExchangeAccount web entity object mapped in JSON detailed fields
     */
    @GetMapping(path = "exchange/account")
    public ExchangeAccount getAccountInfo() throws Exception {
        return exchangeService.getAccountInfo();
    }

    /**
     * Post endpoint used to create a new Spot Trade order in Binance's System
     * (Admin Helper Function)
     * 
     * @param amount in value to be traded into USD
     * @return Gemini InstantOrder web entity order mapped in JSON detailed fileds
     */
    @PostMapping(path = "exchange/executetrade/amount={amount}")
    public ExchangeTradeResponse getAccountInfo(@PathVariable float amount) throws Exception {
        return exchangeService.executeNewTrade(amount);
    }

    // EtherScan Endpoints
    /**
     * Get endpoint used to validade Ethereum's address passed by the user
     * 
     * @param address Ethereum address to be validated
     * @return status of the address. Possible values: Valid Ethereum Address or
     *         Invalid Address.
     */
    @GetMapping(path = "ethereum/validateaddress/address={address}")
    public String validateEthereumAddress(@PathVariable String address) {
        return etherscanService.validateAddress(address);
    }

    /**
     * Get endpoint used to get Eth's current USD price
     * 
     * @return Price web entity containing valid USD price and time of the quote
     */
    @GetMapping(path = "ethereum/price")
    public Price getEthPrice() {
        return etherscanService.getEthPrice();
    }

}
