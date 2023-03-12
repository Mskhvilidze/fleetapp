package com.kindsonthegenius.fleetapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import util.WebPage;

@Controller
public class JobTitleController {

    @GetMapping("/" + WebPage.JOB_TITLE)
    public String getJobTitle(){
        return WebPage.JOB_TITLE;
    }
}
