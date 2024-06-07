package com.hms.service;

import com.hms.entity.Room;
import com.hms.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoomService {

    private final RoomRepository roomRepository;

    @Autowired
    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    // Define service methods to interact with RoomRepository
    //method to find a room by id
    public Optional<Room> findRoomById(Long id) {
        return roomRepository.findById(id);
    }

    //method to find all rooms
    public List<Room> findAllRooms() {
        return roomRepository.findAll();
    }

    //method to check if room present by id
    public boolean isRoomPresent(Long id) {
        return roomRepository.existsById(id);
    }

    //method to save or update a room
    public Room saveRoom(Room room) {
        return roomRepository.save(room);
    }

    //method to delete a room by id
    public void deleteRoomById(Long id) {
        roomRepository.deleteById(id);
    }
}
