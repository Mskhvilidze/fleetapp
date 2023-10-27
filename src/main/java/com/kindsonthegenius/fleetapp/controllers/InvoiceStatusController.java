package com.kindsonthegenius.fleetapp.controllers;

import com.kindsonthegenius.fleetapp.model.EmployeeType;
import com.kindsonthegenius.fleetapp.model.InvoiceStatus;
import com.kindsonthegenius.fleetapp.model.VehicleMake;
import com.kindsonthegenius.fleetapp.servieces.InvoiceStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import util.WebPage;

import java.text.SimpleDateFormat;
import java.util.Date;
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
        model.addAttribute("invoiceStatusList", invoiceStatusList);
        model.addAttribute("title", "Invoice_Status_Value Management");
        return WebPage.INVOICE_STATUS;
    }

    @PostMapping("/invoiceStatus/addNew")
    public String addNew(@ModelAttribute InvoiceStatus invoiceStatus) {
        invoiceStatus.setLastModifiedBy(getDate());
        invoiceStatus.setLastModifiedDate(new Date());
        invoiceStatusService.save(invoiceStatus);
        return REDIRECT_INVOICE_STATUS;
    }

    @RequestMapping(value = "/invoiceStatus/findById/")
    @ResponseBody
    public ResponseEntity<?> findById(Integer id) {
        InvoiceStatus invoiceStatus = invoiceStatusService.findById(id).orElse(null);
        if (invoiceStatus != null) {
            return ResponseEntity.ok(invoiceStatus);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invoice_Status not found");
        }
    }

    @RequestMapping(value = "/invoiceStatus/update", method = {RequestMethod.PUT, RequestMethod.GET})
    public String update(@ModelAttribute InvoiceStatus invoiceStatus, RedirectAttributes redirectAttributes) {
        if (invoiceStatus.getId() == null) {
            redirectAttributes.addFlashAttribute("danger", "Nope, it doesn't work that way!");
        } else {
            int is = invoiceStatusService.updateById(invoiceStatus.getId(), invoiceStatus.getDescription(), invoiceStatus.getDetails(),
                    getDate(), new Date());
            if (is == 1) {
                redirectAttributes.addFlashAttribute("success", "Yup, Update was successful!");
            } else {
                redirectAttributes.addFlashAttribute("danger", "Nope, Invoice_Status could not be updetad!");
            }
        }
        return REDIRECT_INVOICE_STATUS;
    }

    @RequestMapping(value = "/invoiceStatus/delete/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
    public String delete(@PathVariable(name = "id") Integer id, RedirectAttributes redirectAttributes) {
        int invoiceStatus = invoiceStatusService.delete(id);
        if (invoiceStatus == 1) {
            redirectAttributes.addFlashAttribute("success", "Yup, InvoiceStatus has been deleted!");
        } else {
            redirectAttributes.addFlashAttribute("danger", "Nope, InvoiceStatus could not be deleted!");
        }
        return REDIRECT_INVOICE_STATUS;
    }

    @RequestMapping(value = "/invoiceStatus/details/{id}", method = {RequestMethod.GET})
    @ResponseBody
    public ResponseEntity<?> detailsFindById(@PathVariable(name = "id") Integer id) {
        InvoiceStatus invoiceStatus = invoiceStatusService.findById(id).orElse(null);
        if (invoiceStatus != null) {
            return ResponseEntity.ok(invoiceStatus); // JobTitle wurde gefunden
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("VehicleMake not found"); // JobTitle wurde nicht gefunden
        }
    }
    private String getDate() {
        String pattern = "HH:mm:ss.SSSZ";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(new Date());
    }
}
