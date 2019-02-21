package com.example.abishekg.mileagecalculator.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.abishekg.mileagecalculator.Models.VehicleDetails;
import com.example.abishekg.mileagecalculator.R;
import com.example.abishekg.mileagecalculator.Utilities.DBHelper;

public class ReadingActivity extends AppCompatActivity {

    EditText odometerReading,fuelRefill;
    Intent intent;
    int id;
    DBHelper dbHelper;
    float odometer,fuel;
    VehicleDetails vehicleDetails;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reading);
        odometerReading = (EditText) findViewById(R.id.odometerReadingEdit4);
        fuelRefill = (EditText) findViewById(R.id.fuelRefillEdit4);
        dbHelper = new DBHelper(this);

    }
    public void calculateClick(View view) {
        if(fuelRefill.getText().toString().isEmpty() || odometerReading.getText().toString().isEmpty()) {
            Toast.makeText(this, "Cannot leave fields Empty", Toast.LENGTH_SHORT).show();
        }
        else{
            if(Float.valueOf(fuelRefill.getText().toString()) == 0){
                Toast.makeText(this, "Cant be zero", Toast.LENGTH_SHORT).show();
            }
            else {
                fuel = Float.valueOf(fuelRefill.getText().toString());
                odometer = Float.valueOf(odometerReading.getText().toString());
                intent = getIntent();
                Bundle b = new Bundle();
                b = intent.getExtras();
                id = b.getInt("id");
                vehicleDetails = new VehicleDetails(id, currentMileage(odometer, id), fuel, odometer);
                dbHelper.tripModelDB(vehicleDetails);
                setResult(RESULT_OK, intent);
                finish();
            }
        }
    }

    public void cancelClick(View view){
        intent = new Intent();
        setResult(RESULT_CANCELED,intent);
        finish();
    }

    public float currentMileage(float odoMeterReading, int id){
        VehicleDetails previousVehicleDetails = dbHelper.getPreviousTripDetails(id);
        if (previousVehicleDetails == null) {
            previousVehicleDetails = new VehicleDetails(0f,0f,0f);
        }
        float currentMileage=0;
        try {
            if(previousVehicleDetails.getFuelQuantity() != 0f)
                currentMileage = (odoMeterReading - previousVehicleDetails.getOdometerReading()) / previousVehicleDetails.getFuelQuantity();
            else
                currentMileage = 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return currentMileage;

    }

}