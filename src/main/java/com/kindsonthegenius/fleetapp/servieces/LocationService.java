package com.kindsonthegenius.fleetapp.servieces;


import com.kindsonthegenius.fleetapp.model.Location;
import com.kindsonthegenius.fleetapp.model.State;
import com.kindsonthegenius.fleetapp.repositories.LocationRepository;
import com.kindsonthegenius.fleetapp.repositories.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LocationService {

    @Autowired
    private LocationRepository repository;

    //Return list of state
    public List<Location> getLocations() {
        return repository.findAll();
    }

    //Save new State
    public void save(Location location) {
        repository.save(location);
    }

    //get by id
    public Optional<Location> findById(int id) {
        return repository.findById(id);
    }

    public int delete(Integer id){
        return repository.deleteLocation(id);
    }
}