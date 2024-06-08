package com.example.mocklocationservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LocationRestController {

    @Autowired
    LocationGeneratorService service;

    @GetMapping("/location")
    public void getLocations() {
        service.generate();
    }

}
