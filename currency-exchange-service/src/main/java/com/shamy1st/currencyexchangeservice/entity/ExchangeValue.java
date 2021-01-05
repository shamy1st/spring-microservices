package com.shamy1st.currencyexchangeservice.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="exchange_value")
public class ExchangeValue {
    @Id
    private int id;
    @Column(name="from_currency")
    private String from;
    @Column(name="to_currency")
    private String to;
    @Column(name="rate")
    private float rate;
    @Column(name="port")
    private int port;

    public ExchangeValue() {

    }

    public ExchangeValue(int id, String from, String to, float value, int port) {
        this.id = id;
        this.from = from;
        this.to = to;
        this.rate = value;
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

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}