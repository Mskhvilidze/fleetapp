package com.kindsonthegenius.fleetapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import util.WebPage;

@Controller
public class VehicleMaintenanceController {

    @GetMapping("/" + WebPage.VEHICLE_MAINTENANCE)
    public String getVehicleMaintenance(){
        return WebPage.VEHICLE_MAINTENANCE;
    }
}