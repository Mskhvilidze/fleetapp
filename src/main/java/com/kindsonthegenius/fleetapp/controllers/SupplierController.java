package com.kindsonthegenius.fleetapp.controllers;

import com.kindsonthegenius.fleetapp.model.Country;
import com.kindsonthegenius.fleetapp.model.State;
import com.kindsonthegenius.fleetapp.model.Supplier;
import com.kindsonthegenius.fleetapp.servieces.CountryService;
import com.kindsonthegenius.fleetapp.servieces.StateService;
import com.kindsonthegenius.fleetapp.servieces.SupplierService;
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
public class SupplierController {
    @Autowired
    private SupplierService service;
    @Autowired
    private CountryService countryService;
    @Autowired
    private StateService stateServiceService;
    private static final String REDIRECT_SUPPLIER = "redirect:/supplier";
    private static final Logger LOG = LogManager.getLogger(SupplierController.class);

    @GetMapping("/" + WebPage.SUPPLIER)
    public String getState(Model model) {
        List<Supplier> suppliers = service.getSuppliers();
        List<Country> countryList = countryService.getCountries();
        List<State> stateList = stateServiceService.getStates();

        model.addAttribute("countries", countryList);
        model.addAttribute("supplierList", suppliers);
        model.addAttribute("states", stateList);
        model.addAttribute("title", "Supplier Management");
        return WebPage.SUPPLIER;
    }

    @PostMapping("/supplier/addNew")
    public ResponseEntity<?> addNew(@ModelAttribute Supplier supplier) {
        if (supplier.getName() == null || supplier.getName().isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Supplier wurde nicht erstellt! Bitte füllen Sie die erfolderischen Felder");
        }
        LOG.info("Supplier Data {}", supplier);
        service.save(supplier);
        return ResponseEntity.status(HttpStatus.CREATED).body("Supplier wurde erfolgreich erstellt");
    }

    @RequestMapping(value = "supplier/findById/")
    @ResponseBody
    public ResponseEntity<?> findById(Integer id) {
        Supplier supplier = service.findById(id).orElse(null);
        if (supplier != null) {
            return ResponseEntity.ok(supplier);
        } else {
            LOG.error("Error: {}", "Supplier not Found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nope, Supplier not found");
        }
    }

    @RequestMapping(value = "/supplier/update", method = {RequestMethod.PUT, RequestMethod.GET})
    public String updateSupplier(@ModelAttribute Supplier supplier, RedirectAttributes redirectAttributes) {
        if (supplier.getId() == null) {
            redirectAttributes.addFlashAttribute("danger", "Nope, it doesn't work that way!");
        } else {
            LOG.info("ID {}", supplier);
            int is = service.updateById(supplier.getId(), supplier.getCountryid(), supplier.getStateid(), supplier.getName(),
                    supplier.getDetails(), supplier.getWebsite(), supplier.getAddress(), supplier.getCity(), supplier.getPhone(),
                    supplier.getMobile(), supplier.getEmail());
            if (is == 1) {
                redirectAttributes.addFlashAttribute("success", "Yup, Update was successful!");
            } else {
                redirectAttributes.addFlashAttribute("danger", "Nope, Supplier could not be updetad!");
            }
        }
        return REDIRECT_SUPPLIER;
    }

    @RequestMapping(value = "/supplier/delete/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
    public String delete(@PathVariable(name = "id") Integer id, RedirectAttributes redirectAttributes) {
        int deleteSupplier = service.delete(id);
        if (deleteSupplier == 1) {
            redirectAttributes.addFlashAttribute("success", "Yup, Supplier has been deleted!");
        } else {
            redirectAttributes.addFlashAttribute("danger", "Nope, Supplier could not be deleted!");
        }
        return REDIRECT_SUPPLIER;
    }

    @RequestMapping(value = "/supplier/details/{id}", method = {RequestMethod.GET})
    @ResponseBody
    public ResponseEntity<?> detailsFindById(@PathVariable(name = "id") Integer id) {
        Supplier supplier = service.findById(id).orElse(null);
        LOG.info("Supplier Details: {}", supplier.getName());
        if (supplier != null) {
            return ResponseEntity.ok(supplier);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Supplier not found");
        }
    }
}
