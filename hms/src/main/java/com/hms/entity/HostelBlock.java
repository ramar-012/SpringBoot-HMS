package com.hms.entity;

import javax.persistence.*;

@Entity
@Table(name = "hostel_blocks")

public class HostelBlock{

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private long block_id;

    @Column(nullable = false)
    private long hostel;

    @Column(nullable = false)
    private String name;

    @Column(length = 255)
    private String description;

    //constructor

    //no-args constructor
    public HostelBlock() {
    }

    //all-args constructor

    public HostelBlock(long id, long hostel, String name, String description) {
        this.block_id = id;
        this.hostel = hostel;
        this.name = name;
        this.description = description;
        //this.rooms = rooms;
    }

    //getters and setters

    public long getBlock_id() {
        return block_id;
    }

    public void setBlock_id(long block_id) {
        this.block_id = block_id;
    }

    public long getHostel() {
        return hostel;
    }

    public void setHostel(long hostel) {
        this.hostel = hostel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

//    public Room getRooms() {
//        return rooms;
//    }
//
//    public void setRooms(Room rooms) {
//        this.rooms = rooms;
//    }
}