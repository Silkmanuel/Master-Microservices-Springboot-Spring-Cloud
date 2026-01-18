package com.in28minutes.microservices.currency_exchange_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.in28minutes.microservices.currency_exchange_service.models.CurrencyExchange;

public interface CurrencyExchangeRepository extends JpaRepository<CurrencyExchange, Long>{
    CurrencyExchange findByFromCurrencyAndToCurrency(String fromCurrency, String toCurrency);
}
