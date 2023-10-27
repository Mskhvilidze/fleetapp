package com.kindsonthegenius.fleetapp.controllers;

import com.kindsonthegenius.fleetapp.model.EmployeeType;
import com.kindsonthegenius.fleetapp.model.VehicleStatus;
import com.kindsonthegenius.fleetapp.servieces.VehicleStatusService;
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
public class VehicleStatusController {

    //Geblieben sind Details und Delete
    @Autowired
    private VehicleStatusService vehicleStatusService;
    private static final String REDIRECT_VEHICLE_STATUS = "redirect:/vehicleStatus";

    @GetMapping("/" + WebPage.VEHICLE_STATUS)
    public String getState(Model model) {
        List<VehicleStatus> vehicleStatusList = vehicleStatusService.getVehicleStatus();
        model.addAttribute("vehicleStatusList", vehicleStatusList);
        model.addAttribute("title", "Vehicle Status Management");
        return WebPage.VEHICLE_STATUS;
    }

    @PostMapping("/vehicleStatus/addNew")
    public String addNew(@ModelAttribute VehicleStatus vehicleStatus) {
        vehicleStatus.setLastModifiedBy(getDate());
        vehicleStatus.setLastModifiedDate(new Date());
        vehicleStatusService.save(vehicleStatus);
        return REDIRECT_VEHICLE_STATUS;
    }

    @RequestMapping(value = "/vehicleStatus/findById/")
    @ResponseBody
    public ResponseEntity<?> findById(Integer id) {
        VehicleStatus vehicleStatus = vehicleStatusService.findById(id).orElse(null);
        if (vehicleStatus != null) {
            return ResponseEntity.ok(vehicleStatus);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("VehicleStatus not found");
        }
    }

    @RequestMapping(value = "/vehicleStatus/update", method = {RequestMethod.PUT, RequestMethod.GET})
    public String update(@ModelAttribute VehicleStatus vehicleStatus, RedirectAttributes redirectAttributes) {
        if (vehicleStatus.getId() == null) {
            redirectAttributes.addFlashAttribute("danger", "Nope, it doesn't work that way!");
        } else {
            int is = vehicleStatusService.updateById(vehicleStatus.getId(), vehicleStatus.getDescription(), vehicleStatus.getDetails(),
                    getDate(), new Date());
            if (is == 1) {
                redirectAttributes.addFlashAttribute("success", "Yup, Update was successful!");
            } else {
                redirectAttributes.addFlashAttribute("danger", "Nope, Vehicle_Status could not be updetad!");
            }
        }
        return REDIRECT_VEHICLE_STATUS;
    }

    @RequestMapping(value = "/vehicleStatus/delete/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
    public String delete(@PathVariable(name = "id") Integer id, RedirectAttributes redirectAttributes) {
        int deleteCountry = vehicleStatusService.delete(id);
        System.out.println(id);
        if (deleteCountry == 1) {
            redirectAttributes.addFlashAttribute("success", "Yup, Vehicle_Status has been deleted!");
        } else {
            redirectAttributes.addFlashAttribute("danger", "Nope, Vehicle_Status could not be deleted!");
        }
        return REDIRECT_VEHICLE_STATUS;
    }

    @RequestMapping(value = "/vehicleStatus/details/{id}", method = {RequestMethod.GET})
    @ResponseBody
    public ResponseEntity<?> detailsFindById(@PathVariable(name = "id") Integer id) {
        VehicleStatus vehicleStatus = vehicleStatusService.findById(id).orElse(null);
        if (vehicleStatus != null) {
            return ResponseEntity.ok(vehicleStatus); // JobTitle wurde gefunden
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("VehicleStatus not found"); // JobTitle wurde nicht gefunden
        }
    }
    private String getDate() {
        String pattern = "HH:mm:ss.SSSZ";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(new Date());
    }
}
