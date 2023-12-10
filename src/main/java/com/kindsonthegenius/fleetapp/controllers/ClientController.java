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

@Controller
public class ClientController {
    @Autowired
    private ClientService service;
    @Autowired
    private CountryService countryService;
    @Autowired
    private StateService stateServiceService;
    private static final String REDIRECT_CLIENT = "redirect:/client";
    private static final Logger LOG = LogManager.getLogger(ClientController.class);

    @GetMapping("/" + WebPage.CLIENT)
    public String getState(Model model) {
        List<Client> clientList = service.getClients();
        List<Country> countryList = countryService.getCountries();
        List<State> stateList = stateServiceService.getStates();

        model.addAttribute("countries", countryList);
        model.addAttribute("clientList", clientList);
        model.addAttribute("states", stateList);
        model.addAttribute("title", "Client Management");
        return WebPage.CLIENT;
    }

    @PostMapping("/client/addNew")
    public ResponseEntity<?> addNew(@ModelAttribute Client client) {
        if (client.getName() == null || client.getName().isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Client wurde nicht erstellt! Bitte füllen Sie die erfolderischen Felder");
        }
        LOG.info("Client Details {} und Name {}", client.getDetails(), client.getName());
        service.save(client);
        return ResponseEntity.status(HttpStatus.CREATED).body("Client wurde erfolgreich erstellt");
    }

    @RequestMapping(value = "clients/findById/")
    @ResponseBody
    public ResponseEntity<?> findById(Integer id) {
        Client client = service.findById(id).orElse(null);
        if (client != null) {
            return ResponseEntity.ok(client);
        } else {
            LOG.error("Error: {}", "Client not Found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nope, Client not found");
        }
    }

    @RequestMapping(value = "/clients/update", method = {RequestMethod.PUT, RequestMethod.GET})
    public String update(@ModelAttribute Client client, RedirectAttributes redirectAttributes) {
        if (client.getId() == null) {
            redirectAttributes.addFlashAttribute("danger", "Nope, it doesn't work that way!");
        } else {
            LOG.info("ID {}, ID2 {}", client.getStateid());
            int is = service.updateById(client.getId(), client.getCountryid(), client.getStateid(), client.getName(),
                    client.getDetails(), client.getWebsite(), client.getAddress(), client.getCity(), client.getPhone(),
                    client.getMobile(), client.getEmail());
            if (is == 1) {
                redirectAttributes.addFlashAttribute("success", "Yup, Update was successful!");
            } else {
                redirectAttributes.addFlashAttribute("danger", "Nope, Client could not be updetad!");
            }
        }
        return REDIRECT_CLIENT;
    }

    @RequestMapping(value = "/clients/delete/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
    public String delete(@PathVariable(name = "id") Integer id, RedirectAttributes redirectAttributes) {
        int deleteCountry = service.delete(id);
        if (deleteCountry == 1) {
            redirectAttributes.addFlashAttribute("success", "Yup, Client has been deleted!");
        } else {
            redirectAttributes.addFlashAttribute("danger", "Nope, Client could not be deleted!");
        }
        return REDIRECT_CLIENT;
    }

    @RequestMapping(value = "/clients/details/{id}", method = {RequestMethod.GET})
    @ResponseBody
    public ResponseEntity<?> detailsFindById(@PathVariable(name = "id") Integer id) {
        Client client = service.findById(id).orElse(null);
        LOG.info("Client Details: {}" + client.getName());
        if (client != null) {
            return ResponseEntity.ok(client);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Client not found");
        }
    }
}
