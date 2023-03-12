package com.kindsonthegenius.fleetapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import util.WebPage;

@Controller
public class UserController {

    @GetMapping("/" + WebPage.USER)
    public String getUser(){
        return WebPage.USER;
    }
}
