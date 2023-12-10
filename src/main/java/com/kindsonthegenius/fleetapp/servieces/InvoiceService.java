package com.kindsonthegenius.fleetapp.servieces;

import com.kindsonthegenius.fleetapp.model.Client;
import com.kindsonthegenius.fleetapp.model.Invoice;
import com.kindsonthegenius.fleetapp.model.InvoiceStatus;
import com.kindsonthegenius.fleetapp.repositories.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class InvoiceService {

    @Autowired
    InvoiceRepository repository;

    //Return list of state
    public List<Invoice> getInvoices() {
        return repository.findAll();
    }

    //Save new State
    public void save(Invoice invoice) {
        repository.save(invoice);
    }

    public int updateById(int id, Integer clientid, Date invoiceDate, Integer invoicestatusid, String remarks) {
        return repository.updateById(id, clientid, invoiceDate, invoicestatusid, remarks);
    }
    //get by id
    public Optional<Invoice> findById(int id) {
        return repository.findById(id);
    }

    public int delete(Integer id){
        return repository.deleteInvoice(id);
    }
}
