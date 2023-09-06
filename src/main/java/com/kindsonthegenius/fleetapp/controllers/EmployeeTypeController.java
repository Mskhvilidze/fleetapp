package com.kindsonthegenius.fleetapp.controllers;

import com.kindsonthegenius.fleetapp.model.EmployeeType;
import com.kindsonthegenius.fleetapp.model.Location;
import com.kindsonthegenius.fleetapp.servieces.EmployeeTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import util.WebPage;

import java.util.List;
import java.util.Optional;

@Controller
public class EmployeeTypeController {

    @Autowired
    private EmployeeTypeService employeeTypeController;
    private static final String REDIRECT_EMPLOYEE_TYPE = "redirect:/employeeType";

    @GetMapping("/" + WebPage.EMPLOYEE_TYPE)
    public String getState(Model model) {
        List<EmployeeType> employeeTypeList = employeeTypeController.getEmploystype();
        model.addAttribute("locations", employeeTypeList);
        model.addAttribute("title", "Location Management");
        return WebPage.VEHICLE_MAKE;
    }

    @PostMapping("/employeeType/addNew")
    public String addNew(@ModelAttribute EmployeeType employeeType) {
        employeeTypeController.save(employeeType);
        return REDIRECT_EMPLOYEE_TYPE;
    }

    @RequestMapping(value = "/employeeType/findById/")
    @ResponseBody
    public Optional<EmployeeType> findById(int id) {
        return employeeTypeController.findById(id);
    }

    @RequestMapping(value = "/employeeType/update", method = {RequestMethod.PUT, RequestMethod.GET})
    public String update(@ModelAttribute EmployeeType employeeType, RedirectAttributes redirectAttributes) {
        if (employeeType.getId() == null) {
            redirectAttributes.addFlashAttribute("danger", "Nope, it doesn't work that way!");
        } else {
            redirectAttributes.addFlashAttribute("success", "Yup, Update was successful!");
            employeeTypeController.save(employeeType);
        }
        return REDIRECT_EMPLOYEE_TYPE;
    }

    @RequestMapping(value = "/employeeType/delete/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
    public String delete(@PathVariable(name = "id") Integer id, RedirectAttributes redirectAttributes) {
        int deleteCountry = employeeTypeController.delete(id);
        System.out.println(id);
        if (deleteCountry == 1) {
            redirectAttributes.addFlashAttribute("success", "Yup, Country has been deleted!");
        } else {
            redirectAttributes.addFlashAttribute("danger", "Nope, Country could not be deleted!");
        }
        return REDIRECT_EMPLOYEE_TYPE;
    }

    @RequestMapping(value = "/employeeType/details/{id}", method = {RequestMethod.GET})
    @ResponseBody
    public Optional<EmployeeType> detailsFindById(@PathVariable(name = "id") Integer id) {
        return employeeTypeController.findById(id);
    }
}
