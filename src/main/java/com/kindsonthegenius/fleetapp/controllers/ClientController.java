package com.kindsonthegenius.fleetapp.controllers;

import com.kindsonthegenius.fleetapp.model.Client;
import com.kindsonthegenius.fleetapp.model.Country;
import com.kindsonthegenius.fleetapp.model.State;
import com.kindsonthegenius.fleetapp.servieces.ClientService;
import com.kindsonthegenius.fleetapp.servieces.CountryService;
import com.kindsonthegenius.fleetapp.servieces.StateService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import util.WebPage;

import java.util.List;

import static org.springframework.web.servlet.function.RequestPredicates.accept;

@Controller
public class ClientController {
    @Autowired
    private ClientService service;
    @Autowired
    private CountryService countryService;
    @Autowired
    private StateService stateServiceService;
    private static final String REDIRECT_STATE = "redirect:/state";
    private static final Logger logger = LogManager.getLogger(ClientController.class);
    @GetMapping("/" + WebPage.CLIENT)
    public String getState(Model model) {
        List<Client> clientList = service.getStates();
        List<Country> countryList = countryService.getCountries();
        List<State> stateList  = stateServiceService.getStates();
        System.out.println("SAFas");
        logger.info("AAAa");
        logger.debug("BBBB");
        logger.warn("CCC");
        logger.fatal("DDDD");
        model.addAttribute("countries", countryList);
        model.addAttribute("clients", clientList);
        model.addAttribute("states", stateList);
        model.addAttribute("title", "Client Management");
        return WebPage.CLIENT;
    }

    @PostMapping("/clients/addNew")
    public String addNew(@ModelAttribute Client client) {
        service.save(client);
        return REDIRECT_STATE;
    }

    @RequestMapping(value = "clients/findById/")
    @ResponseBody
    public ResponseEntity<?> findById(Integer id) {
        Client client = service.findById(id).orElse(null);
        if (client != null) {
            return ResponseEntity.ok(client);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Statei not found");
        }
    }

    @RequestMapping(value = "/clients/update", method = {RequestMethod.PUT, RequestMethod.GET})
    public String update(@ModelAttribute Client client, RedirectAttributes redirectAttributes) {
        if (client.getId() == null) {
            redirectAttributes.addFlashAttribute("danger", "Nope, it doesn't work that way!");
        } else {
            int is = service.updateById(client.getId(), client.getName(), client.getDetails(),
                    client.getAddress(), client.getEmail(), client.getPhone(), client.getWebsite());
            if (is == 1) {
                redirectAttributes.addFlashAttribute("success", "Yup, Update was successful!");
            } else {
                redirectAttributes.addFlashAttribute("danger", "Nope, Invoice_Status could not be updetad!");
            }
        }
        return REDIRECT_STATE;
    }

    @RequestMapping(value = "/clients/delete/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
    public String delete(@PathVariable(name = "id") Integer id, RedirectAttributes redirectAttributes) {
        int deleteCountry = service.delete(id);
        if (deleteCountry == 1) {
            redirectAttributes.addFlashAttribute("success", "Yup, Country has been deleted!");
        } else {
            redirectAttributes.addFlashAttribute("danger", "Nope, Country could not be deleted!");
        }
        return REDIRECT_STATE;
    }

    @RequestMapping(value = "/clients/details/{id}", method = {RequestMethod.GET})
    @ResponseBody
    public ResponseEntity<?> detailsFindById(@PathVariable(name = "id") Integer id) {
        Client client = service.findById(id).orElse(null);
        if (client != null) {
            return ResponseEntity.ok(client);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("State not found");
        }
    }
}
