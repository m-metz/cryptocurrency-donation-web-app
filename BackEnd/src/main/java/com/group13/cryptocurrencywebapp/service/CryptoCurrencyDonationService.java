package com.group13.cryptocurrencywebapp.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.support.Repositories;
import org.springframework.stereotype.Service;
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

    @Autowired
    public CryptoCurrencyDonationService(CryptoCurrencyDonationRepository cryptoCurrencyDonationRepository,
            CryptoTransferRepository cryptoTransferRepository,
            FeeRepository feeRepository,
            EtherscanService etherscanService,
            TradeService tradeService, TradeRepository tradeRepository, ExchangeService exchangeService) {
        this.cryptoCurrencyDonationRepository = cryptoCurrencyDonationRepository;
        this.tradeService = tradeService;
        this.tradeRepository = tradeRepository;
        this.cryptoTransferRepository = cryptoTransferRepository;
        this.etherscanService = etherscanService;
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
        newTrade.setToCurrency("USD");

        ExchangeTradeResponse response = exchangeService.executeNewTrade(amount);

        if (response != null) {
            newTrade.setExchangeReferenceId(response.getClientOrderId());
            float convertedAmount = 0;
            float comission = 0;
            for (Fill fill : response.getFills()) {
                convertedAmount = convertedAmount + Float.parseFloat(fill.getQty());
                comission = comission + Float.parseFloat(fill.getCommission());
            }
            newTrade.setConvertedAmount(convertedAmount);
            newTrade.setFinal_amount(convertedAmount);
            newTrade = tradeRepository.save(newTrade);

            Fee comissionFee = new Fee(comission, newTrade, "Trade", "USD");
            comissionFee = feeRepository.save(comissionFee);

            List<Fee> fees = new ArrayList<>();
            fees.add(comissionFee);

            newTrade.setFees(fees);
            newTrade = tradeRepository.save(newTrade);

            donation.setTrade(newTrade);
            donation = cryptoCurrencyDonationRepository.save(donation);

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

}
