package com.kindsonthegenius.fleetapp.servieces;

import com.kindsonthegenius.fleetapp.model.State;
import com.kindsonthegenius.fleetapp.repositories.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
public class StateService {

    @Autowired
    StateRepository repository;

    //Return list of state
    public List<State> getStates() {
        return repository.findAll();
    }

    //Save new State
    public void save(State state) {
        repository.save(state);
    }

    public int updateById(int id, String name, String details, String code) {
        return repository.updateById(id, name, details, code);
    }
    //get by id
    public Optional<State> findById(int id) {
        return repository.findById(id);
    }

    public int delete(Integer id){
        return repository.deleteState(id);
    }

}
