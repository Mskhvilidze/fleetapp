package com.kindsonthegenius.fleetapp.servieces;

import com.kindsonthegenius.fleetapp.model.InvoiceStatus;
import com.kindsonthegenius.fleetapp.model.Location;
import com.kindsonthegenius.fleetapp.repositories.InvoiceStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
public class InvoiceStatusService {

    @Autowired
    private InvoiceStatusRepository invoiceStatusRepository;

    //Return list of state
    public List<InvoiceStatus> InvoiceStatus() {
        return invoiceStatusRepository.findAll();
    }

    //Save new State
    public void save(InvoiceStatus invoiceStatus) {
        invoiceStatusRepository.save(invoiceStatus);
    }

    public int updateById(int id, String description, String details, String last_modified_by, Date last_modified_date) {
        return invoiceStatusRepository.updateById(id, description, details, last_modified_by, last_modified_date);
    }
    //get by id
    public Optional<InvoiceStatus> findById(int id) {
        return invoiceStatusRepository.findById(id);
    }

    public int delete(Integer id){
        return invoiceStatusRepository.deleteLocation(id);
    }
}
