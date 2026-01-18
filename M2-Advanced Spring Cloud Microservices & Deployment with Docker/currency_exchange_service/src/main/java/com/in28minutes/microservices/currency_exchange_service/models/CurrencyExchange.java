package com.in28minutes.microservices.currency_exchange_service.models;

import java.math.BigDecimal;
import jakarta.persistence.*;

@Entity
public class CurrencyExchange {
    @Id
    private Long id;
    @Column(name = "currency_from")
    private String fromCurrency;
    @Column(name = "currency_to")
    private String toCurrency;
    @Column(name = "conversion_multiple")
    private BigDecimal conversionMultiple;
    private String environment;

    
    public CurrencyExchange(Long id, String fromCurrency, String toCurrency, BigDecimal conversionMultiple, String environment) {
        this.id = id;
        this.fromCurrency = fromCurrency;
        this.toCurrency = toCurrency;
        this.conversionMultiple = conversionMultiple;
    }
    public CurrencyExchange() {}
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getFrom() {
        return fromCurrency;
    }
    public void setFrom(String fromCurrency) {
        this.fromCurrency = fromCurrency;
    }
    public String getTo() {
        return toCurrency;
    }
    public void setTo(String toCurrency) {
        this.toCurrency = toCurrency;
    }
    public BigDecimal getConversionMultiple() {
        return conversionMultiple;
    }
    public void setConversionMultiple(BigDecimal conversionMultiple) {
        this.conversionMultiple = conversionMultiple;
    }
    public String getEnvironment() {
        return environment;
    }
    public void setEnvironment(String environment) {
        this.environment = environment;
    }
    
}

