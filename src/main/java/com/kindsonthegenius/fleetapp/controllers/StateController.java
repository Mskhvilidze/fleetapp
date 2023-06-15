package com.kindsonthegenius.fleetapp.controllers;

import com.kindsonthegenius.fleetapp.model.Country;
import com.kindsonthegenius.fleetapp.servieces.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import util.WebPage;

import java.util.Optional;

@Controller
public class StateController {
    //TODO Ich war letztes mal hier, Video 21, Minut 5:33
/*    @Autowired
    CountryService service;
    private static final String REDIRECT_COUNTRY = "redirect:/countries";
    @GetMapping("/" + WebPage.STATE)
    public String getState(Model model){
        model.addAttribute("title", "States Management");
        return WebPage.STATE;
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
    }*/
}
