package com.kindsonthegenius.fleetapp.servieces;

import com.kindsonthegenius.fleetapp.model.Location;
import com.kindsonthegenius.fleetapp.model.VehicleStatus;
import com.kindsonthegenius.fleetapp.repositories.VehicleStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class VehicleStatusService {

    @Autowired
    private VehicleStatusRepository vehicleStatusRepository;

    //Return list of state
    public List<VehicleStatus> getVehicleStatus() {
        return vehicleStatusRepository.findAll();
    }

    //Save new State
    public void save(VehicleStatus vehicleStatus) {
        vehicleStatusRepository.save(vehicleStatus);
    }

    //get by id
    public Optional<VehicleStatus> findById(int id) {
        return vehicleStatusRepository.findById(id);
    }

    public int updateById(int id, String description, String details, String last_modified_by, Date last_modified_date) {
        return vehicleStatusRepository.updateById(id, description, details, last_modified_by, last_modified_date);
    }

    public int delete(Integer id){
        return vehicleStatusRepository.deleteLocation(id);
    }
}
