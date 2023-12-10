package com.kindsonthegenius.fleetapp.repositories;

import com.kindsonthegenius.fleetapp.model.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

public interface InvoiceRepository extends JpaRepository<Invoice, Integer> {
    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "delete from fleetappdb.invoice where id = ?")
    int deleteInvoice(int id);

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "UPDATE fleetappdb.invoice Set clientid = :clientid, " +
            "invoice_date = :invoiceDate, invoicestatusid = :invoicestatusid, remarks = :remarks where id = :id")
    int updateById(@Param("id") int id, @Param("clientid") Integer clientid,
                   @Param("invoiceDate") Date invoiceDate, @Param("invoicestatusid") Integer invoicestatusid,
                   @Param("remarks") String remarks);

}
