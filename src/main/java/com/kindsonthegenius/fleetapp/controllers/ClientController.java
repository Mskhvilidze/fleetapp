package com.kindsonthegenius.fleetapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ClientController {

    static final String CLIENT = "client";

    @GetMapping("/" + CLIENT)
    public String getContact() {
        return CLIENT;
    }
}
