package com.in28minutes.rest.webservice.restfulwebservce.RestfulWebService.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWordController {
    @GetMapping(path = "/")
    public String helloWord(){
        return "{\"message\":\"Hello Word Java v4\"}";
    }
    
}
