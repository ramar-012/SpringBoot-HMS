package com.hms.service;

import com.hms.entity.HostelBlock;
import com.hms.repository.HostelBlockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HostelBlockService {

    private final HostelBlockRepository hostelBlockRepository;

    @Autowired
    public HostelBlockService(HostelBlockRepository hostelBlockRepository) {
        this.hostelBlockRepository = hostelBlockRepository;
    }

    // Define service methods to interact with HostelBlockRepository
    //method to find a hostel block by id
    public Optional<HostelBlock> findHostelBlockById(Long id) {
        return hostelBlockRepository.findById(id);
    }


    //check if hostel Block exists by id
    public boolean isPresent(long id) {
        return !hostelBlockRepository.existsById(id);
    }

    //method to return all hostel block
    public List<HostelBlock> findALlHostelBlocks() {
        return hostelBlockRepository.findAll();
    }

    //method to find all hostel blocks by id
    public List<HostelBlock> findAllHostelBlocksByHostelId(long hostelId) {
        return hostelBlockRepository.findByHostel(hostelId);
    }

    //method to save or update a hostel block
    public HostelBlock saveHostelBlock(HostelBlock hostelBlock) {
        return hostelBlockRepository.save(hostelBlock);
    }

    //method to delete a hostel block by id
    public void deleteHostelBlockById(Long id) {
        hostelBlockRepository.deleteById(id);
    }
}
