package com.hms.controller;

import com.hms.entity.Hostel;
import com.hms.service.HostelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/hostels")
public class HostelController {
    private final HostelService hostelService;

    @Autowired
    public HostelController(HostelService hostelService) {
        this.hostelService = hostelService;
    }

    @GetMapping
    public ResponseEntity<List<Hostel>> getAllHostels(){
        List<Hostel> hostels = hostelService.getAllHostels();
        return new ResponseEntity<>(hostels, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Hostel> getHostelById(@PathVariable long id){
        return hostelService.findHostelById(id)
                .map(hostel -> new ResponseEntity<>(hostel, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<List<Hostel>> findByType(@PathVariable String type){
        return new ResponseEntity<>(hostelService.findHostelsByType(type), HttpStatus.OK);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Hostel> findByName(@PathVariable String name){
        return new ResponseEntity<>(hostelService.findHostelByName(name), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Hostel> createHostel(@RequestBody Hostel hostel){
        if(hostelService.hostelExists(hostel.getHostel_id())){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        Hostel savedHostel = hostelService.saveHostel(hostel);
        return new ResponseEntity<>(savedHostel, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Hostel> updateHostel(@PathVariable long id, @RequestBody Hostel hostel){
        if(!hostelService.hostelExists(id)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else if(hostel.getHostel_id() != id){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        hostel.setHostel_id(id);
        Hostel updatedHostel = hostelService.saveHostel(hostel);
        return new ResponseEntity<>(updatedHostel, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Hostel> deleteHostel(@PathVariable long id){
        if(!hostelService.hostelExists(id)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        hostelService.deleteHostelById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
