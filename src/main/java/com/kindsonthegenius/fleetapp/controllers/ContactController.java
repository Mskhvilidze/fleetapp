package com.kindsonthegenius.fleetapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ContactController {

    static final String CONTACT  = "contact";

    @GetMapping("/" + CONTACT)
    public String getContact(){
        return CONTACT;
    }
}
