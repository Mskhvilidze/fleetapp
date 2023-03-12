package com.kindsonthegenius.fleetapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import util.WebPage;

@Controller
public class VehicleMovementController {

    @GetMapping("/" + WebPage.VEHICLE_MOVEMENT)
    public String getVehicleMovement(){
        return WebPage.VEHICLE_MOVEMENT;
    }
}
