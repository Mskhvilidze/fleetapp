package com.kindsonthegenius.fleetapp.servieces;

import com.kindsonthegenius.fleetapp.model.Country;
import com.kindsonthegenius.fleetapp.repositories.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryService {
    @Autowired
    CountryRepository repository;

    public void insert() {
        repository.insert("Budapest", "02", "North America", "United States", "American");
    }

    //Return list of countries
    public List<Country> getCountries() {
        return repository.findAll();
    }

    //Save new Country
    public void save(Country country) {
        repository.save(country);
    }

    //get by id
    public Optional<Country> findById(int id) {
        return repository.findById(id);
    }

    public int delete(Integer id){
        return repository.deleteCountry(id);
    }
}
