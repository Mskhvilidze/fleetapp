package com.kindsonthegenius.fleetapp.servieces;

import com.kindsonthegenius.fleetapp.model.Location;
import com.kindsonthegenius.fleetapp.model.VehicleMake;
import com.kindsonthegenius.fleetapp.repositories.VehicleMakeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleMakeService {

    @Autowired
    private VehicleMakeRepository vehicleMakeRepository;

    //Return list of state
    public List<VehicleMake> getVehicleMake() {
        return vehicleMakeRepository.findAll();
    }

    //Save new State
    public void save(VehicleMake location) {
        vehicleMakeRepository.save(location);
    }

    //get by id
    public Optional<VehicleMake> findById(int id) {
        return vehicleMakeRepository.findById(id);
    }

    public int delete(Integer id){
        return vehicleMakeRepository.deleteLocation(id);
    }
}
