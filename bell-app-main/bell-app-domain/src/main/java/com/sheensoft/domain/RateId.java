package com.sheensoft.domain;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class RateId implements Serializable {
    private String currencyFrom;
    private String currencyTo;

    RateId(){}

    public RateId(String currencyFrom, String currencyTo) {
        this.currencyFrom = currencyFrom;
        this.currencyTo = currencyTo;
    }

    public String getCurrencyFrom() {
        return currencyFrom;
    }

    public void setCurrencyFrom(String currencyFrom) {
        this.currencyFrom = currencyFrom;
    }

    public String getCurrencyTo() {
        return currencyTo;
    }

    public void setCurrencyTo(String currencyTo) {
        this.currencyTo = currencyTo;
    }
}
