package com.kindsonthegenius.fleetapp.controllers;

import com.kindsonthegenius.fleetapp.model.Client;
import com.kindsonthegenius.fleetapp.model.Invoice;
import com.kindsonthegenius.fleetapp.model.InvoiceStatus;
import com.kindsonthegenius.fleetapp.servieces.ClientService;
import com.kindsonthegenius.fleetapp.servieces.InvoiceService;
import com.kindsonthegenius.fleetapp.servieces.InvoiceStatusService;
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

import java.time.Instant;
import java.util.List;

@Controller
public class InvoiceController {
    @Autowired
    private InvoiceService service;
    @Autowired
    private ClientService clientService;
    @Autowired
    private InvoiceStatusService invoiceStatusService;
    private static final String REDIRECT_INVOICE = "redirect:/invoice";
    private static final Logger LOG = LogManager.getLogger(InvoiceController.class);

    @GetMapping("/" + WebPage.INVOICE)
    public String getInvoice(Model model) {
        List<Invoice> invoiceList = service.getInvoices();
        List<Client> clientList = clientService.getClients();
        List<InvoiceStatus> invoiceStatuses = invoiceStatusService.InvoiceStatus();

        model.addAttribute("invoices", invoiceList);
        model.addAttribute("clients", clientList);
        model.addAttribute("invoiceStatuses", invoiceStatuses);
        return WebPage.INVOICE;
    }
    //https://www.youtube.com/watch?v=88l0kVMsIXw&list=PL9l1zUfnZkZkmDvzHCoLlc_nHBc7ZXiRO&index=30

    @PostMapping("/invoice/addNew")
    public ResponseEntity<?> addNew(@ModelAttribute Invoice invoice) {
        if ((invoice.getInvoiceDate() != null && invoice.getInvoiceDate().toInstant().equals(Instant.EPOCH)) ||
                invoice.getRemarks().isEmpty()) {
            LOG.error("Invoicefelder müssen gefüllt werden {}", invoice);
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Invoice wurde nicht erstellt! Bitte füllen Sie die erfolderischen Felder");
        }
        service.save(invoice);
        return ResponseEntity.status(HttpStatus.CREATED).body("Invoice wurde erfolgreich erstellt");
    }

    @RequestMapping(value = "invoice/findById/")
    @ResponseBody
    public ResponseEntity<?> findById(Integer id) {
        Invoice invoice = service.findById(id).orElse(null);
        if (invoice != null) {
            return ResponseEntity.ok(invoice);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invoice not found");
        }
    }

    @RequestMapping(value = "/invoice/update", method = {RequestMethod.PUT, RequestMethod.GET})
    public String update(@ModelAttribute Invoice invoice, RedirectAttributes redirectAttributes) {
        if (invoice.getId() == null) {
            redirectAttributes.addFlashAttribute("danger", "Nope, it doesn't work that way!");
        } else {
            int is = service.updateById(invoice.getId(), invoice.getClient().getId(), invoice.getInvoiceDate(),
                    invoice.getInvoiceStatus().getId(), invoice.getRemarks());
            if (is == 1) {
                redirectAttributes.addFlashAttribute("success", "Yup, Update was successful!");
            } else {
                redirectAttributes.addFlashAttribute("danger", "Nope, Invoice could not be updetad!");
            }
        }
        return REDIRECT_INVOICE;
    }

    @RequestMapping(value = "/invoice/delete/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
    public String delete(@PathVariable(name = "id") Integer id, RedirectAttributes redirectAttributes) {
        int deleteCountry = service.delete(id);
        if (deleteCountry == 1) {
            redirectAttributes.addFlashAttribute("success", "Yup, State has been deleted!");
        } else {
            redirectAttributes.addFlashAttribute("danger", "Nope, State could not be deleted!");
        }
        return REDIRECT_INVOICE;
    }

    @RequestMapping(value = "/invoice/details/{id}", method = {RequestMethod.GET})
    @ResponseBody
    public ResponseEntity<?> detailsFindById(@PathVariable(name = "id") Integer id) {
        Invoice invoice = service.findById(id).orElse(null);
        if (invoice != null) {
            return ResponseEntity.ok(invoice);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("State not found");
        }
    }

}
