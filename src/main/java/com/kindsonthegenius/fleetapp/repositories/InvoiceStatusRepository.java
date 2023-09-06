package com.kindsonthegenius.fleetapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.kindsonthegenius.fleetapp.model.InvoiceStatus;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface InvoiceStatusRepository extends JpaRepository<InvoiceStatus, Integer> {
    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "delete from fleetappdb.invoice_status where id = ?")
    int deleteLocation(int id);
}
