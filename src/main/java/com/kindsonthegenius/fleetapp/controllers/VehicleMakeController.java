package com.kindsonthegenius.fleetapp.controllers;

import com.kindsonthegenius.fleetapp.model.EmployeeType;
import com.kindsonthegenius.fleetapp.model.VehicleMake;
import com.kindsonthegenius.fleetapp.servieces.VehicleMakeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import util.WebPage;

import java.text.SimpleDateFormat;
import java.util.Date;
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
        model.addAttribute("vehicleMakeList", vehicleMakeList);
        model.addAttribute("title", "VehicleMake Management");
        return WebPage.VEHICLE_MAKE;
    }

    @PostMapping("/vehicleMake/addNew")
    public String addNew(@ModelAttribute VehicleMake vehicleMake) {
        vehicleMake.setCreatedBy(getDate());
        vehicleMake.setLastModifiedDate(new Date());
        vehicleMake.setLastModifiedBy(getDate());
        vehicleMakeService.save(vehicleMake);
        return REDIRECT_VEHICLE_MAKE;
    }

    @RequestMapping(value = "/vehicleMake/findById/")
    @ResponseBody
    public ResponseEntity<?> findById(Integer id) {
        VehicleMake vehicleMake = vehicleMakeService.findById(id).orElse(null);
        if (vehicleMake != null) {
            return ResponseEntity.ok(vehicleMake);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("VehicleMake not found");
        }
    }
    @RequestMapping(value = "/vehicleMake/update", method = {RequestMethod.PUT, RequestMethod.GET})
    public String update(@ModelAttribute VehicleMake vehicleMake, RedirectAttributes redirectAttributes) {
        if (vehicleMake.getId() == null) {
            redirectAttributes.addFlashAttribute("danger", "Nope, it doesn't work that way!");
        } else {
            int is = vehicleMakeService.updateById(vehicleMake.getId(), vehicleMake.getDescription(), vehicleMake.getDetails(),
                    getDate(), new Date());
            if (is == 1) {
                redirectAttributes.addFlashAttribute("success", "Yup, Update was successful!");
            } else {
                redirectAttributes.addFlashAttribute("danger", "Nope, Vehicle_Make could not be updetad!");
            }
        }
        return REDIRECT_VEHICLE_MAKE;
    }

    @RequestMapping(value = "/vehicleMake/delete/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
    public String delete(@PathVariable(name = "id") Integer id, RedirectAttributes redirectAttributes) {
        int deleteCountry = vehicleMakeService.delete(id);
        System.out.println(id);
        if (deleteCountry == 1) {
            redirectAttributes.addFlashAttribute("success", "Yup, Vehicle_Make has been deleted!");
        } else {
            redirectAttributes.addFlashAttribute("danger", "Nope, Vehicle_Make could not be deleted!");
        }
        return REDIRECT_VEHICLE_MAKE;
    }

    @RequestMapping(value = "/vehicleMake/details/{id}", method = {RequestMethod.GET})
    @ResponseBody
    public ResponseEntity<?> detailsFindById(@PathVariable(name = "id") Integer id) {
        VehicleMake vehicleMake = vehicleMakeService.findById(id).orElse(null);
        if (vehicleMake != null) {
            return ResponseEntity.ok(vehicleMake); // JobTitle wurde gefunden
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("VehicleMake not found"); // JobTitle wurde nicht gefunden
        }
    }
    private String getDate() {
        String pattern = "HH:mm:ss.SSSZ";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(new Date());
    }
}
