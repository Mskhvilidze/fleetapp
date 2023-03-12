package com.kindsonthegenius.fleetapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CountryController {

    static final String COUNTRY  = "country";

    @GetMapping("/countries")
    public String getCountries(){
        return COUNTRY;
    }
}
