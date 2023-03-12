package com.kindsonthegenius.fleetapp;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ApplicationController {
    static final String HOME = "index";

    @GetMapping("/index")
    public String goHome(){
        return HOME;
    }

}
