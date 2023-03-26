package com.kindsonthegenius.fleetapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.kindsonthegenius.fleetapp.model.Country;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface CountryRepository extends JpaRepository<Country, Integer> {
    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "insert into country values (default, ?, ?, ?, ?, ?)")
    void insert(String capital, String code, String continent, String description, String nationality);

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "delete from country where id = ?")
    int deleteCountry(int id);
}
