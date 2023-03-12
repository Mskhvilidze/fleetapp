package com.kindsonthegenius.fleetapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import util.WebPage;

@Controller
public class VehicleModelController {

    @GetMapping("/" + WebPage.VEHICLE_MODEL)
    public String getVehicleModel(){
        return WebPage.VEHICLE_MODEL;
    }
}
