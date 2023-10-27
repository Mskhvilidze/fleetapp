package com.kindsonthegenius.fleetapp.servieces;

import com.kindsonthegenius.fleetapp.model.EmployeeType;
import com.kindsonthegenius.fleetapp.model.Location;
import com.kindsonthegenius.fleetapp.repositories.EmployeeTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeTypeService {

    @Autowired
    private EmployeeTypeRepository employeeTypeRepository;

    //Return list of state
    public List<EmployeeType> getEmploystype() {
        return employeeTypeRepository.findAll();
    }

    //Save new State
    public void save(EmployeeType employeeType) {
        employeeTypeRepository.save(employeeType);
    }

    public int updateById(int id, String description, String details, String last_modified_by, Date last_modified_date) {
        return employeeTypeRepository.updateById(id, description, details, last_modified_by, last_modified_date);
    }

    //get by id
    public Optional<EmployeeType> findById(int id) {
        return employeeTypeRepository.findById(id);
    }

    public int delete(Integer id){
        return employeeTypeRepository.deleteLocation(id);
    }
}
