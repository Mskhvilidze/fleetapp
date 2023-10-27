package com.kindsonthegenius.fleetapp.controllers;

import com.kindsonthegenius.fleetapp.model.EmployeeType;
import com.kindsonthegenius.fleetapp.model.VehicleType;
import com.kindsonthegenius.fleetapp.servieces.VehicleTypeService;
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
public class VehicleTypeController {

    @Autowired
    private VehicleTypeService vehicleTypeService;
    private static final String REDIRECT_VEHICLE_TYPE = "redirect:/vehicleType";

    @GetMapping("/" + WebPage.VEHICLE_TYPE)
    public String getState(Model model) {
        List<VehicleType> vehicleTypeList = vehicleTypeService.getVehicleType();
        model.addAttribute("vehicleTypeList", vehicleTypeList);
        model.addAttribute("title", "VehicleType Management");
        return WebPage.VEHICLE_TYPE;
    }

    @PostMapping("/vehicleType/addNew")
    public String addNew(@ModelAttribute VehicleType vehicleType) {
        vehicleType.setLastModifiedBy(getDate());
        vehicleType.setLastModifiedDate(new Date());
        vehicleTypeService.save(vehicleType);
        return REDIRECT_VEHICLE_TYPE;
    }

    @RequestMapping(value = "/vehicleType/findById/")
    @ResponseBody
    public ResponseEntity<?> findById(Integer id) {
        VehicleType vehicleType = vehicleTypeService.findById(id).orElse(null);
        if (vehicleType != null) {
            return ResponseEntity.ok(vehicleType);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("VehicleType not found");
        }
    }
    @RequestMapping(value = "/vehicleType/update", method = {RequestMethod.PUT, RequestMethod.GET})
    public String update(@ModelAttribute VehicleType vehicleType, RedirectAttributes redirectAttributes) {
        if (vehicleType.getId() == null) {
            redirectAttributes.addFlashAttribute("danger", "Nope, it doesn't work that way!");
        } else {
            int is = vehicleTypeService.updateById(vehicleType.getId(), vehicleType.getDescription(), vehicleType.getDetails(),
                    getDate(), new Date());
            if (is == 1) {
                redirectAttributes.addFlashAttribute("success", "Yup, Update was successful!");
            } else {
                redirectAttributes.addFlashAttribute("danger", "Nope, Vehicle_Type could not be updetad!");
            }
        }
        return REDIRECT_VEHICLE_TYPE;
    }

    @RequestMapping(value = "/vehicleType/delete/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
    public String delete(@PathVariable(name = "id") Integer id, RedirectAttributes redirectAttributes) {
        int deleteCountry = vehicleTypeService.delete(id);
        if (deleteCountry == 1) {
            redirectAttributes.addFlashAttribute("success", "Yup, Vehicle_Type has been deleted!");
        } else {
            redirectAttributes.addFlashAttribute("danger", "Nope, Vehicle_Type could not be deleted!");
        }
        return REDIRECT_VEHICLE_TYPE;
    }

    @RequestMapping(value = "/vehicleType/details/{id}", method = {RequestMethod.GET})
    @ResponseBody
    public ResponseEntity<?> detailsFindById(@PathVariable(name = "id") Integer id) {
        VehicleType vehicleType = vehicleTypeService.findById(id).orElse(null);
        if (vehicleType != null) {
            return ResponseEntity.ok(vehicleType); // JobTitle wurde gefunden
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("VehicleType not found"); // JobTitle wurde nicht gefunden
        }
    }
    private String getDate() {
        String pattern = "HH:mm:ss.SSSZ";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(new Date());
    }
}
