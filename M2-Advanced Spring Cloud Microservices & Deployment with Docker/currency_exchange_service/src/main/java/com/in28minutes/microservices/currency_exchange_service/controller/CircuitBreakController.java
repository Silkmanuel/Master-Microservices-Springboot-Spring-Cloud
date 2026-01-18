package com.in28minutes.microservices.currency_exchange_service.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;

@RestController
public class CircuitBreakController {
    private Logger logger = LoggerFactory.getLogger(CircuitBreakController.class);

    // IMPLEMENTING RETRY
    // @GetMapping("/sample-api")
    // @Retry(name = "sample-api", fallbackMethod = "hardcodedResponse")
    // public String sampleApi (){
    //     logger.info("Sample api call received");
    //     ResponseEntity<String> forEntity = new RestTemplate().getForEntity("http://localhost:8080/some-dummy-url", String.class);
    //    return forEntity.getBody();
    // }

    // public String hardcodedResponse(Exception e){
    //     return "Fallback-response";
    // }

    // IMPLEMENTING CIRCUIT BREAK
    // @GetMapping("/sample-api")
    // @CircuitBreaker(name = "default", fallbackMethod = "hardcodedResponse")
    // public String sampleApi (){
    //     logger.info("Sample api call received");
    //     ResponseEntity<String> forEntity = new RestTemplate().getForEntity("http://localhost:8080/some-dummy-url", String.class);
    //    return forEntity.getBody();
    // }

    // public String hardcodedResponse(Exception e){
    //     return "Fallback-response";
    // }

    // IMPLEMENTING RATE LIMITING AND BULKHEAD 
    // @GetMapping("/sample-api")
    // @RateLimiter(name = "default", fallbackMethod = "rateLimitFallback")
    // public String sampleApi (){
    //     logger.info("Sample api call received");
    //    return "Sample API";
    // }

    // public String rateLimitFallback(Exception e){
    //     logger.warn("Rate limit reached: {}", e.getMessage());
    //     return "Too many requests - try again later";
    // }


    // BULKHEAD 
    @GetMapping("/sample-api")
    @Bulkhead(name = "sample-api", type = Bulkhead.Type.SEMAPHORE, fallbackMethod = "bulkheadFallback")
    public String sampleApi() throws InterruptedException {
        Thread.sleep(3000);
        return "Sample API response";
    }
    
    public String bulkheadFallback(Exception e){
        logger.warn("Bulkhead limit reached: {}", e.getMessage());
        return "Service busy, try again later";
    }



}