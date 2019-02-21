package com.example.abishekg.mileagecalculator.Models;

import java.io.Serializable;

/**
 * Created by abishek.g on 1/12/2018.
 */

public class VehicleDetails implements Serializable {
    int vehicleId;
    int tripCount;
    Float currrentMileage;
    Float fuelQuantity;



    Float odometerReading;


    public int getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    public int getTripCount() {
        return tripCount;
    }

    public void setTripCount(int tripCount) {
        this.tripCount = tripCount;
    }

    public Float getCurrrentMileage() {
        return currrentMileage;
    }

    public void setCurrrentMileage(Float currrentMileage) {
        this.currrentMileage = currrentMileage;
    }

    public Float getFuelQuantity() {
        return fuelQuantity;
    }

    public void setFuelQuantity(Float fuelQuantity) {
        this.fuelQuantity = fuelQuantity;
    }

    public Float getOdometerReading() {
        return odometerReading;
    }

    public void setOdometerReading(Float odometerReading) {
        this.odometerReading = odometerReading;
    }

    public VehicleDetails(int vehicleId, int tripCount, Float currrentMileage, Float fuelQuantity, Float odometerReading) {
        this.vehicleId = vehicleId;
        this.tripCount = tripCount;
        this.currrentMileage = currrentMileage;
        this.fuelQuantity = fuelQuantity;
        this.odometerReading = odometerReading;
    }
    public VehicleDetails(int vehicleId, Float currrentMileage, Float fuelQuantity, Float odometerReading) {
        this.vehicleId = vehicleId;
        this.currrentMileage = currrentMileage;
        this.fuelQuantity = fuelQuantity;
        this.odometerReading = odometerReading;
    }
    public VehicleDetails(Float currrentMileage, Float fuelQuantity, Float odometerReading) {
        this.currrentMileage = currrentMileage;
        this.fuelQuantity = fuelQuantity;
        this.odometerReading = odometerReading;
    }
}
