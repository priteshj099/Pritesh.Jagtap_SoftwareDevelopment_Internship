package com.example.loadbooking.service;

import com.example.loadbooking.model.Load;
import com.example.loadbooking.model.LoadStatus;
import com.example.loadbooking.model.Booking;
import com.example.loadbooking.repository.LoadRepository;
import com.example.loadbooking.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
public class BookingService {
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private LoadRepository loadRepository;

    public Booking createBooking(Booking booking) {
        Load load = loadRepository.findById(booking.getLoadId()).orElseThrow(() -> new RuntimeException("Load not found"));
        if (load.getStatus() == LoadStatus.CANCELLED) {
            throw new RuntimeException("Cannot book a cancelled load");
        }
        return bookingRepository.save(booking);
    }

    public void deleteBooking(UUID bookingId) {
        Booking booking = bookingRepository.findById(bookingId).orElseThrow(() -> new RuntimeException("Booking not found"));
        Load load = loadRepository.findById(booking.getLoadId()).orElseThrow(() -> new RuntimeException("Load not found"));
        load.setStatus(LoadStatus.CANCELLED);
        loadRepository.save(load);
        bookingRepository.deleteById(bookingId);
    }

    public List<Booking> getBookings(String transporterId) {
        if (transporterId != null) {
            return bookingRepository.findByTransporterId(transporterId);
        } else {
            return bookingRepository.findAll();
        }
    }

    public Booking updateBooking(UUID bookingId, Booking booking) {
        Booking existingBooking = bookingRepository.findById(bookingId).orElseThrow(() -> new RuntimeException("Booking not found"));
        existingBooking.setTransporterId(booking.getTransporterId());
        existingBooking.setProposedRate(booking.getProposedRate());
        existingBooking.setComment(booking.getComment());
        return bookingRepository.save(existingBooking);
    }

    public Booking getBooking(UUID bookingId) {
        return bookingRepository.findById(bookingId).orElseThrow(() -> new RuntimeException("Booking not found"));
    }
}