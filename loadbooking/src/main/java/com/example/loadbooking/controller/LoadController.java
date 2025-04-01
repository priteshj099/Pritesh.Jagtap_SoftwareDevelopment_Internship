package com.example.loadbooking.controller;

import com.example.loadbooking.model.Load;
import com.example.loadbooking.service.LoadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/load")
public class LoadController {

    @Autowired
    private LoadService loadService;

    @PostMapping
    public ResponseEntity<Load> createLoad(@RequestBody Load load) {
        Load createdLoad = loadService.createLoad(load);
        return new ResponseEntity<>(createdLoad, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Load>> getLoads(
            @RequestParam(required = false) String shipperId,
            @RequestParam(required = false) String truckType) {
        List<Load> loads = loadService.getLoads(shipperId, truckType);
        return new ResponseEntity<>(loads, HttpStatus.OK);
    }

    @GetMapping("/{loadId}")
    public ResponseEntity<Load> getLoad(@PathVariable UUID loadId) {
        Load load = loadService.getLoad(loadId);
        return new ResponseEntity<>(load, HttpStatus.OK);
    }

    @PutMapping("/{loadId}")
    public ResponseEntity<Load> updateLoad(@PathVariable UUID loadId, @RequestBody Load load) {
        Load updatedLoad = loadService.updateLoad(loadId, load);
        return new ResponseEntity<>(updatedLoad, HttpStatus.OK);
    }

    @DeleteMapping("/{loadId}")
    public ResponseEntity<Void> deleteLoad(@PathVariable UUID loadId) {
        loadService.deleteLoad(loadId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}