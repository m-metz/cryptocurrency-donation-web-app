package com.group13.cryptocurrencywebapp.service;

import java.util.ArrayList;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.group13.cryptocurrencywebapp.entity.CryptoCurrencyDonation;
import com.group13.cryptocurrencywebapp.entity.CryptoTransfer;
import com.group13.cryptocurrencywebapp.entity.Fee;
import com.group13.cryptocurrencywebapp.entity.Trade;
import com.group13.cryptocurrencywebapp.repository.CryptoCurrencyDonationRepository;
import com.group13.cryptocurrencywebapp.repository.CryptoTransferRepository;
import com.group13.cryptocurrencywebapp.repository.FeeRepository;
import com.group13.cryptocurrencywebapp.web_entity.benevity.BenevityDonation;
import com.group13.cryptocurrencywebapp.web_entity.etherscan.transaction.Result;
import com.group13.cryptocurrencywebapp.web_entity.exchange.binance.ExchangeTradeResponse;
import com.group13.cryptocurrencywebapp.web_entity.exchange.binance.Fill;
import com.group13.cryptocurrencywebapp.repository.TradeRepository;

import net.minidev.json.JSONObject;


@Configuration
@EnableScheduling
@Service
public class CryptoCurrencyDonationService {
    private final CryptoCurrencyDonationRepository cryptoCurrencyDonationRepository;
    private final CryptoTransferRepository cryptoTransferRepository;
    private final FeeRepository feeRepository;
    private final EtherscanService etherscanService;
    private final TradeRepository tradeRepository;
    private final ExchangeService exchangeService;
    private final BenevityService benevityService;

    @Autowired
    public CryptoCurrencyDonationService(
            CryptoCurrencyDonationRepository cryptoCurrencyDonationRepository,
            CryptoTransferRepository cryptoTransferRepository, FeeRepository feeRepository,
            EtherscanService etherscanService, BenevityService benevityService,
            TradeRepository tradeRepository, ExchangeService exchangeService) {

        this.cryptoCurrencyDonationRepository = cryptoCurrencyDonationRepository;
        this.tradeRepository = tradeRepository;
        this.cryptoTransferRepository = cryptoTransferRepository;
        this.etherscanService = etherscanService;
        this.benevityService = benevityService;
        this.feeRepository = feeRepository;
        this.exchangeService = exchangeService;
    }

    public CryptoCurrencyDonation createNewDonation(CryptoCurrencyDonation cryptoDonation) {

        if (cryptoDonation != null) {
            cryptoDonation.setStatus("NEW");

            if (cryptoDonation.getTaxReceipt().getAmount() == -999) {

                System.out.println("CHANGING VALUE~~~~~~~~~~~~~~~~~~\n\n");
                float ethPrice =
                        Float.parseFloat(etherscanService.getEthPrice().getResult().getEthusd());
                cryptoDonation.getTaxReceipt()
                        .setAmount(cryptoDonation.getInitialCryptoAmount() * ethPrice);
                System.out.println(cryptoDonation.getTaxReceipt().getAmount());
                System.out.println("CHANGING VALUE~~~~~~~~~~~~~~~~~~\n\n");
            }
            // TODO Create a catch for if etherscan is down

            cryptoDonation = cryptoCurrencyDonationRepository.save(cryptoDonation);

            return cryptoDonation;
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Donation Creation Failed! Invalid information sent.");
        }

    }

    public List<CryptoCurrencyDonation> getAllDonations() {
        return cryptoCurrencyDonationRepository.findAll();
    }


    public List<CryptoTransfer> getAllDeposits() {
        return cryptoTransferRepository.findAll();
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

    public List<Trade> getAllTrades() {
        return tradeRepository.findAll();
    }

    public List<CryptoCurrencyDonation> getAllDonationsForUser(String userid) {
        if (userid == null || userid.equals("")) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Please inform a valid userid");
        }
        List<CryptoCurrencyDonation> donations =
                cryptoCurrencyDonationRepository.findAllByDonorUserId(userid);

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

        if (donation.getStatus().equals("NEW")) {
            donation.setStatus("D-INPROGRESS");
            System.out.println("\n\n~~~StatusUpdate: " + donation.getStatus());
        } else {
            System.out.println("This donation is not in the correct state to perform a deposit!");
            return;
        }

        CryptoTransfer deposit = new CryptoTransfer();

        deposit.setAmount(donation.getInitialCryptoAmount());
        deposit.setCurrency("ETH"); // This is hardcoded. In the future, for other coins,
                                    // you may need to collect this
                                    // information from the front end when
                                    // you create the cryptoDonation object

        deposit.setTime(java.time.LocalDateTime.now());

        Result latest =
                filterTransactions(donation.getToCryptoAddress(), donation.getFromCryptoAddress());
        deposit.setExchangeReferenceId(latest.getHash());
        deposit = cryptoTransferRepository.save(deposit);

        Fee gasFee = new Fee(Float.parseFloat(latest.getGasUsed()), deposit, "Gas", "ETH");
        gasFee = feeRepository.save(gasFee);

        List<Fee> fees = new ArrayList<>();
        fees.add(gasFee);
        deposit.setFees(fees);
        deposit.setFinal_amount(deposit.getAmount()
                - Float.parseFloat(latest.getGasUsed()) / (float) 1000000000000000000.0);

        CryptoTransfer deposit1 =
                cryptoTransferRepository.findById(deposit.getTransactionId()).get();

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
        CryptoCurrencyDonation donation =
                cryptoCurrencyDonationRepository.findById(donationId).get();
        Trade newTrade = new Trade();

        if (donation.getStatus().equals("D-INPROGRESS")) {
            donation.setStatus("T-INPROGRESS");
            System.out.println("\n\n~~~StatusUpdate: " + donation.getStatus());
        } else {
            System.out.println("This donation is not in the correct state to perform a trade!");
            return;
        }

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

            createBenevityDonation(donation, "USD", 0);

        } else {
            donation.setStatus("T-TIMEOUT");
            cryptoCurrencyDonationRepository.save(donation);
            return;
        }

    }

    public void createBenevityDonation(CryptoCurrencyDonation donation, String currency,
            int timesTried) {

        if (donation.getStatus().equals("T-INPROGRESS")) {
            donation.setStatus("BD-INPROGRESS");
            donation = cryptoCurrencyDonationRepository.save(donation);
            System.out.println("\n\n~~~StatusUpdate: " + donation.getStatus());
        } else {
            System.out.println(
                    "This donation is not in the correct state to create a Benevity donation!");
            return;
        }

        JSONObject payload = new JSONObject();
        JSONObject data = new JSONObject();
        data.appendField("type", "donations");

        JSONObject attributes = new JSONObject();

        JSONObject destination = new JSONObject();
        destination.appendField("recipientId", donation.getNonProfitId());
        attributes.appendField("destination", destination);

        JSONObject donor = new JSONObject();
        donor.appendField("fullName", donation.getTaxReceipt().getGivenNames() + " "
                + donation.getTaxReceipt().getLastName());
        donor.appendField("email", donation.getTaxReceipt().getEmail());
        donor.appendField("receipted", donation.getReceipted());

        JSONObject address = new JSONObject();
        address.appendField("city", donation.getTaxReceipt().getCity());
        address.appendField("country", donation.getTaxReceipt().getCountry());
        address.appendField("line1", donation.getTaxReceipt().getAddress1());
        address.appendField("line2", donation.getTaxReceipt().getAddress2());
        address.appendField("state", donation.getTaxReceipt().getStateProvinceRegion());
        address.appendField("zip", donation.getTaxReceipt().getZipPostalCode());

        donor.appendField("address", address);

        attributes.appendField("donor", donor);

        JSONObject funds = new JSONObject();
        funds.appendField("amount", (int) donation.getTaxReceipt().getAmount() * 100);
        funds.appendField("currencyCode", currency);
        funds.appendField("paymentType", "DONATION_REPORT");
        funds.appendField("source", "COMPANY");
        attributes.appendField("funds", funds);

        // Metadata if needed
        // JSONArray metadata = new JSONArray();
        // metadata.add(new JSONObject().appendField("amount",
        // donation.getTaxReceipt().getAmount()));

        data.appendField("attributes", (attributes));

        payload.appendField("data", data);
        System.out.println("Donation body created: \n" + payload.toJSONString());
        BenevityDonation benevityResponse = benevityService.createDonation(payload.toJSONString());

        BenevityDonation status =
                benevityService.getDonationStatus(benevityResponse.retrieveDonationId());

        int retryCount = 1;
        while (status.retrieveStatus().equals("ACCEPTED")) {
            System.out.println("Benevity donation is not yet approved. status = "
                    + status.retrieveStatus() + ". Waiting 1 min to retry....");
            try {
                Thread.sleep(60000 * retryCount);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            status = benevityService.getDonationStatus(benevityResponse.retrieveDonationId());

        }

        if (status.retrieveStatus().equals("DECLINED")) {
            System.out.println("Donation was declined!");

            if (timesTried >= 4) {
                System.out.println(
                        "Donation retried 5 times with no result! Contact a system administrator.");
                donation.setStatus("BD-TIMEOUT");
                donation = cryptoCurrencyDonationRepository.save(donation);
                return;
            }

            createBenevityDonation(donation, currency, timesTried + 1);

        } else if (status.retrieveStatus().equals("INITIATED")) {

            System.out.println("Benevity donation has been accepted!");
            donation.setBenevityDonationId(benevityResponse.retrieveDonationId());
            donation.setStatus("COMPLETE");
            donation = cryptoCurrencyDonationRepository.save(donation);

            if (donation.getReceipted() == true && timesTried == 0) {
                benevityService.sendReceiptEmail(status.retrieveReceiptId(),
                        donation.getTaxReceipt().getEmail());
            }
        } else {
            System.out.println(
                    "Unknown donation status encountered. Contact a system administrator.");
            donation.setStatus("BD-UNKNOWNSTATUS");
            donation = cryptoCurrencyDonationRepository.save(donation);
        }

    }

    @Transactional
    @Scheduled(fixedDelayString = "${fixedDelay.in.milliseconds}",
            initialDelayString = "${initialDelay.in.milliseconds}")
    public void retryTimedoutCryptoDonations() throws InterruptedException {
        List<CryptoCurrencyDonation> donations =
                cryptoCurrencyDonationRepository.findByStatusContaining("TIMEDOUT");
        if (donations.isEmpty()) {
            System.out.println("No Timedout donations found");
            return;
        }
        for (CryptoCurrencyDonation donation : donations) {
            if (donation.getStatus().equals("D-TIMEDOUT")) {
                CryptoTransfer deposit = donation.getCryptoTransfer();
                donation.setCryptoTransfer(null);
                donation = cryptoCurrencyDonationRepository.save(donation);
                if (deposit != null) {
                    // Deleting failed Crypto Transfer
                    List<Fee> fees = deposit.getFees();
                    deposit.setFees(null);
                    deposit = cryptoTransferRepository.save(deposit);
                    if (fees.isEmpty() == false) {
                        for (Fee fee : fees) {
                            feeRepository.delete(fee);
                        }
                    }

                    cryptoTransferRepository.delete(deposit);
                }
                // Updating Donation and Starting over CryptoTransfer flow
                donation.setStatus("NEW");
                donation = cryptoCurrencyDonationRepository.save(donation);
                createFlowNewDeposit(donation.getDonationId());
                System.out.println("Deposit recovery for CryptoDonation id: "
                        + donation.getDonationId() + "executed!");
            } else if (donation.getStatus().equals("T-TIMEDOUT")) {
                Trade trade = donation.getTrade();
                donation.setTrade(null);
                donation = cryptoCurrencyDonationRepository.save(donation);
                if (trade != null) {
                    // Deleting failed Trade
                    List<Fee> fees = trade.getFees();
                    trade.setFees(null);
                    trade = tradeRepository.save(trade);
                    if (fees.isEmpty() == false) {
                        for (Fee fee : fees) {
                            feeRepository.delete(fee);
                        }
                    }
                    tradeRepository.delete(trade);
                }
                // Updating Donation and Starting over Trade flow
                donation.setStatus("D-INPROGRESS");
                donation = cryptoCurrencyDonationRepository.save(donation);
                createFlowTrade(donation.getDonationId(),
                        donation.getCryptoTransfer().getFinal_amount());
                System.out.println("Trade recovery for CryptoDonation id: "
                        + donation.getDonationId() + "executed!");
            } else {
                // Updating Donation and Starting over Benevity Donation flow
                donation.setStatus("T-INPROGRESS");
                donation = cryptoCurrencyDonationRepository.save(donation);
                createBenevityDonation(donation, "USD", 0);
                System.out.println("Benevity Donation recovery for CryptoDonation id: "
                        + donation.getDonationId() + "executed!");
            }
        }
    }


}
