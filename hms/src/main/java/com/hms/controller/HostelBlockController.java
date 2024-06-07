package com.hms.controller;

import com.hms.entity.HostelBlock;
import com.hms.service.HostelBlockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/hostel-blocks")
public class HostelBlockController {

    private final HostelBlockService hostelBlockService;

    @Autowired
    public HostelBlockController(HostelBlockService hostelBlockService) {
        this.hostelBlockService = hostelBlockService;
    }

    // Define your controller methods here
    @GetMapping
    public ResponseEntity<List<HostelBlock>> getAllHostelBlocks(){
        List<HostelBlock> hostelBlocks = hostelBlockService.findALlHostelBlocks();
        return new ResponseEntity<>(hostelBlocks, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<HostelBlock> getHostelBlockById(@PathVariable long id){
        return hostelBlockService.findHostelBlockById(id)
                .map(hostelBlock -> new ResponseEntity<>(hostelBlock, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/hostel-id/{id}")
    public ResponseEntity<List<HostelBlock>> getBlocksByHostelId(@PathVariable long id){
        List<HostelBlock> block = hostelBlockService.findAllHostelBlocksByHostelId(id);
        return new ResponseEntity<>(block, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<HostelBlock> createHostelBlock(@RequestBody HostelBlock hostelBlock){
        if(hostelBlockService.isPresent(hostelBlock.getBlock_id())){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        HostelBlock savedHostelBlock = hostelBlockService.saveHostelBlock(hostelBlock);
        return new ResponseEntity<>(savedHostelBlock, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HostelBlock> updateHostelBlock(@PathVariable long id, @RequestBody HostelBlock hostelBlock){
        if(hostelBlockService.isPresent(id)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else if(id != hostelBlock.getBlock_id()){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        hostelBlock.setBlock_id(id);
        HostelBlock updatedHostelBlock = hostelBlockService.saveHostelBlock(hostelBlock);
        return new ResponseEntity<>(updatedHostelBlock, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHostelBlock(@PathVariable long id){
        if(hostelBlockService.isPresent(id)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        hostelBlockService.deleteHostelBlockById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
