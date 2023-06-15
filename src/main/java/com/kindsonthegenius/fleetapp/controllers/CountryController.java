package com.kindsonthegenius.fleetapp.controllers;

import com.kindsonthegenius.fleetapp.model.Country;
import com.kindsonthegenius.fleetapp.servieces.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import util.WebPage;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class CountryController {

    @Autowired
    CountryService service;
    private static final String REDIRECT_COUNTRY = "redirect:/countries";

    @GetMapping("/countries")
    public String getCountries(Model model) {
        List<Country> countryList = service.getCountries();
        model.addAttribute("countries", countryList);
        model.addAttribute("title", "Country Management Page");
        return WebPage.COUNTRY;
    }

    @PostMapping("/countries/addNew")
    public String addNew(@ModelAttribute Country county) {
        service.save(county);
        return REDIRECT_COUNTRY;
    }

    @RequestMapping(value = "countries/findById/")
    @ResponseBody
    public Optional<Country> findById(int id) {
        return service.findById(id);
    }

    @RequestMapping(value = "/countries/update", method = {RequestMethod.PUT, RequestMethod.GET})
    public String update(@ModelAttribute Country country, RedirectAttributes redirectAttributes) {
        if (country.getId() == null) {
            redirectAttributes.addFlashAttribute("danger", "Nope, it doesn't work that way!");
        } else {
            redirectAttributes.addFlashAttribute("success", "Yup, Update was successful!");
            service.save(country);
        }
        return REDIRECT_COUNTRY;
    }

    @RequestMapping(value = "/countries/delete/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
    public String delete(@PathVariable(name = "id") Integer id, RedirectAttributes redirectAttributes) {
        int deleteCountry = service.delete(id);
        System.out.println(deleteCountry);
        if (deleteCountry == 1) {
            redirectAttributes.addFlashAttribute("success", "Yup, Country has been deleted!");
        } else {
            redirectAttributes.addFlashAttribute("danger", "Nope, Country could not be deleted!");
        }
        return REDIRECT_COUNTRY;
    }
}