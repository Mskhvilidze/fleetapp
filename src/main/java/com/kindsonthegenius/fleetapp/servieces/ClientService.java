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
    public List<Client> getStates() {
        return repository.findAll();
    }

    //Save new State
    public void save(Client client) {
        repository.save(client);
    }

    public int updateById(int id, String name, String details, String address, String email, String phone, String website) {
        return repository.updateById(id, name, details, address, email, phone, website);
    }
    //get by id
    public Optional<Client> findById(int id) {
        return repository.findById(id);
    }

    public int delete(Integer id){
        return repository.deleteState(id);
    }
}
