package com.kindsonthegenius.fleetapp.controllers;

import com.kindsonthegenius.fleetapp.model.Location;
import com.kindsonthegenius.fleetapp.model.VehicleMake;
import com.kindsonthegenius.fleetapp.model.VehicleModel;
import com.kindsonthegenius.fleetapp.servieces.VehicleMakeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import util.WebPage;

import java.util.List;
import java.util.Optional;

@Controller
public class VehicleMakeController {

    @Autowired
    private VehicleMakeService vehicleMakeService;
    private static final String REDIRECT_VEHICLE_MAKE = "redirect:/vehicleMake";

    @GetMapping("/" + WebPage.VEHICLE_MAKE)
    public String getState(Model model) {
        List<VehicleMake> vehicleMakeList = vehicleMakeService.getVehicleMake();
        model.addAttribute("locations", vehicleMakeList);
        model.addAttribute("title", "Location Management");
        return WebPage.VEHICLE_MAKE;
    }

    @PostMapping("/vehicleMake/addNew")
    public String addNew(@ModelAttribute VehicleMake vehicleMake) {
        vehicleMakeService.save(vehicleMake);
        return REDIRECT_VEHICLE_MAKE;
    }

    @RequestMapping(value = "/vehicleMake/findById/")
    @ResponseBody
    public Optional<VehicleMake> findById(int id) {
        return vehicleMakeService.findById(id);
    }

    @RequestMapping(value = "/vehicleMake/update", method = {RequestMethod.PUT, RequestMethod.GET})
    public String update(@ModelAttribute VehicleMake vehicleMake, RedirectAttributes redirectAttributes) {
        if (vehicleMake.getId() == null) {
            redirectAttributes.addFlashAttribute("danger", "Nope, it doesn't work that way!");
        } else {
            redirectAttributes.addFlashAttribute("success", "Yup, Update was successful!");
            vehicleMakeService.save(vehicleMake);
        }
        return REDIRECT_VEHICLE_MAKE;
    }

    @RequestMapping(value = "/vehicleMake/delete/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
    public String delete(@PathVariable(name = "id") Integer id, RedirectAttributes redirectAttributes) {
        int deleteCountry = vehicleMakeService.delete(id);
        System.out.println(id);
        if (deleteCountry == 1) {
            redirectAttributes.addFlashAttribute("success", "Yup, Country has been deleted!");
        } else {
            redirectAttributes.addFlashAttribute("danger", "Nope, Country could not be deleted!");
        }
        return REDIRECT_VEHICLE_MAKE;
    }

    @RequestMapping(value = "/vehicleMake/details/{id}", method = {RequestMethod.GET})
    @ResponseBody
    public Optional<VehicleMake> detailsFindById(@PathVariable(name = "id") Integer id) {
        return vehicleMakeService.findById(id);
    }
}
