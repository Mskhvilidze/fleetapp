package com.kindsonthegenius.fleetapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import util.WebPage;

@Controller
public class InvoiceStatusController {

    @GetMapping("/" + WebPage.INVOICE_STATUS)
    public String getInvoiceStatus(){
        return WebPage.INVOICE_STATUS;
    }
}
