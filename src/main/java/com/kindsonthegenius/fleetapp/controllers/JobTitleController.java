package com.kindsonthegenius.fleetapp.controllers;

import com.kindsonthegenius.fleetapp.model.JobTitle;
import com.kindsonthegenius.fleetapp.servieces.JobTitleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import util.WebPage;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class JobTitleController {

    @Autowired
    private JobTitleService jobTitleService;
    private static final String REDIRECT_VEHICLE_TITLE = "redirect:/jobTitle";

    @GetMapping("/" + WebPage.JOB_TITLE)
    public String getState(Model model) {
        List<JobTitle> jobTitleList = jobTitleService.getJobTitle();
        model.addAttribute("jobTitleList", jobTitleList);
        model.addAttribute("title", "Job_Title Management");
        return WebPage.JOB_TITLE;
    }

    @PostMapping("/jobTitle/addNew")
    public String addNew(@ModelAttribute JobTitle jobTitle) {
        jobTitle.setLastModifiedBy(getDate());
        jobTitle.setLastModifiedDate(new Date());
        jobTitleService.save(jobTitle);
        return REDIRECT_VEHICLE_TITLE;
    }

    @RequestMapping(value = "/jobTitle/findById/")
    @ResponseBody
    public ResponseEntity<?> findById(Integer id) {
        JobTitle jobTitle = jobTitleService.findById(id).orElse(null);
        if (jobTitle != null) {
            return ResponseEntity.ok(jobTitle);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Job Title not found");
        }
    }

    @RequestMapping(value = "/jobTitle/update", method = {RequestMethod.PUT, RequestMethod.GET})
    public String update(@ModelAttribute JobTitle jobTitle, RedirectAttributes redirectAttributes) {
        if (jobTitle.getId() == null) {
            redirectAttributes.addFlashAttribute("danger", "Nope, it doesn't work that way!");
        } else {
            int is = jobTitleService.updateById(jobTitle.getId(), jobTitle.getDescription(), jobTitle.getDetails(),
                    getDate(), new Date());
            if (is == 1) {
                redirectAttributes.addFlashAttribute("success", "Yup, Update was successful!");
            } else {
                redirectAttributes.addFlashAttribute("danger", "Nope, JobTitle could not be updetad!");
            }
        }
        return REDIRECT_VEHICLE_TITLE;
    }

    @RequestMapping(value = "/jobTitle/delete/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
    public String delete(@PathVariable(name = "id") Integer id, RedirectAttributes redirectAttributes) {
        int deleteCountry = jobTitleService.delete(id);
        if (deleteCountry == 1) {
            redirectAttributes.addFlashAttribute("success", "Yup, JobTitle has been deleted!");
        } else {
            redirectAttributes.addFlashAttribute("danger", "Nope, JobTitle could not be deleted!");
        }
        return REDIRECT_VEHICLE_TITLE;
    }

    @RequestMapping(value = "/jobTitle/details/{id}", method = {RequestMethod.GET})
    @ResponseBody
    public ResponseEntity<?> detailsFindById(@PathVariable(name = "id") Integer id) {
        JobTitle jobTitle = jobTitleService.findById(id).orElse(null);
        if (jobTitle != null) {
            return ResponseEntity.ok(jobTitle); // JobTitle wurde gefunden
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("JobTitle not found"); // JobTitle wurde nicht gefunden
        }
    }

    private String getDate() {
        String pattern = "HH:mm:ss.SSSZ";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(new Date());
    }
}
