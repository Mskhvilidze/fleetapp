package com.kindsonthegenius.fleetapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import util.WebPage;

@Controller
public class SupplierController {

    @GetMapping("/" + WebPage.SUPPLIER)
    public String getSupplier(){
        return WebPage.SUPPLIER;
    }
}
