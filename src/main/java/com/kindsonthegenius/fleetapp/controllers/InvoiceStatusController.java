package com.kindsonthegenius.fleetapp.controllers;

import com.kindsonthegenius.fleetapp.model.EmployeeType;
import com.kindsonthegenius.fleetapp.model.InvoiceStatus;
import com.kindsonthegenius.fleetapp.servieces.InvoiceStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import util.WebPage;

import java.util.List;
import java.util.Optional;

@Controller
public class InvoiceStatusController {

    @Autowired
    private InvoiceStatusService invoiceStatusService;
    private static final String REDIRECT_INVOICE_STATUS = "redirect:/invoiceStatus";

    @GetMapping("/" + WebPage.INVOICE_STATUS)
    public String getState(Model model) {
        List<InvoiceStatus> invoiceStatusList = invoiceStatusService.InvoiceStatus();
        model.addAttribute("locations", invoiceStatusList);
        model.addAttribute("title", "Location Management");
        return WebPage.INVOICE_STATUS;
    }

    @PostMapping("/invoiceStatus/addNew")
    public String addNew(@ModelAttribute InvoiceStatus invoiceStatus) {
        invoiceStatusService.save(invoiceStatus);
        return REDIRECT_INVOICE_STATUS;
    }

    @RequestMapping(value = "/invoiceStatus/findById/")
    @ResponseBody
    public Optional<InvoiceStatus> findById(int id) {
        return invoiceStatusService.findById(id);
    }

    @RequestMapping(value = "/invoiceStatus/update", method = {RequestMethod.PUT, RequestMethod.GET})
    public String update(@ModelAttribute InvoiceStatus invoiceStatus, RedirectAttributes redirectAttributes) {
        if (invoiceStatus.getId() == null) {
            redirectAttributes.addFlashAttribute("danger", "Nope, it doesn't work that way!");
        } else {
            redirectAttributes.addFlashAttribute("success", "Yup, Update was successful!");
            invoiceStatusService.save(invoiceStatus);
        }
        return REDIRECT_INVOICE_STATUS;
    }

    @RequestMapping(value = "/invoiceStatus/delete/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
    public String delete(@PathVariable(name = "id") Integer id, RedirectAttributes redirectAttributes) {
        int deleteCountry = invoiceStatusService.delete(id);
        System.out.println(id);
        if (deleteCountry == 1) {
            redirectAttributes.addFlashAttribute("success", "Yup, Country has been deleted!");
        } else {
            redirectAttributes.addFlashAttribute("danger", "Nope, Country could not be deleted!");
        }
        return REDIRECT_INVOICE_STATUS;
    }

    @RequestMapping(value = "/invoiceStatus/details/{id}", method = {RequestMethod.GET})
    @ResponseBody
    public Optional<InvoiceStatus> detailsFindById(@PathVariable(name = "id") Integer id) {
        return invoiceStatusService.findById(id);
    }
}
