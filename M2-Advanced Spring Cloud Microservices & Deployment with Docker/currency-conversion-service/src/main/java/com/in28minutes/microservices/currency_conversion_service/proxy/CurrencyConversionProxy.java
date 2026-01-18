package com.in28minutes.microservices.currency_conversion_service.proxy;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.in28minutes.microservices.currency_conversion_service.model.CurrencyConversion;

// @FeignClient(name = "currency-exchange", url = "localhost:8000")
@FeignClient(name = "CURRENCY-EXCHANGE-SERVICE") // same name saved on the naming server
public interface CurrencyConversionProxy {
    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public CurrencyConversion retrieverExchangeValue(
        @PathVariable("from") String from,
        @PathVariable("to") String to
    );
    
}
