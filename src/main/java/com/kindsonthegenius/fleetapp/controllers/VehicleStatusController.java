package com.kindsonthegenius.fleetapp.controllers;

import com.kindsonthegenius.fleetapp.model.Location;
import com.kindsonthegenius.fleetapp.model.VehicleStatus;
import com.kindsonthegenius.fleetapp.servieces.VehicleStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import util.WebPage;

import java.util.List;
import java.util.Optional;

@Controller
public class VehicleStatusController {

    @Autowired
    private VehicleStatusService vehicleStatusService;
    private static final String REDIRECT_VEHICLE_STATUS = "redirect:/vehicleStatus";

    @GetMapping("/" + WebPage.VEHICLE_STATUS)
    public String getState(Model model) {
        List<VehicleStatus> vehicleStatusList = vehicleStatusService.getVehicleStatus();
        model.addAttribute("locations", vehicleStatusList);
        model.addAttribute("title", "Location Management");
        return WebPage.VEHICLE_STATUS;
    }

    @PostMapping("/vehicleStatus/addNew")
    public String addNew(@ModelAttribute VehicleStatus vehicleStatus) {
        vehicleStatusService.save(vehicleStatus);
        return REDIRECT_VEHICLE_STATUS;
    }

    @RequestMapping(value = "/vehicleStatus/findById/")
    @ResponseBody
    public Optional<VehicleStatus> findById(int id) {
        return vehicleStatusService.findById(id);
    }

    @RequestMapping(value = "/vehicleStatus/update", method = {RequestMethod.PUT, RequestMethod.GET})
    public String update(@ModelAttribute VehicleStatus vehicleStatus, RedirectAttributes redirectAttributes) {
        if (vehicleStatus.getId() == null) {
            redirectAttributes.addFlashAttribute("danger", "Nope, it doesn't work that way!");
        } else {
            redirectAttributes.addFlashAttribute("success", "Yup, Update was successful!");
            vehicleStatusService.save(vehicleStatus);
        }
        return REDIRECT_VEHICLE_STATUS;
    }

    @RequestMapping(value = "/vehicleStatus/delete/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
    public String delete(@PathVariable(name = "id") Integer id, RedirectAttributes redirectAttributes) {
        int deleteCountry = vehicleStatusService.delete(id);
        System.out.println(id);
        if (deleteCountry == 1) {
            redirectAttributes.addFlashAttribute("success", "Yup, Country has been deleted!");
        } else {
            redirectAttributes.addFlashAttribute("danger", "Nope, Country could not be deleted!");
        }
        return REDIRECT_VEHICLE_STATUS;
    }

    @RequestMapping(value = "/vehicleStatus/details/{id}", method = {RequestMethod.GET})
    @ResponseBody
    public Optional<VehicleStatus> detailsFindById(@PathVariable(name = "id") Integer id) {
        return vehicleStatusService.findById(id);
    }
}
