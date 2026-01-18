package com.in28minuts.rest.webservice.demo.hellowrod;

import java.util.Locale;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWordController {
    private MessageSource messageSource;

    public HelloWordController(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @GetMapping("/hello-world")
    public String helloWorld() {
        return "Hello World";
    }

    @GetMapping("/hello-world-bean")
    public HelloWordBean helloWorldBean() {
        return new HelloWordBean("Hello Word");
    }

    @GetMapping("/hello-world/path-variable/{name}")
    public HelloWordBean helloWorldPathVariable(@PathVariable String name) {
        return new HelloWordBean(String.format("Hello Word %s", name));
    }
    // 'en' - English
    // 'fr' - French
    // 'nl' - Dutch
    // 'de' - Deutsch
    @GetMapping("/hello-world-internationalized")
    public String helloWorldInternationalized() {
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage("good.morning.message", null, "Default message", locale);
    }
}
