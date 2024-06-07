package com.hms.controller;

import com.hms.entity.Room;
import com.hms.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/rooms")
public class RoomController {

    private final RoomService roomService;

    @Autowired
    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    // Define your controller methods here
    @GetMapping
    public ResponseEntity<List<Room>> getAllRooms(){
        List<Room> rooms = roomService.findAllRooms();
        return new ResponseEntity<>(rooms, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Room> getRoomById(@PathVariable long id){
        return roomService.findRoomById(id)
                .map(room -> new ResponseEntity<>(room, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Room> createRoom(@RequestBody Room room){
        if(roomService.isRoomPresent(room.getRoom_id())){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        Room savedRoom = roomService.saveRoom(room);
        return new ResponseEntity<>(savedRoom, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Room> updateRoom(@PathVariable long id, @RequestBody Room room){
        if(!roomService.isRoomPresent(id) || (!roomService.isRoomPresent(room.getRoom_id()))){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else if(id != room.getRoom_id()){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        room.setRoom_id(id);
        Room updatedRoom = roomService.saveRoom(room);
        return new ResponseEntity<>(updatedRoom, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRoom(@PathVariable long id){
        if(!roomService.isRoomPresent(id)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        roomService.deleteRoomById(id);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
