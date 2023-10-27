package com.kindsonthegenius.fleetapp.controllers;

import com.kindsonthegenius.fleetapp.model.Country;
import com.kindsonthegenius.fleetapp.model.State;
import com.kindsonthegenius.fleetapp.servieces.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<?> findById(Integer id) {
        Country country = service.findById(id).orElse(null);
        if (country != null) {
            return ResponseEntity.ok(country);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Statei not found");
        }
    }

    @RequestMapping(value = "/countries/update", method = {RequestMethod.PUT, RequestMethod.GET})
    public String update(@ModelAttribute Country country, RedirectAttributes redirectAttributes) {
        if (country.getId() == null) {
            redirectAttributes.addFlashAttribute("danger", "Nope, it doesn't work that way!");
        } else {
            int is = service.updateById(country.getId(), country.getCapital(), country.getCode(),
                    country.getContinent(), country.getDescription(), country.getNationality());
            if (is == 1) {
                redirectAttributes.addFlashAttribute("success", "Yup, Update was successful!");
            } else {
                redirectAttributes.addFlashAttribute("danger", "Nope, Invoice_Status could not be updetad!");
            }
        }
        return REDIRECT_COUNTRY;
    }

    @RequestMapping(value = "/countries/delete/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
    public String delete(@PathVariable(name = "id") Integer id, RedirectAttributes redirectAttributes) {
        int deleteCountry = service.delete(id);
        if (deleteCountry == 1) {
            redirectAttributes.addFlashAttribute("success", "Yup, Country has been deleted!");
        } else {
            redirectAttributes.addFlashAttribute("danger", "Nope, Country could not be deleted!");
        }
        return REDIRECT_COUNTRY;
    }

    @RequestMapping(value = "/countries/details/{id}", method = {RequestMethod.GET})
    @ResponseBody
    public ResponseEntity<?> detailsFindById(@PathVariable(name = "id") Integer id) {
        Country country = service.findById(id).orElse(null);
        System.out.println("Country: " + country.getCode());
        if (country != null) {
            return ResponseEntity.ok(country);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Country not found");
        }
    }
}