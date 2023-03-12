package com.kindsonthegenius.fleetapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import util.WebPage;

@Controller
public class VehicleTypeController {

    @GetMapping("/" + WebPage.VEHICLE_TYPE)
    public String getVehicleType(){
        return WebPage.VEHICLE_TYPE;
    }
}
