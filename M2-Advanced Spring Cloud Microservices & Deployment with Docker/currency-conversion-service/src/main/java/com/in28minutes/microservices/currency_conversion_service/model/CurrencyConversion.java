package com.in28minutes.microservices.currency_conversion_service.model;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class CurrencyConversion {
    @Id
    private Long id;
    private String currencyFrom;
    private String currencyTo;
    private BigDecimal quantity;
    private BigDecimal conversionMultiple;
    private BigDecimal titalCalculatedAmount;
    private String environment;
    public CurrencyConversion(Long id, String currencyFrom, String currencyTo, BigDecimal quantity,
            BigDecimal conversionMultiple, BigDecimal titalCalculatedAmount, String environment) {
        this.id = id;
        this.currencyFrom = currencyFrom;
        this.currencyTo = currencyTo;
        this.quantity = quantity;
        this.conversionMultiple = conversionMultiple;
        this.titalCalculatedAmount = titalCalculatedAmount;
        this.environment = environment;
    }
    public CurrencyConversion() {
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
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
    public BigDecimal getQuantity() {
        return quantity;
    }
    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }
    public BigDecimal getConversionMultiple() {
        return conversionMultiple;
    }
    public void setConversionMultiple(BigDecimal conversionMultiple) {
        this.conversionMultiple = conversionMultiple;
    }
    public BigDecimal getTitalCalculatedAmount() {
        return titalCalculatedAmount;
    }
    public void setTitalCalculatedAmount(BigDecimal titalCalculatedAmount) {
        this.titalCalculatedAmount = titalCalculatedAmount;
    }
    public String getEnvironment() {
        return environment;
    }
    public void setEnvironment(String environment) {
        this.environment = environment;
    }
    
    
}
