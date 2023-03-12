package com.kindsonthegenius.fleetapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import util.WebPage;

@Controller
public class InvoiceController {

    @GetMapping("/" + WebPage.INVOICE)
    public String getInvoice(){
        return WebPage.INVOICE;
    }
}
