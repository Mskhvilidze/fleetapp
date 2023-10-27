package com.kindsonthegenius.fleetapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.kindsonthegenius.fleetapp.model.EmployeeType;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Repository
public interface EmployeeTypeRepository extends JpaRepository<EmployeeType, Integer> {
    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "delete from fleetappdb.employee_type where id = ?")
    int deleteLocation(int id);

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "UPDATE fleetappdb.employee_type set description = ?2, " +
            "details = ?3, last_modified_by = ?4, last_modified_date = ?5 where id = ?1")
    int updateById(int id, String description, String details, String last_modified_by, Date last_modified_date);
}
