package com.kindsonthegenius.fleetapp.servieces;

import com.kindsonthegenius.fleetapp.model.JobTitle;
import com.kindsonthegenius.fleetapp.model.Location;
import com.kindsonthegenius.fleetapp.repositories.JobTitleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobTitleService {

    @Autowired
    private JobTitleRepository jobTitleRepository;

    //Return list of state
    public List<JobTitle> getJobTitle() {
        return jobTitleRepository.findAll();
    }

    //Save new State
    public void save(JobTitle jobTitle) {
        jobTitleRepository.save(jobTitle);
    }

    //get by id
    public Optional<JobTitle> findById(int id) {
        return jobTitleRepository.findById(id);
    }

    public int delete(Integer id){
        return jobTitleRepository.deleteLocation(id);
    }
}
