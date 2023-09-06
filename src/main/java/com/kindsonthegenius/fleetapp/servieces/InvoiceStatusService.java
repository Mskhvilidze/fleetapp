package com.kindsonthegenius.fleetapp.servieces;

import com.kindsonthegenius.fleetapp.model.InvoiceStatus;
import com.kindsonthegenius.fleetapp.model.Location;
import com.kindsonthegenius.fleetapp.repositories.InvoiceStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    //get by id
    public Optional<InvoiceStatus> findById(int id) {
        return invoiceStatusRepository.findById(id);
    }

    public int delete(Integer id){
        return invoiceStatusRepository.deleteLocation(id);
    }
}
