package com.kindsonthegenius.fleetapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import util.WebPage;

@Controller
public class VehicleController {

    @GetMapping("/" + WebPage.VEHICLE)
    public String getVehicle(){
        return WebPage.VEHICLE;
    }
}
