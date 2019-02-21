package com.example.abishekg.mileagecalculator.Utilities;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.abishekg.mileagecalculator.Models.DriverDetails;
import com.example.abishekg.mileagecalculator.Models.VehicleDetails;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by abishek.g on 1/11/2018.
 */

public class DBHelper extends SQLiteOpenHelper {

    SQLiteDatabase sd;
    public DBHelper(Context context) {

        super(context, "androidsqlite.db", null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        sd=db;
        createDB();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        sd=db;
        dropDB();
        createDB();
    }
    public  void createDB() {
        //sd = this.getWritableDatabase();
        sd.execSQL("CREATE TABLE IF NOT EXISTS " + ApplicationConstants.tableName+ "(" + ApplicationConstants.id + " INTEGER PRIMARY KEY AUTOINCREMENT,"+ApplicationConstants.profileName+" VARCHAR(15) NOT NULL," + ApplicationConstants.profileDob +" VARCHAR(15)," +ApplicationConstants.vehicleMake + " VARCHAR(15) NOT NULL,"+ApplicationConstants.vehicleModel + " VARCHAR(15) NOT NULL,"+ApplicationConstants.fuelType + " VARCHAR(15) NOT NULL,"+ApplicationConstants.licensePlate + " VARCHAR(15) NOT NULL,"+ApplicationConstants.insuranceNumber + " VARCHAR(15),"+ApplicationConstants.insuranceExpiry + " VARCHAR(15),"+ApplicationConstants.distanceIn + " VARCHAR(15) NOT NULL);");
        sd.execSQL("CREATE TABLE IF NOT EXISTS " + ApplicationConstants.tableName2+ "(" + ApplicationConstants.ID2 + " INTEGER references " +ApplicationConstants.tableName+"("+ApplicationConstants.id +"),"+ApplicationConstants.tripCount + " INTEGER PRIMARY KEY AUTOINCREMENT,"+ApplicationConstants.currentMileage + " FLOAT NOT NULL,"+ApplicationConstants.fuelQuantity + " FLOAT NOT NULL,"+ApplicationConstants.odometerReading + " FLOAT NOT NULL);");
    }

    public void dropDB() {
        sd.execSQL("DROP TABLE "+ApplicationConstants.tableName +";");
        sd.execSQL("DROP TABLE "+ApplicationConstants.tableName2 + ";");
    }

    public List<String> getProfileNames() {

        List<String> profileNames = new ArrayList<>();

        try{
            String query="SELECT DISTINCT "+ApplicationConstants.profileName+" FROM " + ApplicationConstants.tableName +";";
            sd = getWritableDatabase();
            Cursor c = sd.rawQuery(query,null);
            if(c.moveToFirst()) {
                do {
                    profileNames.add(c.getString(0));
                }
                while(c.moveToNext());
            }c.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sd.close();
        }
        return profileNames;
    }

    public DriverDetails ModelDB(DriverDetails driverDetails) {
        DriverDetails result = null;
        ContentValues contentValues = new ContentValues();

        try {
            contentValues.put(ApplicationConstants.profileName, driverDetails.getProfileName());
            contentValues.put(ApplicationConstants.profileDob, String.valueOf(driverDetails.getProfileDob()));
            contentValues.put(ApplicationConstants.vehicleMake, driverDetails.getProfileVehicleMake());
            contentValues.put(ApplicationConstants.vehicleModel, driverDetails.getProfileVehicleModel());
            contentValues.put(ApplicationConstants.fuelType, driverDetails.getProfileFuelType());
            contentValues.put(ApplicationConstants.licensePlate, driverDetails.getProfileLicensePlate());
            contentValues.put(ApplicationConstants.insuranceNumber, driverDetails.getProfileInsuranceNumber());
            contentValues.put(ApplicationConstants.insuranceExpiry, String.valueOf(driverDetails.getGetProfileInsuranceExpiryDate()));
            contentValues.put(ApplicationConstants.distanceIn, driverDetails.getDistanceIn());
            sd = getWritableDatabase();
            if (sd.insert(ApplicationConstants.tableName, ApplicationConstants.id, contentValues) != -1) {
                result = driverDetails;
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            sd.close();
        }

        return result;
    }





    public List<DriverDetails> getDetails(String profileName) {
        List<DriverDetails> result = new ArrayList<>();
        try {
            sd = this.getReadableDatabase();
            String query = "SELECT * FROM " + ApplicationConstants.tableName + " WHERE " + ApplicationConstants.profileName + " = '" + profileName+ "'";
            Cursor c = sd.rawQuery(query, null);
            if (c.moveToFirst()) {
                do {
                    result.add(new DriverDetails(
                            c.getInt(0),
                            c.getString(1),
                            c.getString(2),
                            c.getString(3),
                            c.getString(4),
                            c.getString(5),
                            c.getString(6),
                            c.getString(7),
                            c.getString(8),
                            c.getString(9)
                    ));
                }
                while (c.moveToNext());
            }
            c.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sd.close();
        }
        return result;
    }

    public VehicleDetails tripModelDB(VehicleDetails vehicleDetails){
        VehicleDetails result = null;
        ContentValues contentValues = new ContentValues();
        try {
            contentValues.put(ApplicationConstants.ID2, vehicleDetails.getVehicleId());
            contentValues.put(ApplicationConstants.currentMileage, vehicleDetails.getCurrrentMileage());
            contentValues.put(ApplicationConstants.fuelQuantity, vehicleDetails.getFuelQuantity());
            contentValues.put(ApplicationConstants.odometerReading, vehicleDetails.getOdometerReading());
            sd = getWritableDatabase();
            if(sd.insert(ApplicationConstants.tableName2, ApplicationConstants.tripCount, contentValues)!=-1){
                result= vehicleDetails;
            }
        } catch(Exception e){
            e.printStackTrace();
        }
        finally {
            sd.close();
        }
        return result;
    }

    public List<VehicleDetails> getTripDetails(int id){
        List<VehicleDetails> result = new ArrayList<>();
        try {
            sd = this.getReadableDatabase();
            String query = "SELECT * FROM " + ApplicationConstants.tableName2 + " WHERE " + ApplicationConstants.ID2 + " = " + id;
            Cursor c = sd.rawQuery(query, null);
            if (c.moveToFirst()) {
                do {
                    result.add(new VehicleDetails(
                            c.getInt(0),
                            c.getInt(1),
                            c.getFloat(2),
                            c.getFloat(3),
                            c.getFloat(4)
                    ));
                }
                while (c.moveToNext());
            }
            c.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sd.close();
        }
        return result;
    }

    public int getTripCount(int id){
        int result=0;
        try {
            sd = this.getReadableDatabase();
            String query = "SELECT COUNT("+ApplicationConstants.ID2+") FROM " + ApplicationConstants.tableName2 + " WHERE " + ApplicationConstants.ID2 + " = " + id;
            Cursor c = sd.rawQuery(query, null);
            while (c.moveToNext()){
                result++;
            }
            c.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sd.close();
        }
        return result;
    }

    public VehicleDetails getPreviousTripDetails(int id){
        VehicleDetails vehicleDetails = null;
        try {
            sd = this.getReadableDatabase();
            String query = "SELECT " + ApplicationConstants.fuelQuantity + ", " + ApplicationConstants.odometerReading + ", " +
                    ApplicationConstants.currentMileage + " FROM " + ApplicationConstants.tableName2 + " WHERE "+
                    ApplicationConstants.tripCount +" = (SELECT MAX(" + ApplicationConstants.tripCount +") FROM " +
                    ApplicationConstants.tableName2 +" WHERE "+ApplicationConstants.ID2 +"=" + id+")";
            Cursor c = sd.rawQuery(query, null);
            if (c.moveToFirst()) {
                vehicleDetails = new VehicleDetails(
                        c.getFloat(2),
                        c.getFloat(0),
                        c.getFloat(1)
                );
            }
            c.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sd.close();
        }
        return vehicleDetails;
    }

    public DriverDetails getProfileDetails(String profileName) {
        DriverDetails driverDetails = null;
        try{
            sd=this.getReadableDatabase();
            String query = "SELECT * FROM " + ApplicationConstants.tableName + " WHERE " + ApplicationConstants.licensePlate + " = '" + profileName+ "'";
            Cursor c = sd.rawQuery(query, null);
            if(c.moveToFirst()) {
                driverDetails = new DriverDetails(
                        c.getInt(0),
                        c.getString(1),
                        c.getString(2),
                        c.getString(3),
                        c.getString(4),
                        c.getString(5),
                        c.getString(6),
                        c.getString(7),
                        c.getString(8),
                        c.getString(9)
                );
            }c.close();
        }catch(Exception e){
            e.printStackTrace();
        } finally {
            sd.close();
        }
        return driverDetails;
    }


    public void updateDetails(String columnName, String columnValue, Integer id) {
        ContentValues contentValues = new ContentValues();

        try {
            contentValues.put(columnName, columnValue);
            sd = getWritableDatabase();
            sd.update(ApplicationConstants.tableName, contentValues, ApplicationConstants.id + "=" + id, null);
        }catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            sd.close();
        }
    }

    public float currentMileageSum(int id) {
        float a = 0f;
        try {
            sd = this.getReadableDatabase();
            String query = "SELECT SUM(" + ApplicationConstants.currentMileage + ")/(count(*)-1) FROM " + ApplicationConstants.tableName2 + " WHERE " + ApplicationConstants.ID2 + " = " + id;
            Cursor c = sd.rawQuery(query, null);
            if (c.moveToFirst()) {
                a = c.getFloat(0);
            }
            c.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally {
            sd.close();
        }

        return a;
    }


}