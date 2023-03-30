package com.group13.cryptocurrencywebapp.service;

import java.util.Arrays;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.group13.cryptocurrencywebapp.entity.CryptoCurrencyDonation;
import com.group13.cryptocurrencywebapp.entity.CryptoTransfer;
import com.group13.cryptocurrencywebapp.entity.Fee;
import com.group13.cryptocurrencywebapp.entity.Trade;
import com.group13.cryptocurrencywebapp.repository.CryptoCurrencyDonationRepository;
import com.group13.cryptocurrencywebapp.repository.CryptoTransferRepository;
import com.group13.cryptocurrencywebapp.repository.FeeRepository;
import com.group13.cryptocurrencywebapp.web_entity.etherscan.transaction.Result;
import com.group13.cryptocurrencywebapp.repository.FeeRepository;
import com.group13.cryptocurrencywebapp.repository.TradeRepository;

@Service
public class CryptoCurrencyDonationService {
    private final CryptoCurrencyDonationRepository cryptoCurrencyDonationRepository;
    private final CryptoTransferRepository cryptoTransferRepository;
    private final FeeRepository feeRepository;
    private final EtherscanService etherscanService;
    private final TradeService tradeService;
    private final TradeRepository tradeRepository;
    private final FeeRepository feeRepository;

    @Autowired
    public CryptoCurrencyDonationService(CryptoCurrencyDonationRepository cryptoCurrencyDonationRepository,
            CryptoTransferRepository cryptoTransferRepository,
            FeeRepository feeRepository,
            EtherscanService etherscanService,
            TradeService tradeService, TradeRepository tradeRepository, FeeRepository feeRepository) {
        this.cryptoCurrencyDonationRepository = cryptoCurrencyDonationRepository;
        this.tradeService = tradeService;
        this.tradeRepository = tradeRepository;
        this.feeRepository = feeRepository;
        this.cryptoTransferRepository = cryptoTransferRepository;
        this.etherscanService = etherscanService;
        this.feeRepository = feeRepository;
    }

    public CryptoCurrencyDonation createNewDonation(CryptoCurrencyDonation cryptoDonation) {

        cryptoDonation = cryptoCurrencyDonationRepository.save(cryptoDonation);

        return cryptoDonation;
    }

    public List<CryptoCurrencyDonation> getAllDonations() {
        return cryptoCurrencyDonationRepository.findAll();
    }

    public void createTrade(int donationId, float amount) {
        CryptoCurrencyDonation donation = cryptoCurrencyDonationRepository.findById(donationId).get();
        Trade newTrade = new Trade();

        newTrade.setCurrency("ETH");
        newTrade.setAmount(amount);
        newTrade.setTime(LocalDateTime.now());
        newTrade.setToCurrency("USD");

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

        Fee gasFee = new Fee(Float.parseFloat(latest.getGasUsed()), deposit, "Gas");
        gasFee = feeRepository.save(gasFee);

        deposit.setFees(Arrays.asList(new Fee[] { gasFee }));
        deposit.setFinal_amount(
                deposit.getAmount() - Float.parseFloat(latest.getGasUsed()) / (float) 1000000000000000000.0);

        // deposit = cryptoTransferRepository.save(deposit);
        CryptoTransfer deposit1 = cryptoTransferRepository.findById(deposit.getTransactionId()).get();

        // gasFee.setTransaction(deposit1);
        // gasFee = feeRepository.save(deposit.getFees().get(0));

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
