package com.kindsonthegenius.fleetapp.servieces;

import com.kindsonthegenius.fleetapp.model.Location;
import com.kindsonthegenius.fleetapp.model.VehicleModel;
import com.kindsonthegenius.fleetapp.repositories.VehicleModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class VehicleModelService {

    @Autowired
    private VehicleModelRepository vehicleModelRepository;

    //Return list of state
    public List<VehicleModel> getVehicleModel() {
        return vehicleModelRepository.findAll();
    }

    //Save new State
    public void save(VehicleModel vehicleModel) {
        vehicleModelRepository.save(vehicleModel);
    }

    public int updateById(int id, String description, String details, String last_modified_by, Date last_modified_date) {
       return vehicleModelRepository.updateById(id, description, details, last_modified_by, last_modified_date);
    }

    //get by id
    public Optional<VehicleModel> findById(int id) {
        return vehicleModelRepository.findById(id);
    }

    public int delete(Integer id){
        return vehicleModelRepository.deleteLocation(id);
    }
}
