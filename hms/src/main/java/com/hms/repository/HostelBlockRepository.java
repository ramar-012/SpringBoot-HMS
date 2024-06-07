package com.hms.repository;

import com.hms.entity.HostelBlock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HostelBlockRepository extends JpaRepository<HostelBlock, Long>{
    //find all hostel blocks by hostel
    //List<HostelBlock> findAllByHostel(Hostel hostel);

    //find a hostel by name
    HostelBlock findByName(String name);

    //method to find all hostel blocks by id
    HostelBlock findById(long id);

    //method to find all hostel blocks by hostel id
    List<HostelBlock> findByHostel(long id);

}
