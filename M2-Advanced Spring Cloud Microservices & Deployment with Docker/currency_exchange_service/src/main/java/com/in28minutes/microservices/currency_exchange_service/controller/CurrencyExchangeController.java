package com.in28minutes.microservices.currency_exchange_service.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.in28minutes.microservices.currency_exchange_service.models.CurrencyExchange;
import com.in28minutes.microservices.currency_exchange_service.repository.CurrencyExchangeRepository;


@RestController
public class CurrencyExchangeController {
    private Logger logger = LoggerFactory.getLogger(CurrencyExchangeController.class);
    @Autowired
    private CurrencyExchangeRepository repository;
    @Autowired
    private Environment environment;
    
    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public CurrencyExchange retrieveExchangeValue(
        @PathVariable String from,
        @PathVariable String to
    ){
    System.out.println("=== Handled by port " + environment.getProperty("local.server.port"));
        logger.info("== retrieve Exchange Value called {} to {} ", from, to);
        CurrencyExchange currencyExchange = repository.findByFromCurrencyAndToCurrency(from, to);
        if(currencyExchange== null){
            throw new RuntimeException("unable to find fdata for " + from + " to " + to);
          
        }
        String port = environment.getProperty("local.server.port");
        currencyExchange.setEnvironment(port);
        return currencyExchange;
    }
}