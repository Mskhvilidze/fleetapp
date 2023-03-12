package com.kindsonthegenius.fleetapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import util.WebPage;

@Controller
public class VehicleHireController {

    @GetMapping("/" + WebPage.VEHICLE_HIRE)
    public String vehicleHire(){
        return WebPage.VEHICLE_HIRE;
    }
}
