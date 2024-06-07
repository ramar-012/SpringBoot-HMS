package com.hms.repository;

import com.hms.entity.Hostel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HostelRepository extends JpaRepository<Hostel, Long>{
    //find a hostel by name
    Hostel findByName(String name);

    //find a hostel by location
    Hostel findByLocation(String location);

    //find by type
    List<Hostel> findByType(String type);

}
