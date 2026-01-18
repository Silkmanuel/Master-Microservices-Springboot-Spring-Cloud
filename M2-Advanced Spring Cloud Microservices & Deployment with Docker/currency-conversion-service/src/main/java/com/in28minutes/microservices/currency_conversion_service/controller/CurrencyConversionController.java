package com.in28minutes.microservices.currency_conversion_service.controller;

import java.math.BigDecimal;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.in28minutes.microservices.currency_conversion_service.model.CurrencyConversion;
import com.in28minutes.microservices.currency_conversion_service.proxy.CurrencyConversionProxy;


@RestController
public class CurrencyConversionController {

    @Autowired
    CurrencyConversionProxy proxy;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversion calculateCurrencyConversion(
        @PathVariable("from") String from,
        @PathVariable("to") String to,
        @PathVariable("quantity") BigDecimal quantity
    ){
        HashMap<String, String> uriVariables = new HashMap<>();
        uriVariables.put("from", from);
        uriVariables.put("to", to);
        ResponseEntity<CurrencyConversion> responseEntity = restTemplate.getForEntity("http://localhost:8000/currency-exchange/from/{from}/to/{to}", CurrencyConversion.class, uriVariables);
        CurrencyConversion currencyConversion = responseEntity.getBody();
        
        return new CurrencyConversion(
            currencyConversion.getId(), 
            from, 
            to, 
            quantity, 
            currencyConversion.getConversionMultiple(), 
            quantity.multiply(quantity), 
            currencyConversion.getEnvironment() + " rest template"
        );
    }


    @GetMapping("/currency-conversion-feign/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversion calculateCurrencyConversionFeign(
        @PathVariable("from") String from,
        @PathVariable("to") String to,
        @PathVariable("quantity") BigDecimal quantity
    ){
        CurrencyConversion currency = proxy.retrieverExchangeValue(from, to);
        
        return new CurrencyConversion(
            currency.getId(), 
            from, 
            to, 
            quantity, 
            currency.getConversionMultiple(), 
            quantity.multiply(quantity), 
            currency.getEnvironment() + " feign"
        );
    }

}
