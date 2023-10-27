package com.kindsonthegenius.fleetapp.controllers;

import com.kindsonthegenius.fleetapp.model.Country;
import com.kindsonthegenius.fleetapp.model.Location;
import com.kindsonthegenius.fleetapp.model.State;
import com.kindsonthegenius.fleetapp.servieces.CountryService;
import com.kindsonthegenius.fleetapp.servieces.LocationService;
import com.kindsonthegenius.fleetapp.servieces.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import util.WebPage;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
public class LocationController {
    @Autowired
    private StateService service;
    @Autowired
    private CountryService countryService;
    @Autowired
    private LocationService locationService;
    private static final String REDIRECT_LOCATION = "redirect:/location";

    @GetMapping("/" + WebPage.LOCATION)
    public String getState(Model model) {
        List<State> stateList = service.getStates();
        List<Country> countryList = countryService.getCountries();
        List<Location> locationList = locationService.getLocations();

        model.addAttribute("countries", countryList);
        model.addAttribute("states", stateList);
        model.addAttribute("locations", locationList);
        model.addAttribute("title", "Location Management");
        return WebPage.LOCATION;
    }

    @PostMapping("/location/addNew")
    public String addNew(@ModelAttribute Location location) {
        locationService.save(location);
        return REDIRECT_LOCATION;
    }

    @RequestMapping(value = "location/findById/")
    @ResponseBody
    public Optional<Location> findById(int id) {
        return locationService.findById(id);
    }

    @RequestMapping(value = "/location/update", method = {RequestMethod.PUT, RequestMethod.GET})
    public String update(@ModelAttribute Location location, RedirectAttributes redirectAttributes) {
        if (location.getId() == null) {
            redirectAttributes.addFlashAttribute("danger", "Nope, it doesn't work that way!");
        } else {
            int is = locationService.updateById(location.getId(), location.getAddress(), location.getCity(),
                    location.getCountry().getId(), location.getDescription(), location.getDetails(), location.getState().getId());
            if (is == 1) {
                redirectAttributes.addFlashAttribute("success", "Yup, Update was successful!");
            } else {
                redirectAttributes.addFlashAttribute("danger", "Nope, Location could not be updetad!");
            }
        }
        return REDIRECT_LOCATION;
    }

    @RequestMapping(value = "/location/delete/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
    public String delete(@PathVariable(name = "id") Integer id, RedirectAttributes redirectAttributes) {
        int deleteCountry = locationService.delete(id);
        System.out.println(id);
        if (deleteCountry == 1) {
            redirectAttributes.addFlashAttribute("success", "Yup, Country has been deleted!");
        } else {
            redirectAttributes.addFlashAttribute("danger", "Nope, Country could not be deleted!");
        }
        return REDIRECT_LOCATION;
    }

    @RequestMapping(value = "/location/details/{id}", method = {RequestMethod.GET})
    @ResponseBody
    public Optional<Location> detailsFindById(@PathVariable(name = "id") Integer id) {
        return locationService.findById(id);
    }
}