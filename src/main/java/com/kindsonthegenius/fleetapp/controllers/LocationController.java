package com.kindsonthegenius.fleetapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import util.WebPage;

@Controller
public class LocationController {

    @GetMapping("/" + WebPage.LOCATION)
    public String getLocation(){
        return WebPage.LOCATION;
    }
}
