package com.kindsonthegenius.fleetapp.controllers;

import com.kindsonthegenius.fleetapp.model.EmployeeType;
import com.kindsonthegenius.fleetapp.model.VehicleModel;
import com.kindsonthegenius.fleetapp.servieces.VehicleModelService;
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
public class VehicleModelController {

    @Autowired
    private VehicleModelService vehicleModelService;
    private static final String REDIRECT_VEHICLE_MODEL = "redirect:/vehicleModel";

    @GetMapping("/" + WebPage.VEHICLE_MODEL)
    public String getState(Model model) {
        List<VehicleModel> vehicleModelList = vehicleModelService.getVehicleModel();
        model.addAttribute("vehicleModelList", vehicleModelList);
        model.addAttribute("title", "VehicleModel Management");
        return WebPage.VEHICLE_MODEL;
    }

    @PostMapping("/vehicleModel/addNew")
    public String addNew(@ModelAttribute VehicleModel vehicleModel) {
        vehicleModel.setLastModifiedBy(getDate());
        vehicleModel.setLastModifiedDate(new Date());
        vehicleModelService.save(vehicleModel);
        return REDIRECT_VEHICLE_MODEL;
    }

    @RequestMapping(value = "/vehicleModel/findById/")
    @ResponseBody
    public ResponseEntity<?> findById(Integer id) {
        VehicleModel vehicleModel = vehicleModelService.findById(id).orElse(null);
        if (vehicleModel != null) {
            return ResponseEntity.ok(vehicleModel);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("VehicleModel not found");
        }
    }
    @RequestMapping(value = "/vehicleModel/update", method = {RequestMethod.PUT, RequestMethod.GET})
    public String update(@ModelAttribute VehicleModel vehicleModel, RedirectAttributes redirectAttributes) {
        if (vehicleModel.getId() == null) {
            redirectAttributes.addFlashAttribute("danger", "Nope, it doesn't work that way!");
        } else {
            int is = vehicleModelService.updateById(vehicleModel.getId(), vehicleModel.getDescription(), vehicleModel.getDetails(),
                    getDate(), new Date());
            if (is == 1) {
                redirectAttributes.addFlashAttribute("success", "Yup, Update was successful!");
            } else {
                redirectAttributes.addFlashAttribute("danger", "Nope, Vehicle_Model could not be updetad!");
            }
        }
        return REDIRECT_VEHICLE_MODEL;
    }

    @RequestMapping(value = "/vehicleModel/delete/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
    public String delete(@PathVariable(name = "id") Integer id, RedirectAttributes redirectAttributes) {
        int deleteCountry = vehicleModelService.delete(id);
        System.out.println(id);
        if (deleteCountry == 1) {
            redirectAttributes.addFlashAttribute("success", "Yup, Vehicle_Model has been deleted!");
        } else {
            redirectAttributes.addFlashAttribute("danger", "Nope, Vehicle_Model could not be deleted!");
        }
        return REDIRECT_VEHICLE_MODEL;
    }

    @RequestMapping(value = "/vehicleModel/details/{id}", method = {RequestMethod.GET})
    @ResponseBody
    public ResponseEntity<?> detailsFindById(@PathVariable(name = "id") Integer id) {
        VehicleModel vehicleModel = vehicleModelService.findById(id).orElse(null);
        if (vehicleModel != null) {
            return ResponseEntity.ok(vehicleModel); // JobTitle wurde gefunden
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("VehicleModel not found"); // JobTitle wurde nicht gefunden
        }
    }

    private String getDate() {
        String pattern = "HH:mm:ss.SSSZ";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(new Date());
    }
}
