package com.example.loadbooking.service;

import com.example.loadbooking.model.Load;
import com.example.loadbooking.repository.LoadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class LoadService {
    @Autowired
    private LoadRepository loadRepository;

    public Load createLoad(Load load) {
        return loadRepository.save(load);
    }

    public Load getLoad(UUID loadId) {
        return loadRepository.findById(loadId).orElseThrow(() -> new RuntimeException("Load not found"));
    }

    public void deleteLoad(UUID loadId) {
        loadRepository.deleteById(loadId);
    }

    public List<Load> getLoads(String shipperId, String truckType) {
        if (shipperId != null && truckType != null) {
            return loadRepository.findByShipperIdAndTruckType(shipperId, truckType);
        } else if (shipperId != null) {
            return loadRepository.findByShipperId(shipperId);
        } else if (truckType != null) {
            return loadRepository.findByTruckType(truckType);
        } else {
            return loadRepository.findAll();
        }
    }

    public Load updateLoad(UUID loadId, Load load) {
        Load existingLoad = loadRepository.findById(loadId).orElseThrow(() -> new RuntimeException("Load not found"));
        existingLoad.setShipperId(load.getShipperId());
        existingLoad.setTruckType(load.getTruckType());
        //existingLoad.setPickupLocation(load.getPickupLocation());
        //existingLoad.setDeliveryLocation(load.getDeliveryLocation());
        existingLoad.setWeight(load.getWeight());
        return loadRepository.save(existingLoad);
    }

    // Other methods for update and fetching loads
}