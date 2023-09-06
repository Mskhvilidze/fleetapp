package com.kindsonthegenius.fleetapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.kindsonthegenius.fleetapp.model.EmployeeType;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface EmployeeTypeRepository extends JpaRepository<EmployeeType, Integer> {
    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "delete from fleetappdb.employee_type where id = ?")
    int deleteLocation(int id);
}
