package com.group13.cryptocurrencywebapp.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.support.Repositories;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.group13.cryptocurrencywebapp.entity.CryptoCurrencyDonation;
import com.group13.cryptocurrencywebapp.entity.CryptoTransfer;
import com.group13.cryptocurrencywebapp.entity.Fee;
import com.group13.cryptocurrencywebapp.entity.Trade;
import com.group13.cryptocurrencywebapp.repository.CryptoCurrencyDonationRepository;
import com.group13.cryptocurrencywebapp.repository.CryptoTransferRepository;
import com.group13.cryptocurrencywebapp.repository.FeeRepository;
import com.group13.cryptocurrencywebapp.web_entity.etherscan.transaction.Result;
import com.group13.cryptocurrencywebapp.web_entity.exchange.binance.ExchangeTradeResponse;
import com.group13.cryptocurrencywebapp.web_entity.exchange.binance.Fill;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;

import com.group13.cryptocurrencywebapp.repository.TradeRepository;

@Service
public class CryptoCurrencyDonationService {
    private final CryptoCurrencyDonationRepository cryptoCurrencyDonationRepository;
    private final CryptoTransferRepository cryptoTransferRepository;
    private final FeeRepository feeRepository;
    private final EtherscanService etherscanService;
    private final TradeService tradeService;
    private final TradeRepository tradeRepository;
    private final ExchangeService exchangeService;
    private final BenevityService benevityService;

    @Autowired
    public CryptoCurrencyDonationService(CryptoCurrencyDonationRepository cryptoCurrencyDonationRepository,
            CryptoTransferRepository cryptoTransferRepository,
            FeeRepository feeRepository,
            EtherscanService etherscanService,
            BenevityService benevityService,
            TradeService tradeService, TradeRepository tradeRepository, ExchangeService exchangeService) {
        this.cryptoCurrencyDonationRepository = cryptoCurrencyDonationRepository;
        this.tradeService = tradeService;
        this.tradeRepository = tradeRepository;
        this.cryptoTransferRepository = cryptoTransferRepository;
        this.etherscanService = etherscanService;
        this.benevityService = benevityService;
        this.feeRepository = feeRepository;
        this.exchangeService = exchangeService;
    }

    public CryptoCurrencyDonation createNewDonation(CryptoCurrencyDonation cryptoDonation) {

        cryptoDonation = cryptoCurrencyDonationRepository.save(cryptoDonation);

        return cryptoDonation;
    }

    public List<CryptoCurrencyDonation> getAllDonations() {
        return cryptoCurrencyDonationRepository.findAll();
    }

    public Trade createTrade(int donationId, float amount) throws InterruptedException {
        CryptoCurrencyDonation donation = cryptoCurrencyDonationRepository.findById(donationId).get();
        Trade newTrade = new Trade();

        newTrade.setCurrency("ETH");
        newTrade.setAmount(amount);
        newTrade.setTime(LocalDateTime.now());

        ExchangeTradeResponse response = exchangeService.executeNewTrade(amount);

        if (response != null) {
            newTrade.setExchangeReferenceId(response.getClientOrderId());
            float convertedAmount = 0;
            float comission = 0;
            String comissiontAsset = "";
            for (Fill fill : response.getFills()) {
                convertedAmount = convertedAmount
                        + (Float.parseFloat(fill.getQty()) * Float.parseFloat(fill.getPrice()));
                comission = comission + Float.parseFloat(fill.getCommission());
                comissiontAsset = fill.getCommissionAsset();

            }
            newTrade.setConvertedAmount(convertedAmount);
            newTrade.setFinal_amount(convertedAmount);
            newTrade.setToCurrency(comissiontAsset);
            newTrade = tradeRepository.save(newTrade);

            Fee comissionFee = new Fee(comission, newTrade, "Trade", comissiontAsset);
            comissionFee = feeRepository.save(comissionFee);

            List<Fee> fees = new ArrayList<>();
            fees.add(comissionFee);

            newTrade.setFees(fees);
            newTrade = tradeRepository.save(newTrade);

            donation.setTrade(newTrade);
            donation = cryptoCurrencyDonationRepository.save(donation);

            // INSERT HERE CONNECTION WITH BENEVITY DONATION
            createBenevityDonation(donation, "USD");
            return newTrade;

        } else {
            return null;
        }

    }

    public List<CryptoTransfer> getAllDeposits() {
        return cryptoTransferRepository.findAll();
    }

    public CryptoTransfer createDeposit(int id) {
        CryptoCurrencyDonation donation = cryptoCurrencyDonationRepository.findById(id).get();

        CryptoTransfer deposit = new CryptoTransfer();

        deposit.setAmount(donation.getInitialCryptoAmount());
        deposit.setCurrency("ETH"); // This is hardcoded. In the future, for other coins,
                                    // you may need to collect this
                                    // information from the front end when
                                    // you create the cryptoDonation object

        deposit.setTime(java.time.LocalDateTime.now());

        Result latest = filterTransactions(donation.getToCryptoAddress(), donation.getFromCryptoAddress());
        deposit.setExchangeReferenceId(latest.getHash());
        deposit = cryptoTransferRepository.save(deposit);

        Fee gasFee = new Fee(Float.parseFloat(latest.getGasUsed()), deposit, "Gas", "ETH");
        gasFee = feeRepository.save(gasFee);

        List<Fee> fees = new ArrayList<>();
        fees.add(gasFee);
        // had to change because of immutable list
        // deposit.setFees(Arrays.asList(new Fee[] { gasFee }));
        deposit.setFees(fees);
        deposit.setFinal_amount(
                deposit.getAmount() - Float.parseFloat(latest.getGasUsed()) / (float) 1000000000000000000.0);

        // deposit = cryptoTransferRepository.save(deposit);
        CryptoTransfer deposit1 = cryptoTransferRepository.findById(deposit.getTransactionId()).get();

        donation.setCryptoTransfer(deposit1);
        donation = cryptoCurrencyDonationRepository.save(donation);

        // gasFee.setTransaction(deposit1);
        // gasFee = feeRepository.save(deposit.getFees().get(0));

        // INSERT CONNECTION TO TRADE HERE

        return deposit1;
    }

    public void createBenevityDonation(CryptoCurrencyDonation donation, String currency) {
        JSONObject payload = new JSONObject();
            JSONObject data = new JSONObject();
            data.appendField("type",
                    "donations");

            JSONObject attributes = new JSONObject();

            JSONObject destination = new JSONObject();
            destination.appendField("recipientId",
                    donation.getNonProfitId());
            attributes.appendField("destination", destination);
            
            JSONObject donor = new JSONObject();
            donor.appendField("fullName",
                    donation.getTaxReceipt().getGivenNames() + " " +
                            donation.getTaxReceipt().getLastName());
            donor.appendField("email",
                    donation.getTaxReceipt().getEmail());
            donor.appendField("receipted",
                    true);

            JSONObject address = new JSONObject();
            address.appendField("city",
                    donation.getTaxReceipt().getCity());
            address.appendField("country",
                    donation.getTaxReceipt().getCountry());
            address.appendField("line1",
                    donation.getTaxReceipt().getAddress1());
            address.appendField("line2",
                    donation.getTaxReceipt().getAddress2());
            address.appendField("state",
                    donation.getTaxReceipt().getStateProvinceRegion());
            address.appendField("zip",
                    donation.getTaxReceipt().getZipPostalCode());

            donor.appendField("address",address);

            attributes.appendField("donor",donor);

            JSONObject funds = new JSONObject();
            funds.appendField("amount",
                    donation.getTaxReceipt().getAmount());
            funds.appendField("currencyCode",
                    currency);
            funds.appendField("paymentType",
                    "DONATION_REPORT");
            funds.appendField("source",
                    "COMPANY");
            attributes.appendField("funds",funds);

            // Metadata if needed
            // JSONArray metadata = new JSONArray();
            // metadata.add(new JSONObject().appendField("amount",
            // donation.getTaxReceipt().getAmount()));

            data.appendField("attributes",(attributes));

        payload.appendField("data", data);
        System.out.println("Donation body created: \n" + payload.toJSONString());
        benevityService.createDonation(payload.toJSONString());


        // JSONArray payload = new JSONArray();
        //     JSONArray data = new JSONArray();
        //     data.add(new JSONObject().appendField("type",
        //             "donations"));

        //     JSONArray attributes = new JSONArray();

        //     JSONArray destination = new JSONArray();
        //     destination.add(new JSONObject().appendField("recipientId",
        //             donation.getNonProfitId()));

        //     JSONArray donor = new JSONArray();
        //     donor.add(new JSONObject().appendField("fullName",
        //             donation.getTaxReceipt().getGivenNames() + " " +
        //                     donation.getTaxReceipt().getLastName()));
        //     donor.add(new JSONObject().appendField("email",
        //             donation.getTaxReceipt().getEmail()));
        //     donor.add(new JSONObject().appendField("receipted",
        //             true));

        //     JSONArray address = new JSONArray();
        //     address.add(new JSONObject().appendField("city",
        //             donation.getTaxReceipt().getCity()));
        //     address.add(new JSONObject().appendField("country",
        //             donation.getTaxReceipt().getCountry()));
        //     address.add(new JSONObject().appendField("line1",
        //             donation.getTaxReceipt().getAddress1()));
        //     address.add(new JSONObject().appendField("line2",
        //             donation.getTaxReceipt().getAddress2()));
        //     address.add(new JSONObject().appendField("state",
        //             donation.getTaxReceipt().getStateProvinceRegion()));
        //     address.add(new JSONObject().appendField("zip",
        //             donation.getTaxReceipt().getZipPostalCode()));

        //     donor.add(address);

        //     attributes.add(donor);

        //     JSONArray funds = new JSONArray();
        //     funds.add(new JSONObject().appendField("amount",
        //             donation.getTaxReceipt().getAmount()));
        //     funds.add(new JSONObject().appendField("currency",
        //             currency));
        //     funds.add(new JSONObject().appendField("paymentType",
        //             "DONATION_REPORT"));
        //     funds.add(new JSONObject().appendField("source",
        //             "COMPANY"));
        //     attributes.add(funds);

        //     // Metadata if needed
        //     // JSONArray metadata = new JSONArray();
        //     // metadata.add(new JSONObject().appendField("amount",
        //     // donation.getTaxReceipt().getAmount()));

        //     data.add(attributes);

        // payload.add(data);
        // System.out.println("Donation body created: \n" + payload.toJSONString());
        // benevityService.createDonation(payload.toJSONString());
        
    }

    public Result filterTransactions(String toAddress, String fromAddress) {
        List<Result> allTransactions = etherscanService.getTransactions(toAddress);

        Result latest = null;

        for (int i = 0; i < allTransactions.size(); i++) {
            if (allTransactions.get(i).getFrom().equals(fromAddress.toLowerCase())) {
                latest = allTransactions.get(i);
                break;
            }
        }

        return latest;
    }

    public List<Fee> getAllFees() {
        return feeRepository.findAll();
    }

    public List<CryptoCurrencyDonation> getAllDonationsForUser(String userid) {
        if (userid == null || userid.equals("")) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Please inform a valid userid");
        }
        List<CryptoCurrencyDonation> donations = cryptoCurrencyDonationRepository.findAllByDonorUserId(userid);

        if (donations == null || donations.isEmpty() == true) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "No donations found for the userid:  " + userid + ".");
        } else {
            return donations;

        }

    }

    // Transaction Flow methods
    public void createFlowNewDeposit(int id) {
        CryptoCurrencyDonation donation = cryptoCurrencyDonationRepository.findById(id).get();

        CryptoTransfer deposit = new CryptoTransfer();

        deposit.setAmount(donation.getInitialCryptoAmount());
        deposit.setCurrency("ETH"); // This is hardcoded. In the future, for other coins,
                                    // you may need to collect this
                                    // information from the front end when
                                    // you create the cryptoDonation object

        deposit.setTime(java.time.LocalDateTime.now());

        Result latest = filterTransactions(donation.getToCryptoAddress(), donation.getFromCryptoAddress());
        deposit.setExchangeReferenceId(latest.getHash());
        deposit = cryptoTransferRepository.save(deposit);

        Fee gasFee = new Fee(Float.parseFloat(latest.getGasUsed()), deposit, "Gas", "ETH");
        gasFee = feeRepository.save(gasFee);

        List<Fee> fees = new ArrayList<>();
        fees.add(gasFee);
        // had to change because of immutable list
        // deposit.setFees(Arrays.asList(new Fee[] { gasFee }));
        deposit.setFees(fees);
        deposit.setFinal_amount(
                deposit.getAmount() - Float.parseFloat(latest.getGasUsed()) / (float) 1000000000000000000.0);

        // deposit = cryptoTransferRepository.save(deposit);
        CryptoTransfer deposit1 = cryptoTransferRepository.findById(deposit.getTransactionId()).get();

        donation.setCryptoTransfer(deposit1);
        donation = cryptoCurrencyDonationRepository.save(donation);

        try {
            createFlowTrade(donation.getDonationId(), deposit.getFinal_amount());
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println("Trade creation Failed");
        }
    }

    public void createFlowTrade(int donationId, float amount) throws InterruptedException {
        CryptoCurrencyDonation donation = cryptoCurrencyDonationRepository.findById(donationId).get();
        Trade newTrade = new Trade();

        newTrade.setCurrency("ETH");
        newTrade.setAmount(amount);
        newTrade.setTime(LocalDateTime.now());

        ExchangeTradeResponse response = exchangeService.executeNewTrade(amount);

        if (response != null) {
            newTrade.setExchangeReferenceId(response.getClientOrderId());
            float convertedAmount = 0;
            float comission = 0;
            String comissiontAsset = "";
            for (Fill fill : response.getFills()) {
                convertedAmount = convertedAmount
                        + (Float.parseFloat(fill.getQty()) * Float.parseFloat(fill.getPrice()));
                comission = comission + Float.parseFloat(fill.getCommission());
                comissiontAsset = fill.getCommissionAsset();
            }
            newTrade.setConvertedAmount(convertedAmount);
            newTrade.setFinal_amount(convertedAmount);
            newTrade.setToCurrency(comissiontAsset);
            newTrade = tradeRepository.save(newTrade);

            Fee comissionFee = new Fee(comission, newTrade, "Trade", comissiontAsset);
            comissionFee = feeRepository.save(comissionFee);

            List<Fee> fees = new ArrayList<>();
            fees.add(comissionFee);

            newTrade.setFees(fees);
            newTrade = tradeRepository.save(newTrade);

            donation.setTrade(newTrade);
            donation = cryptoCurrencyDonationRepository.save(donation);

            createBenevityDonation(donation, newTrade.getToCurrency());

        }

    }

}
