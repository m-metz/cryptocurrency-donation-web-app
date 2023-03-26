package com.group13.cryptocurrencywebapp.web_entity.exchange.gemini;

public class Account {

    private String name;
    private String account;
    private String type;
    private String counterparty_id;
    private long created;
    private String status;

    public Account() {

    }

    public Account(String name, String account, String type, String counterparty_id, long created, String status) {
        this.name = name;
        this.account = account;
        this.type = type;
        this.counterparty_id = counterparty_id;
        this.created = created;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCounterparty_id() {
        return counterparty_id;
    }

    public void setCounterparty_id(String counterparty_id) {
        this.counterparty_id = counterparty_id;
    }

    public long getCreated() {
        return created;
    }

    public void setCreated(long created) {
        this.created = created;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
