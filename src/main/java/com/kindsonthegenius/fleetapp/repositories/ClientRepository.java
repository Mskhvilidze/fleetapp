package com.kindsonthegenius.fleetapp.repositories;

import com.kindsonthegenius.fleetapp.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "delete from client where id = ?")
    int deleteState(int id);

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "UPDATE fleetappdb.client set name = ?2, " +
            "details = ?3, address = ?4, email = ?5, phone = 6?, website = ?7  where id = ?1")
    int updateById(int id, String name, String details, String address, String email, String phone, String website);
}
