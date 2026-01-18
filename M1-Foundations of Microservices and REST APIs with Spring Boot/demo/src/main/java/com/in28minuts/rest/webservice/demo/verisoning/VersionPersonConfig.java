package com.in28minuts.rest.webservice.demo.verisoning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersionPersonConfig {

    // URL VERSIONING
    @GetMapping("/v1/person")
    public PersonV1 personV1() {
        return new PersonV1("Bob Charlie");
    }

    @GetMapping("/v2/person")
    public PersonV2 personV2() {
        return new PersonV2(new Name("Bob", "Charlie"));
    }

    // REQUEST PARAMETER VERSIONING    
    @GetMapping(path="/person", params = "version=1")
    public PersonV1 getFirstVerisonOfPersonRequestParameter() {
        return new PersonV1("Bob Charlie");
    }
    @GetMapping(path="/person", params = "version=2")
    public PersonV2 getSecondVerisonOfPersonRequestParameter() {
        return new PersonV2(new Name("Bob", "Charlie"));
    }
    
    // HEADER VERSIONING
    @GetMapping(path="/person/header", headers = "X-API-VERSION=1")
    public PersonV1 getFirstVerisonOfPersonRequestHeader() {
        return new PersonV1("Bob Charlie");
    }
    @GetMapping(path="/person/header", headers = "X-API-VERSION=2")
    public PersonV2 getSecondVerisonOfPersonRequestHeader() {
        return new PersonV2(new Name("Bob", "Charlie"));
    }

    // MEDIA TYPE VERSIONING (a.k.a Accept Header Versioning)
    @GetMapping(path="/person/accept", produces = "application/vnd.company.app-v1+json")
    public PersonV1 getFirstVerisonOfPersonAcceptHeader() {
        return new PersonV1("Bob Charlie");
    }

    @GetMapping(path="/person/accept", produces = "application/vnd.company.app-v2+json")
    public PersonV2 getSecondVerisonOfPersonAcceptHeader() {
        return new PersonV2(new Name("Bob", "Charlie"));
    }
}
