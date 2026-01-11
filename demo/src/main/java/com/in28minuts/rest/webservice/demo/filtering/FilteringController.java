package com.in28minuts.rest.webservice.demo.filtering;

import java.util.List;

import org.springframework.boot.autoconfigure.task.TaskExecutionProperties.Simple;
import org.springframework.boot.web.server.MimeMappings.Mapping;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
public class FilteringController {

    @GetMapping("/filtering")
    public MappingJacksonValue filtering() {
        SomeBean someBean = new SomeBean("value1", "value2", "value3");

        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(someBean);
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter
                .filterOutAllExcept("field1", "field3");
        SimpleFilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);
        mappingJacksonValue.setFilters(filters);
        return mappingJacksonValue;
    }

    @GetMapping("/filtering-list") // value 2, value 3
    public MappingJacksonValue filteringList() {
       List<SomeBean> list = List.of(new SomeBean("value1", "value2", "value3"),
                       new SomeBean("value4", "value5", "value6"));
                       //
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(list);
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter
                .filterOutAllExcept("field2", "field3");
        SimpleFilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);
        mappingJacksonValue.setFilters(filters);
        return mappingJacksonValue;
    }
}
