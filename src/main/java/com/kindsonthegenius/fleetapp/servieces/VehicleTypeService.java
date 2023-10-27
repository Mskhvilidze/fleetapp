package com.kindsonthegenius.fleetapp.servieces;

import com.kindsonthegenius.fleetapp.model.Location;
import com.kindsonthegenius.fleetapp.model.VehicleType;
import com.kindsonthegenius.fleetapp.repositories.LocationRepository;
import com.kindsonthegenius.fleetapp.repositories.VehicleTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class VehicleTypeService {

    @Autowired
    private VehicleTypeRepository vehicleTypeRepository;

    //Return list of state
    public List<VehicleType> getVehicleType() {
        return vehicleTypeRepository.findAll();
    }

    //Save new State
    public void save(VehicleType vehicleType) {
        vehicleTypeRepository.save(vehicleType);
    }

    public int updateById(int id, String description, String details, String last_modified_by, Date last_modified_date) {
       return vehicleTypeRepository.updateById(id, description, details, last_modified_by, last_modified_date);
    }

    //get by id
    public Optional<VehicleType> findById(int id) {
        return vehicleTypeRepository.findById(id);
    }

    public int delete(Integer id){
        return vehicleTypeRepository.deleteLocation(id);
    }
}
