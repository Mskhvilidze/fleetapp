package com.kindsonthegenius.fleetapp.controllers;

import com.kindsonthegenius.fleetapp.model.EmployeeType;
import com.kindsonthegenius.fleetapp.model.JobTitle;
import com.kindsonthegenius.fleetapp.servieces.EmployeeTypeService;
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
public class EmployeeTypeController {

    @Autowired
    private EmployeeTypeService employeeTypeService;
    private static final String REDIRECT_EMPLOYEE_TYPE = "redirect:/employeeType";

    @GetMapping("/" + WebPage.EMPLOYEE_TYPE)
    public String getState(Model model) {
        List<EmployeeType> employeeTypeList = employeeTypeService.getEmploystype();
        model.addAttribute("employeeTypeList", employeeTypeList);
        model.addAttribute("title", "Employee Type Management");
        return WebPage.EMPLOYEE_TYPE;
    }

    @PostMapping("/employeeType/addNew")
    public String addNew(@ModelAttribute EmployeeType employeeType) {
        employeeType.setLastModifiedBy(getDate());
        employeeType.setLastModifiedDate(new Date());
        employeeTypeService.save(employeeType);
        return REDIRECT_EMPLOYEE_TYPE;
    }

    @RequestMapping(value = "/employeeType/findById/")
    @ResponseBody
    public ResponseEntity<?> findById(Integer id) {
        EmployeeType employeeType = employeeTypeService.findById(id).orElse(null);
        if (employeeType != null) {
            return ResponseEntity.ok(employeeType);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("EmployeeType not found");
        }
    }
    @RequestMapping(value = "/employeeType/update", method = {RequestMethod.PUT, RequestMethod.GET})
    public String update(@ModelAttribute EmployeeType employeeType, RedirectAttributes redirectAttributes) {
        if (employeeType.getId() == null) {
            redirectAttributes.addFlashAttribute("danger", "Nope, it doesn't work that way!");
        } else {
            int is = employeeTypeService.updateById(employeeType.getId(), employeeType.getDescription(), employeeType.getDetails(),
                    getDate(), new Date());
            if (is == 1) {
                redirectAttributes.addFlashAttribute("success", "Yup, Update was successful!");
            } else {
                redirectAttributes.addFlashAttribute("danger", "Nope, Employee_Type could not be updetad!");
            }
        }
        return REDIRECT_EMPLOYEE_TYPE;
    }

    @RequestMapping(value = "/employeeType/delete/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
    public String delete(@PathVariable(name = "id") Integer id, RedirectAttributes redirectAttributes) {
        int deleteCountry = employeeTypeService.delete(id);
        System.out.println(id);
        if (deleteCountry == 1) {
            redirectAttributes.addFlashAttribute("success", "Yup, Employee_Type has been deleted!");
        } else {
            redirectAttributes.addFlashAttribute("danger", "Nope, Employee_Type could not be deleted!");
        }
        return REDIRECT_EMPLOYEE_TYPE;
    }

    @RequestMapping(value = "/employeeType/details/{id}", method = {RequestMethod.GET})
    @ResponseBody
    public ResponseEntity<?> detailsFindById(@PathVariable(name = "id") Integer id) {
        EmployeeType employeeType = employeeTypeService.findById(id).orElse(null);
        if (employeeType != null) {
            return ResponseEntity.ok(employeeType); // JobTitle wurde gefunden
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("EmployeeType not found"); // JobTitle wurde nicht gefunden
        }
    }

    private String getDate() {
        String pattern = "HH:mm:ss.SSSZ";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(new Date());
    }
}
