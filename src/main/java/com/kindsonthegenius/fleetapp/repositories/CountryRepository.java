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

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "UPDATE fleetappdb.country set capital = ?2, " +
            "code = ?3, continent = ?4, description = ?5, nationality = ?6 where id = ?1")
    int updateById(int id, String capital, String code, String continent, String description, String nationality);
}
