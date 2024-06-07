package com.hms.entity;

import javax.persistence.*;

@Entity
@Table(name = "rooms")

public class Room{

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private long room_id;

    //@ManyToOne(fetch = FetchType.LAZY)
    //@JoinColumn(name = "block_id", nullable = false)
    @Column(nullable = false)
    private int block_id;
    private int number;
    private String type;
    private int capacity;
    private String status;
    private int areaSqm;

    //no argument constructor for hibernate
    public Room() {
    }
    //constructor

    public Room(long room_id, int block_id, int number, String type, int capacity, String status, int areaSqm) {
        this.room_id = room_id;
        this.block_id = block_id;
        this.number = number;
        this.type = type;
        this.capacity = capacity;
        this.status = status;
        this.areaSqm = areaSqm;
    }

    //getters and setters

    public long getRoom_id() {
        return room_id;
    }

    public void setRoom_id(long room_id) {
        this.room_id = room_id;
    }

    public int getBlock_id() {
        return block_id;
    }

    public void setBlock_id(int block_id) {
        this.block_id = block_id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getRoomType() {
        return type;
    }

    public void setRoomType(String roomType) {
        this.type = roomType;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getAreaSqm() {
        return areaSqm;
    }

    public void setAreaSqm(int areaSqm) {
        this.areaSqm = areaSqm;
    }
}