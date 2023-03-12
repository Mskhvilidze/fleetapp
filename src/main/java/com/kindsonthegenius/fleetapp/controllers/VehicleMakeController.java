package com.kindsonthegenius.fleetapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import util.WebPage;

@Controller
public class VehicleMakeController {

    @GetMapping("/" + WebPage.VEHICLE_MAKE)
    public String getVehicleMake(){
        return WebPage.VEHICLE_MAKE;
    }
}
