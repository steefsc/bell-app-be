package com.sheensoft.domain;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.math.BigDecimal;
import java.util.Date;

@Entity
public class Rate {
    @EmbeddedId
    private RateId rateId;
    private Date date;
    private BigDecimal rate;

    public Rate(RateId rateId, Date date, BigDecimal rate) {
        this.rateId = rateId;
        this.date = date;
        this.rate = rate;
    }

    public Rate(){};

    public RateId getRateId() {
        return rateId;
    }

    public void setRateId(RateId rateId) {
        this.rateId = rateId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }
}
