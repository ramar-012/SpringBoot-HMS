package com.hms.repository;

import com.hms.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    // Find reservations by user ID
    List<Reservation> findByUserId(Long userId);

    // Find reservations by room ID
    List<Reservation> findByRoomId(Long roomId);

    // Find reservations by status
    List<Reservation> findByStatus(String status);

    // Find reservations by user ID and status
    List<Reservation> findByUserIdAndStatus(Long userId, String status);

    // Find reservations by room ID and status
    List<Reservation> findByRoomIdAndStatus(Long roomId, String status);

    // Additional method to find all reservations by user ID
    List<Reservation> findAllByUserId(Long userId);
}
