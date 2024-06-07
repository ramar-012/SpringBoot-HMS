package com.hms.service;

import com.hms.entity.Reservation;
import com.hms.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;

    @Autowired
    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    // Method to find a reservation by id
    public Optional<Reservation> findReservationById(Long id) {
        return reservationRepository.findById(id);
    }

    // Method to find all reservations
    public List<Reservation> findAllReservations() {
        return reservationRepository.findAll();
    }

    // Method to check if a reservation exists by id
    public boolean reservationExists(long id) {
        return !reservationRepository.existsById(id);
    }

    // Method to find all reservations by user id
    public List<Reservation> findReservationsByUserId(long userId) {
        return reservationRepository.findByUserId(userId);
    }

    // Method to save or update a reservation
    public Reservation saveReservation(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    // Method to delete a reservation by id
    public void deleteReservation(long id) {
        reservationRepository.deleteById(id);
    }
}

