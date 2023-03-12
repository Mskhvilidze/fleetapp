package com.kindsonthegenius.fleetapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import util.WebPage;

@Controller
public class EmployeeTypeController {

    @GetMapping("/" + WebPage.EMPLOYEE_TYPE)
    public String getEmployeeType(){
        return WebPage.EMPLOYEE_TYPE;
    }
}
