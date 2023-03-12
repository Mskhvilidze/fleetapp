package com.kindsonthegenius.fleetapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import util.WebPage;

@Controller
public class CountryController {

    @GetMapping("/countries")
    public String getCountries(){
        return WebPage.COUNTRY;
    }
}
