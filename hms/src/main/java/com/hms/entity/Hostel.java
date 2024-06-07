package com.hms.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "hostels")
public class Hostel {
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private long hostel_id;

    @Column(nullable = false)
    private String name;
    private String location;
    private String type;

    //No argument constructor for hibernate
    public Hostel() {
    }

    //Constructor

    public Hostel(long id, String name, String location, List<HostelBlock> block, String type) {
        this.hostel_id = id;
        this.name = name;
        this.location = location;
        //this.block = block;
        this.type = type;
    }

    //Getters and Setters

    public long getHostel_id() {
        return hostel_id;
    }

    public void setHostel_id(long hostel_id) {
        this.hostel_id = hostel_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getType(){
        return this.type;
    }

    public void setType(String type){
        this.type = type;
    }

}
