package com.kindsonthegenius.fleetapp.controllers;

import com.kindsonthegenius.fleetapp.model.Location;
import com.kindsonthegenius.fleetapp.model.VehicleType;
import com.kindsonthegenius.fleetapp.servieces.VehicleTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import util.WebPage;

import java.util.List;
import java.util.Optional;

@Controller
public class VehicleTypeController {

    @Autowired
    private VehicleTypeService vehicleTypeService;
    private static final String REDIRECT_VEHICLE_TYPE = "redirect:/vehicleType";

    @GetMapping("/" + WebPage.VEHICLE_TYPE)
    public String getState(Model model) {
        List<VehicleType> vehicleTypeList = vehicleTypeService.getVehicleType();
        model.addAttribute("locations", vehicleTypeList);
        model.addAttribute("title", "Location Management");
        return WebPage.VEHICLE_TYPE;
    }

    @PostMapping("/vehicleType/addNew")
    public String addNew(@ModelAttribute VehicleType vehicleType) {
        vehicleTypeService.save(vehicleType);
        return REDIRECT_VEHICLE_TYPE;
    }

    @RequestMapping(value = "/vehicleType/findById/")
    @ResponseBody
    public Optional<VehicleType> findById(int id) {
        return vehicleTypeService.findById(id);
    }

    @RequestMapping(value = "/vehicleType/update", method = {RequestMethod.PUT, RequestMethod.GET})
    public String update(@ModelAttribute VehicleType vehicleType, RedirectAttributes redirectAttributes) {
        if (vehicleType.getId() == null) {
            redirectAttributes.addFlashAttribute("danger", "Nope, it doesn't work that way!");
        } else {
            redirectAttributes.addFlashAttribute("success", "Yup, Update was successful!");
            vehicleTypeService.save(vehicleType);
        }
        return REDIRECT_VEHICLE_TYPE;
    }

    @RequestMapping(value = "/vehicleType/delete/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
    public String delete(@PathVariable(name = "id") Integer id, RedirectAttributes redirectAttributes) {
        int deleteCountry = vehicleTypeService.delete(id);
        System.out.println(id);
        if (deleteCountry == 1) {
            redirectAttributes.addFlashAttribute("success", "Yup, Country has been deleted!");
        } else {
            redirectAttributes.addFlashAttribute("danger", "Nope, Country could not be deleted!");
        }
        return REDIRECT_VEHICLE_TYPE;
    }

    @RequestMapping(value = "/vehicleType/details/{id}", method = {RequestMethod.GET})
    @ResponseBody
    public Optional<VehicleType> detailsFindById(@PathVariable(name = "id") Integer id) {
        return vehicleTypeService.findById(id);
    }
}
