package com.group13.cryptocurrencywebapp.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

@RestController
@RequestMapping(path = "api/v1/cryptocurrencydonation")
public class CryptoCurrencyDonationController {
    private final CryptoCurrencyDonationService cryptoCurrencyDonationService;
    private final BenevityService benevityService;
    private final ExchangeService exchangeService;
    private final GeminiService geminiService;
    private final EtherscanService etherscanService;

    @Autowired
    public CryptoCurrencyDonationController(CryptoCurrencyDonationService cryptoCurrencyDonationService,
            BenevityService benevityService, ExchangeService exchangeService, GeminiService geminiService,
            EtherscanService etherscanService) {
        this.cryptoCurrencyDonationService = cryptoCurrencyDonationService;
        this.benevityService = benevityService;
        this.exchangeService = exchangeService;
        this.geminiService = geminiService;
        this.etherscanService = etherscanService;
    }

    // @GetMapping()
    // public List<CryptoCurrencyDonation> getAllCryptoCurrencyDonations() {
    // return cryptoCurrencyDonationService.getAllCryptoDonations();
    // }

    // Benevity Endpoints
    @GetMapping("/Benevity/causes/id={id}")
    public Cause getOBenevityCause(@PathVariable String id) {
        return benevityService.getOneCause(id);
    }

    @GetMapping("/Benevity/causes/{id}")
    public CauseDetail getCauseDetails(@PathVariable String id) {
        return benevityService.getCauseDetails(id);
    }

    @GetMapping("/Benevity/id={id}")
    public BenevityDonation getBenevityDonation(@PathVariable String id) {
        return benevityService.getDonationStatus(id);
    }

    @PostMapping("/Benevity/createDonation")
    public BenevityDonation createBenevityDonation(@RequestBody String benevityDonationStr) {
        return benevityService.createDonation(benevityDonationStr);
    }

    // Crypto Donation Endpoints
    @PostMapping("/createDonation")
    public CryptoCurrencyDonation addNewDonation(@RequestBody CryptoCurrencyDonation cryptoDonation) {
        return cryptoCurrencyDonationService.createNewDonation(cryptoDonation);
    }

    @GetMapping("/getDonation/all")
    public List<CryptoCurrencyDonation> getAllCryptoCurrencyDonations() {
        return cryptoCurrencyDonationService.getAllDonations();
    }

    @PostMapping(path = "/createDeposit/DonationId={id}")
    public CryptoTransfer createNewDeposit(@PathVariable int id) {
        return cryptoCurrencyDonationService.createDeposit(id);
    }

    // Testing transaction flow
    @PostMapping(path = "/flow/createDeposit/DonationId={id}")
    public void createFlowNewDeposit(@PathVariable int id) {
        cryptoCurrencyDonationService.createFlowNewDeposit(id);
    }

    @PostMapping(path = "/createTrade/DonationId={id}/Amount={amount}")
    public Trade createNewDeposit(@PathVariable int id, @PathVariable float amount) throws InterruptedException {
        return cryptoCurrencyDonationService.createTrade(id, amount);
    }

    @GetMapping("/getDeposit/all")
    public List<CryptoTransfer> getAllDeposits() {
        return cryptoCurrencyDonationService.getAllDeposits();
    }

    @GetMapping("/getFee/all")
    public List<Fee> getAllFee() {
        return cryptoCurrencyDonationService.getAllFees();
    }

    @GetMapping("/getdonations/userid={userid}")
    public List<CryptoCurrencyDonation> getAllDonationsForUser(@PathVariable String userid) {
        return cryptoCurrencyDonationService.getAllDonationsForUser(userid);
    }

    // Exchanges Endpoints
    @PostMapping(path = "exchange/newtrade/amount={amount}")
    public Order executeNewTrade(@PathVariable int amount) throws Exception {
        return geminiService.createNewOrder(amount);
    }

    @PostMapping(path = "exchange/newinstanttrade/amount={amount}")
    public InstantOrder createNewInstantOrder(@PathVariable float amount) throws Exception {
        return geminiService.createNewInstantOrder(amount);
    }

    @GetMapping(path = "exchange/account")
    public ExchangeAccount getAccountInfo() throws Exception {
        return exchangeService.getAccountInfo();
    }

    @PostMapping(path = "exchange/executetrade/amount={amount}")
    public ExchangeTradeResponse getAccountInfo(@PathVariable float amount) throws Exception {
        return exchangeService.executeNewTrade(amount);
    }

    @GetMapping(path = "gemini/balances")
    public List<Balance> getGeminiBalancesInfo() throws UnsupportedEncodingException {
        return geminiService.getBalancesInfo();
    }

    // EtherScan Endpoints
    @GetMapping(path = "ethereum/validateaddress/address={address}")
    public String validateEthereumAddress(@PathVariable String address) {
        return etherscanService.validateAddress(address);
    }

    @GetMapping(path = "ethereum/price")
    public Price getEthPrice() {
        return etherscanService.getEthPrice();
    }

}
