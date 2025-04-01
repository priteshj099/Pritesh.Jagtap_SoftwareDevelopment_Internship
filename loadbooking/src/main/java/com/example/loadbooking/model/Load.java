package com.example.loadbooking.model;

import java.sql.Timestamp;
import java.util.UUID;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Load {

    @Id
    @GeneratedValue
    private UUID id;

    private String shipperId;

    @Embedded
    private Facility facility;

    private String productType;
    private String truckType;
    private int noOfTrucks;
    private double weight;
    private String comment;
    private Timestamp datePosted;

    @Enumerated(EnumType.STRING)
    private LoadStatus status = LoadStatus.POSTED;

    // Getters and Setters

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getShipperId() {
        return shipperId;
    }

    public void setShipperId(String shipperId) {
        this.shipperId = shipperId;
    }

    public Facility getFacility() {
        return facility;
    }

    public void setFacility(Facility facility) {
        this.facility = facility;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getTruckType() {
        return truckType;
    }

    public void setTruckType(String truckType) {
        this.truckType = truckType;
    }

    public int getNoOfTrucks() {
        return noOfTrucks;
    }

    public void setNoOfTrucks(int noOfTrucks) {
        this.noOfTrucks = noOfTrucks;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Timestamp getDatePosted() {
        return datePosted;
    }

    public void setDatePosted(Timestamp datePosted) {
        this.datePosted = datePosted;
    }

    public LoadStatus getStatus() {
        return status;
    }

    public void setStatus(LoadStatus cancelled) {
        this.status = cancelled;
    }

    // Facility class (embedded)
    @Embeddable
    public static class Facility {
        private String loadingPoint;
        private String unloadingPoint;
        private Timestamp loadingDate;
        private Timestamp unloadingDate;

        // Getters and Setters

        public String getLoadingPoint() {
            return loadingPoint;
        }

        public void setLoadingPoint(String loadingPoint) {
            this.loadingPoint = loadingPoint;
        }

        public String getUnloadingPoint() {
            return unloadingPoint;
        }

        public void setUnloadingPoint(String unloadingPoint) {
            this.unloadingPoint = unloadingPoint;
        }

        public Timestamp getLoadingDate() {
            return loadingDate;
        }

        public void setLoadingDate(Timestamp loadingDate) {
            this.loadingDate = loadingDate;
        }

        public Timestamp getUnloadingDate() {
            return unloadingDate;
        }

        public void setUnloadingDate(Timestamp unloadingDate) {
            this.unloadingDate = unloadingDate;
        }
    }
}
