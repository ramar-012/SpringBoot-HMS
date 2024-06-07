package com.hms.service;
import com.hms.entity.Hostel;
import com.hms.repository.HostelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HostelService {
    private final HostelRepository hostelRepository;

    @Autowired
    public HostelService(HostelRepository hostelRepository) {
        this.hostelRepository = hostelRepository;
    }

    //method to find a hostel by id
    public Optional<Hostel> findHostelById(Long id) {
        return hostelRepository.findById(id);
    }


    //method to get all hostels
    public List<Hostel> getAllHostels() {
        return hostelRepository.findAll();
    }

    //method to check if a hostel exists by id
    public boolean hostelExists(Long id) {
        return hostelRepository.existsById(id);
    }

    //method to find hostel by name
    public Hostel findHostelByName(String name) {
        return hostelRepository.findByName(name);
    }

    //method to find all hostels
    public List<Hostel> findALlHostels() {
        return hostelRepository.findAll();
    }

    //method to save or update a hostel
    public Hostel saveHostel(Hostel hostel) {
        return hostelRepository.save(hostel);
    }

    //method to delete a hostel by id
    public void deleteHostelById(long id){
        hostelRepository.deleteById(id);
    }

    //find by type
    public List<Hostel> findHostelsByType(String type){
        return hostelRepository.findByType(type);
    }
}
