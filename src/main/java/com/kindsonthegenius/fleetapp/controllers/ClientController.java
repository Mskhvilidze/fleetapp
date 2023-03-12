package com.kindsonthegenius.fleetapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import util.WebPage;

@Controller
public class ClientController {
    @GetMapping("/" + WebPage.CLIENT)
    public String getContact() {
        return WebPage.CLIENT;
    }
}
