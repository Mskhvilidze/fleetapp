package com.kindsonthegenius.fleetapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.kindsonthegenius.fleetapp.model.State;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Repository
public interface StateRepository extends JpaRepository<State, Integer> {

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "delete from state where id = ?")
    int deleteState(int id);

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "UPDATE fleetappdb.state set name = ?2, " +
            "details = ?3, code = ?4 where id = ?1")
    int updateById(int id, String name, String details, String code);

}
