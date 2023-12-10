package com.kindsonthegenius.fleetapp.servieces;

import com.kindsonthegenius.fleetapp.model.Client;
import com.kindsonthegenius.fleetapp.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {
    @Autowired
    ClientRepository repository;

    //Return list of state
    public List<Client> getClients() {
        return repository.findAll();
    }

    //Save new State
    public void save(Client client) {
        repository.save(client);
    }

    public int updateById(Integer id, Integer countryId, Integer stateId, String name, String details, String website,
                          String address, String city, String phone, String mobile, String eMail) {
        return repository.updateById(id, countryId, stateId, name, details, website, address, city, phone,
                mobile, eMail);
    }
    //get by id
    public Optional<Client> findById(int id) {
        return repository.findById(id);
    }

    public int delete(Integer id){
        return repository.deleteState(id);
    }
}
