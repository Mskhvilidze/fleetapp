package com.kindsonthegenius.fleetapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import util.WebPage;

@Controller
public class StateController {

    @GetMapping("/" + WebPage.STATE)
    public String getState(){
        return WebPage.STATE;
    }

}
