package com.kindsonthegenius.fleetapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import util.WebPage;

@Controller
public class VehicleStatusController {

    @GetMapping("/" + WebPage.VEHICLE_STATUS)
    public String getVehicleStatus(){
        return WebPage.VEHICLE_STATUS;
    }
}
