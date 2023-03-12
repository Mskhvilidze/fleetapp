package com.kindsonthegenius.fleetapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import util.WebPage;

@Controller
public class EmployeeController {

    @GetMapping("/" + WebPage.EMPLOYEE)
    public String getEmployee(){
        return WebPage.EMPLOYEE;
    }
}
