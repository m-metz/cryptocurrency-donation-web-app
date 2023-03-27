package com.group13.cryptocurrencywebapp.web_entity.exchange.gemini;

public class Order {

    private String order_id;
    private String id;
    private String symbol;
    private String avg_execution_price;
    private String side;
    private String timestamp;
    private long timestampms;
    private boolean is_live;
    private boolean is_cancelled;
    private boolean is_hidden;
    private String executed_amount;
    private String remaining_amount;
    private String price;
    private String original_amount;

    public Order() {

    }

    public Order(String order_id, String id, String symbol, String avg_execution_price, String side, String timestamp,
            long timestampms, boolean is_live, boolean is_cancelled, boolean is_hidden, String executed_amount,
            String remaining_amount, String price, String original_amount) {
        this.order_id = order_id;
        this.id = id;
        this.symbol = symbol;
        this.avg_execution_price = avg_execution_price;
        this.side = side;
        this.timestamp = timestamp;
        this.timestampms = timestampms;
        this.is_live = is_live;
        this.is_cancelled = is_cancelled;
        this.is_hidden = is_hidden;
        this.executed_amount = executed_amount;
        this.remaining_amount = remaining_amount;
        this.price = price;
        this.original_amount = original_amount;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getAvg_execution_price() {
        return avg_execution_price;
    }

    public void setAvg_execution_price(String avg_execution_price) {
        this.avg_execution_price = avg_execution_price;
    }

    public String getSide() {
        return side;
    }

    public void setSide(String side) {
        this.side = side;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public long getTimestampms() {
        return timestampms;
    }

    public void setTimestampms(long timestampms) {
        this.timestampms = timestampms;
    }

    public boolean isIs_live() {
        return is_live;
    }

    public void setIs_live(boolean is_live) {
        this.is_live = is_live;
    }

    public boolean isIs_cancelled() {
        return is_cancelled;
    }

    public void setIs_cancelled(boolean is_cancelled) {
        this.is_cancelled = is_cancelled;
    }

    public boolean isIs_hidden() {
        return is_hidden;
    }

    public void setIs_hidden(boolean is_hidden) {
        this.is_hidden = is_hidden;
    }

    public String getExecuted_amount() {
        return executed_amount;
    }

    public void setExecuted_amount(String executed_amount) {
        this.executed_amount = executed_amount;
    }

    public String getRemaining_amount() {
        return remaining_amount;
    }

    public void setRemaining_amount(String remaining_amount) {
        this.remaining_amount = remaining_amount;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getOriginal_amount() {
        return original_amount;
    }

    public void setOriginal_amount(String original_amount) {
        this.original_amount = original_amount;
    }

}
