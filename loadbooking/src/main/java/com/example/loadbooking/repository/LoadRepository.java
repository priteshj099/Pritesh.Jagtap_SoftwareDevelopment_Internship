package com.example.loadbooking.repository;

import com.example.loadbooking.model.Load;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;
import java.util.List;

public interface LoadRepository extends JpaRepository<Load, UUID> {
    List<Load> findByShipperId(String shipperId);
    List<Load> findByTruckType(String truckType);
    List<Load> findByShipperIdAndTruckType(String shipperId, String truckType);
}