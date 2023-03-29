package com.group13.cryptocurrencywebapp.web_entity.exchange.binance;

import java.util.List;

public class ExchangeAccount {

    private boolean canTrade;
    private boolean canWithdraw;
    private boolean canDeposit;

    private List<Balance> balances;

    public ExchangeAccount() {

    }

    public ExchangeAccount(boolean canTrade, boolean canWithdraw, boolean canDeposit, List<Balance> balances) {
        this.canTrade = canTrade;
        this.canWithdraw = canWithdraw;
        this.canDeposit = canDeposit;
        this.balances = balances;
    }

    public boolean isCanTrade() {
        return canTrade;
    }

    public void setCanTrade(boolean canTrade) {
        this.canTrade = canTrade;
    }

    public boolean isCanWithdraw() {
        return canWithdraw;
    }

    public void setCanWithdraw(boolean canWithdraw) {
        this.canWithdraw = canWithdraw;
    }

    public boolean isCanDeposit() {
        return canDeposit;
    }

    public void setCanDeposit(boolean canDeposit) {
        this.canDeposit = canDeposit;
    }

    public List<Balance> getBalances() {
        return balances;
    }

    public void setBalances(List<Balance> balances) {
        this.balances = balances;
    }

}
