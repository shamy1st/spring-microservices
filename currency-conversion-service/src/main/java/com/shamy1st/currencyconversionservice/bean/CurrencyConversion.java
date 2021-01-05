package com.shamy1st.currencyconversionservice.bean;

public class CurrencyConversion {
    private int id;
    private String from;
    private String to;
    private float quantity;
    private float rate;
    private float total;
    private int port;

    public CurrencyConversion() {

    }

    public CurrencyConversion(int id, String from, String to, float quantity, float exchangeValue, int port) {
        this.id = id;
        this.from = from;
        this.to = to;
        this.quantity = quantity;
        this.rate = exchangeValue;
        this.total = quantity * exchangeValue;
        this.port = port;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public float getQuantity() {
        return quantity;
    }

    public void setQuantity(float quantity) {
        this.quantity = quantity;
        this.total = this.quantity * this.rate;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
        this.total = this.quantity * this.rate;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}