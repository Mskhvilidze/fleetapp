package com.kindsonthegenius.fleetapp.controllers;

import com.kindsonthegenius.fleetapp.model.Country;
import com.kindsonthegenius.fleetapp.model.State;
import com.kindsonthegenius.fleetapp.servieces.CountryService;
import com.kindsonthegenius.fleetapp.servieces.StateService;
import org.apache.tomcat.util.json.JSONParser;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import util.WebPage;
import java.util.List;

@Controller
public class StateController {
    @Autowired
    private StateService service;
    @Autowired
    private CountryService countryService;
    private static final String REDIRECT_STATE = "redirect:/state";

    @GetMapping("/" + WebPage.STATE)
    public String getState(Model model) {
        List<State> stateList = service.getStates();
        List<Country> countryList = countryService.getCountries();

        model.addAttribute("countries", countryList);
        model.addAttribute("states", stateList);
        model.addAttribute("title", "States Management");
        return WebPage.STATE;
    }

    @PostMapping("/states/addNew")
    public String addNew(@ModelAttribute State state) {
        service.save(state);
        return REDIRECT_STATE;
    }

    @RequestMapping(value = "states/findById/")
    @ResponseBody
    public ResponseEntity<?> findById(Integer id) {
        State state = service.findById(id).orElse(null);
        if (state != null) {
            return ResponseEntity.ok(state);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("State not found");
        }
    }

    @RequestMapping(value = "/states/update", method = {RequestMethod.PUT, RequestMethod.GET})
    public String update(@ModelAttribute State state, RedirectAttributes redirectAttributes) {
        if (state.getId() == null) {
            redirectAttributes.addFlashAttribute("danger", "Nope, it doesn't work that way!");
        } else {
            int is = service.updateById(state.getId(), state.getName(), state.getDetails(),
                    state.getCode());
            if (is == 1) {
                redirectAttributes.addFlashAttribute("success", "Yup, Update was successful!");
            } else {
                redirectAttributes.addFlashAttribute("danger", "Nope, Invoice_Status could not be updetad!");
            }
        }
        return REDIRECT_STATE;
    }

    @RequestMapping(value = "/states/delete/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
    public String delete(@PathVariable(name = "id") Integer id, RedirectAttributes redirectAttributes) {
        int deleteCountry = service.delete(id);
        if (deleteCountry == 1) {
            redirectAttributes.addFlashAttribute("success", "Yup, State has been deleted!");
        } else {
            redirectAttributes.addFlashAttribute("danger", "Nope, State could not be deleted!");
        }
        return REDIRECT_STATE;
    }

    @RequestMapping(value = "/states/details/{id}", method = {RequestMethod.GET})
    @ResponseBody
    public ResponseEntity<?> detailsFindById(@PathVariable(name = "id") Integer id) {
        State state = service.findById(id).orElse(null);
        if (state != null) {
            return ResponseEntity.ok(state);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("State not found");
        }
    }

    @RequestMapping(value = "/states/details/100", method = {RequestMethod.POST})
    public String arrayJSON(@RequestBody String data) {
        JSONParser parser = new JSONParser(data);
        JSONObject json = new JSONObject(data);
        JSONArray array = json.getJSONArray("data1");
        for (int i = 0; i < array.length(); i++) {
            System.out.println("TEST: " + array.get(i).toString());
        }
        return REDIRECT_STATE;
    }
}