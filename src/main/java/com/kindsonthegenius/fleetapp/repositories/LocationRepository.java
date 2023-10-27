package com.kindsonthegenius.fleetapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.kindsonthegenius.fleetapp.model.Location;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Repository
public interface LocationRepository extends JpaRepository<Location, Integer> {
    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "delete from fleetappdb.location where id = ?")
    int deleteLocation(int id);
    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "UPDATE fleetappdb.location set address = ?2, " +
            "city = ?3, countryid = ?4, description = ?5, details = ?6, stateid = ?7 where id = ?1")
    int updateById(int id, String address, String city, Integer countryid, String description, String details, Integer stateid);
}