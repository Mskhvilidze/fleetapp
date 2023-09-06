package com.kindsonthegenius.fleetapp.controllers;

import com.kindsonthegenius.fleetapp.model.VehicleModel;
import com.kindsonthegenius.fleetapp.servieces.VehicleModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import util.WebPage;

import java.util.List;
import java.util.Optional;

@Controller
public class VehicleModelController {

    @Autowired
    private VehicleModelService vehicleModelService;
    private static final String REDIRECT_VEHICLE_MODEL = "redirect:/vehicleModel";

    @GetMapping("/" + WebPage.VEHICLE_MODEL)
    public String getState(Model model) {
        List<VehicleModel> vehicleModelList = vehicleModelService.getVehicleModel();
        model.addAttribute("locations", vehicleModelList);
        model.addAttribute("title", "Location Management");
        return WebPage.VEHICLE_MODEL;
    }

    @PostMapping("/vehicleModel/addNew")
    public String addNew(@ModelAttribute VehicleModel vehicleModel) {
        vehicleModelService.save(vehicleModel);
        return REDIRECT_VEHICLE_MODEL;
    }

    @RequestMapping(value = "/vehicleModel/findById/")
    @ResponseBody
    public Optional<VehicleModel> findById(int id) {
        return vehicleModelService.findById(id);
    }

    @RequestMapping(value = "/vehicleModel/update", method = {RequestMethod.PUT, RequestMethod.GET})
    public String update(@ModelAttribute VehicleModel vehicleModel, RedirectAttributes redirectAttributes) {
        if (vehicleModel.getId() == null) {
            redirectAttributes.addFlashAttribute("danger", "Nope, it doesn't work that way!");
        } else {
            redirectAttributes.addFlashAttribute("success", "Yup, Update was successful!");
            vehicleModelService.save(vehicleModel);
        }
        return REDIRECT_VEHICLE_MODEL;
    }

    @RequestMapping(value = "/vehicleModel/delete/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
    public String delete(@PathVariable(name = "id") Integer id, RedirectAttributes redirectAttributes) {
        int deleteCountry = vehicleModelService.delete(id);
        System.out.println(id);
        if (deleteCountry == 1) {
            redirectAttributes.addFlashAttribute("success", "Yup, Country has been deleted!");
        } else {
            redirectAttributes.addFlashAttribute("danger", "Nope, Country could not be deleted!");
        }
        return REDIRECT_VEHICLE_MODEL;
    }

    @RequestMapping(value = "/vehicle_model/details/{id}", method = {RequestMethod.GET})
    @ResponseBody
    public Optional<VehicleModel> detailsFindById(@PathVariable(name = "id") Integer id) {
        return vehicleModelService.findById(id);
    }
}
