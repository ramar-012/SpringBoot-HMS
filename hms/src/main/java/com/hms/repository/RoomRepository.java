package com.hms.repository;

import com.hms.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long>{
    //find rooms by room number
//    List<Room> findByRoom_Number(int room_number);
//
//    //find rooms by room type
//    List<Room> findByRoom_Type(String room_type);
//
//    //find rooms by room capacity
//    List<Room> findByCapacity(int capacity);

    //find rooms by hostel block
    //List<Room> findByHostelBlock(HostelBlock hostelBlock);


    //count rooms by room type
    //int countByRoom_Type(String room_type);

    //method to find all rooms by hostel block id
    //List<Room> findByHostelBlockId(long hostelBlockId);

    //custom query method to find rooms by hostel block and room capacity
    //List<Room> findAvailableRoomsByHostelBlockAndRoomCapacity(HostelBlock hostelBlock, int roomCapacity);
}
