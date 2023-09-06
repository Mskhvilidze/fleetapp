package com.kindsonthegenius.fleetapp.controllers;

import com.kindsonthegenius.fleetapp.model.JobTitle;
import com.kindsonthegenius.fleetapp.model.Location;
import com.kindsonthegenius.fleetapp.servieces.JobTitleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import util.WebPage;

import java.util.List;
import java.util.Optional;

@Controller
public class JobTitleController {

    @Autowired
    private JobTitleService jobTitleService;
    private static final String REDIRECT_VEHICLE_TITLE = "redirect:/jobTitle";

    @GetMapping("/" + WebPage.JOB_TITLE)
    public String getState(Model model) {
        List<JobTitle> jobTitleList = jobTitleService.getJobTitle();
        model.addAttribute("locations", jobTitleList);
        model.addAttribute("title", "Location Management");
        return WebPage.JOB_TITLE;
    }

    @PostMapping("/jobTitle/addNew")
    public String addNew(@ModelAttribute JobTitle jobTitle) {
        jobTitleService.save(jobTitle);
        return REDIRECT_VEHICLE_TITLE;
    }

    @RequestMapping(value = "/jobTitle/findById/")
    @ResponseBody
    public Optional<JobTitle> findById(int id) {
        return jobTitleService.findById(id);
    }

    @RequestMapping(value = "/jobTitle/update", method = {RequestMethod.PUT, RequestMethod.GET})
    public String update(@ModelAttribute JobTitle jobTitle, RedirectAttributes redirectAttributes) {
        if (jobTitle.getId() == null) {
            redirectAttributes.addFlashAttribute("danger", "Nope, it doesn't work that way!");
        } else {
            redirectAttributes.addFlashAttribute("success", "Yup, Update was successful!");
            jobTitleService.save(jobTitle);
        }
        return REDIRECT_VEHICLE_TITLE;
    }

    @RequestMapping(value = "/jobTitle/delete/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
    public String delete(@PathVariable(name = "id") Integer id, RedirectAttributes redirectAttributes) {
        int deleteCountry = jobTitleService.delete(id);
        System.out.println(id);
        if (deleteCountry == 1) {
            redirectAttributes.addFlashAttribute("success", "Yup, Country has been deleted!");
        } else {
            redirectAttributes.addFlashAttribute("danger", "Nope, Country could not be deleted!");
        }
        return REDIRECT_VEHICLE_TITLE;
    }

    @RequestMapping(value = "/jobTitle/details/{id}", method = {RequestMethod.GET})
    @ResponseBody
    public Optional<JobTitle> detailsFindById(@PathVariable(name = "id") Integer id) {
        return jobTitleService.findById(id);
    }
}
