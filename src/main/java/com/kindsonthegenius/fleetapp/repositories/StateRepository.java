package com.kindsonthegenius.fleetapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kindsonthegenius.fleetapp.model.State;

@Repository
public interface StateRepository extends JpaRepository<State, Integer> {

}
