package com.kindsonthegenius.fleetapp.repositories;

import com.kindsonthegenius.fleetapp.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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
    @Query(nativeQuery = true,
            value = "UPDATE fleetappdb.client SET countryId = :countryId, stateId = :stateId, name = :name, details = :details, " +
                    "website = :website, address = :address, city = :city, phone = :phone, mobile = :mobile, email = :eMail " +
                    "WHERE id = :id")
    int updateById(@Param("id") int id, @Param("countryId") Integer countryId, @Param("stateId") Integer stateId,
                   @Param("name") String name, @Param("details") String details, @Param("website") String website,
                   @Param("address") String address, @Param("city") String city, @Param("phone") String phone,
                   @Param("mobile") String mobile, @Param("eMail") String eMail);

}
