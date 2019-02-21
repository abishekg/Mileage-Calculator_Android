package com.example.abishekg.mileagecalculator.Models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by abishek.g on 1/11/2018.
 */

public class DriverDetails implements Serializable {




    private int id;
    private String profileName;
    private String profileDob;
    private String profileVehicleMake;
    private String profileVehicleModel;
    private String profileFuelType;
    private String profileLicensePlate;
    private String profileInsuranceNumber;
    private String getProfileInsuranceExpiryDate;
    private String distanceIn;
    List<VehicleDetails> tripList = new ArrayList<>();

    public DriverDetails(int id, String profileName, String profileDob, String profileVehicleMake, String profileVehicleModel, String profileFuelType, String profileLicensePlate, String profileInsuranceNumber, String getProfileInsuranceExpiryDate, String distanceIn, List<VehicleDetails> tripList) {
        this.id = id;
        this.profileName = profileName;
        this.profileDob = profileDob;
        this.profileVehicleMake = profileVehicleMake;
        this.profileVehicleModel = profileVehicleModel;
        this.profileFuelType = profileFuelType;
        this.profileLicensePlate = profileLicensePlate;
        this.profileInsuranceNumber = profileInsuranceNumber;
        this.getProfileInsuranceExpiryDate = getProfileInsuranceExpiryDate;
        this.distanceIn = distanceIn;
        this.tripList = tripList;
    }

    public DriverDetails(String profileName, String profileDob, String profileVehicleMake, String profileVehicleModel, String profileFuelType, String profileLicensePlate, String profileInsuranceNumber, String getProfileInsuranceExpiryDate, String distanceIn, List<VehicleDetails> tripList) {
        this.profileName = profileName;
        this.profileDob = profileDob;
        this.profileVehicleMake = profileVehicleMake;
        this.profileVehicleModel = profileVehicleModel;
        this.profileFuelType = profileFuelType;
        this.profileLicensePlate = profileLicensePlate;
        this.profileInsuranceNumber = profileInsuranceNumber;
        this.getProfileInsuranceExpiryDate = getProfileInsuranceExpiryDate;
        this.distanceIn = distanceIn;
        this.tripList = tripList;
    }




    public DriverDetails(int id, String profileName, String profileDob, String profileVehicleMake,
                         String profileVehicleModel, String profileFuelType, String profileLicensePlate,
                         String profileInsuranceNumber, String getProfileInsuranceExpiryDate, String distanceIn) {
        this.id = id;
        this.profileName = profileName;
        this.profileDob = profileDob;
        this.profileVehicleMake = profileVehicleMake;
        this.profileVehicleModel = profileVehicleModel;
        this.profileFuelType = profileFuelType;
        this.profileLicensePlate = profileLicensePlate;
        this.profileInsuranceNumber = profileInsuranceNumber;
        this.getProfileInsuranceExpiryDate = getProfileInsuranceExpiryDate;
        this.distanceIn = distanceIn;
    }

    public DriverDetails(String profileName, String profileDob, String profileVehicleMake,
                         String profileVehicleModel, String profileFuelType, String profileLicensePlate,
                         String profileInsuranceNumber, String getProfileInsuranceExpiryDate, String distanceIn) {
        this.profileName = profileName;
        this.profileDob = profileDob;
        this.profileVehicleMake = profileVehicleMake;
        this.profileVehicleModel = profileVehicleModel;
        this.profileFuelType = profileFuelType;
        this.profileLicensePlate = profileLicensePlate;
        this.profileInsuranceNumber = profileInsuranceNumber;
        this.getProfileInsuranceExpiryDate = getProfileInsuranceExpiryDate;
        this.distanceIn = distanceIn;
    }

    public DriverDetails(String profileName) {
        this.profileName = profileName;
    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getProfileName() {
        return profileName;
    }

    public void setProfileName(String profileName) {
        this.profileName = profileName;
    }

    public String getProfileDob() {
        return profileDob;
    }

    public void setProfileDob(String profileDob) {
        this.profileDob = profileDob;
    }

    public String getProfileVehicleMake() {
        return profileVehicleMake;
    }

    public void setProfileVehicleMake(String profileVehicleMake) {
        this.profileVehicleMake = profileVehicleMake;
    }

    public String getProfileVehicleModel() {
        return profileVehicleModel;
    }

    public void setProfileVehicleModel(String profileVehicleModel) {
        this.profileVehicleModel = profileVehicleModel;
    }

    public String getProfileFuelType() {
        return profileFuelType;
    }

    public void setProfileFuelType(String profileFuelType) {
        this.profileFuelType = profileFuelType;
    }

    public String getProfileLicensePlate() {
        return profileLicensePlate;
    }

    public void setProfileLicensePlate(String profileLicensePlate) {
        this.profileLicensePlate = profileLicensePlate;
    }

    public String getProfileInsuranceNumber() {
        return profileInsuranceNumber;
    }

    public void setProfileInsuranceNumber(String profileInsuranceNumber) {
        this.profileInsuranceNumber = profileInsuranceNumber;
    }

    public String getGetProfileInsuranceExpiryDate() {
        return getProfileInsuranceExpiryDate;
    }

    public void setGetProfileInsuranceExpiryDate(String getProfileInsuranceExpiryDate) {
        this.getProfileInsuranceExpiryDate = getProfileInsuranceExpiryDate;
    }

    public String getDistanceIn() {
        return distanceIn;
    }

    public void setDistanceIn(String distanceIn) {
        this.distanceIn = distanceIn;
    }

    public List<VehicleDetails> getTripList() {
        return tripList;
    }

    public void setTripList(List<VehicleDetails> tripList) {
        this.tripList = tripList;
    }

    @Override
    public String toString() {
        return "DriverDetails{" +
                "id=" + id +
                ", profileName='" + profileName + '\'' +
                ", profileDob='" + profileDob + '\'' +
                ", profileVehicleMake='" + profileVehicleMake + '\'' +
                ", profileVehicleModel='" + profileVehicleModel + '\'' +
                ", profileFuelType='" + profileFuelType + '\'' +
                ", profileLicensePlate='" + profileLicensePlate + '\'' +
                ", profileInsuranceNumber='" + profileInsuranceNumber + '\'' +
                ", getProfileInsuranceExpiryDate='" + getProfileInsuranceExpiryDate + '\'' +
                ", distanceIn='" + distanceIn + '\'' +
                ", tripList=" + tripList +
                '}';
    }

}
