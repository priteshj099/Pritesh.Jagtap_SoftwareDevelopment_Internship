package com.example.loadbooking.repository;

import com.example.loadbooking.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;
import java.util.List;
public interface BookingRepository extends JpaRepository<Booking, UUID> {
    List<Booking> findByTransporterId(String transporterId);
}