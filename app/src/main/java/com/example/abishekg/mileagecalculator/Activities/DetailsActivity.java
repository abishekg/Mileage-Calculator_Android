package com.example.abishekg.mileagecalculator.Activities;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.abishekg.mileagecalculator.Models.DriverDetails;
import com.example.abishekg.mileagecalculator.R;
import com.example.abishekg.mileagecalculator.Utilities.DBHelper;

import java.sql.Driver;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class DetailsActivity extends AppCompatActivity {

    EditText nameEdit,dobEdit, vMakeEdit, vModelEdit, licensePlateEdit, insNumEdit, insExpEdit;
    String fuelType = "petrol",distanceIn="km",profileName,profileDOB, vehicleMake,vehicleModel,licensePlate, insuranceNumber,insuranceExpiryDate;
    RadioGroup fuelTypeRadioGroup,distanceRadioGroup;
    Button resetButton, submitButton, addAnotherButton;
    RadioButton petrolRadioButton, kmRadioButton;
    DBHelper dbHelper;
    Calendar calendar = Calendar.getInstance();
    Calendar calendar1 = Calendar.getInstance();
    ArrayList<DriverDetails> driverDetailsArrayList;
    String myFormat ="dd/MM/yy",myFormat1 = "dd/MM/yy";
    int ch = 0;
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(myFormat);
    SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat(myFormat1);
    DatePickerDialog.OnDateSetListener onDateSetListener,onDateSetListener1;
    Boolean flag1;


    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    public void resetClick(View view) {
        nameEdit.setText(null);
        dobEdit.setText(null);
        vMakeEdit.setText(null);
        vModelEdit.setText(null);
        licensePlateEdit.setText(null);
        insExpEdit.setText(null);
        insNumEdit.setText(null);
        petrolRadioButton.setChecked(true);
        fuelType ="petrol";
        kmRadioButton.setChecked(true);
        distanceIn = "km";
    }


    public void submitClick(View view) {
        if(nameEdit.getText().toString().isEmpty() || vMakeEdit.getText().toString().isEmpty() || vModelEdit.getText().toString().isEmpty() ||licensePlateEdit.getText().toString().isEmpty()){
            Toast.makeText(this, "Cant leave fields Empty", Toast.LENGTH_LONG).show();
        }
        else {
            DriverDetails driverDetails = new DriverDetails(nameEdit.getText().toString(),dobEdit.getText().toString(),vMakeEdit.getText().toString(),vModelEdit.getText().toString(),fuelType,licensePlateEdit.getText().toString(),insNumEdit.getText().toString(),insExpEdit.getText().toString(),distanceIn);
            DBHelper dbHelper = new DBHelper(getBaseContext());
            dbHelper.ModelDB(driverDetails);
            Intent intent = new Intent(this, ProfileActivity.class);
            Bundle bundle = new Bundle();
            bundle.putString("profileName", nameEdit.getText().toString());
            intent.putExtras(bundle);
            startActivity(intent);
            flag1=true;
            editor.putBoolean("flag",true);
            editor.commit();
            finish();
        }
    }

    public void addAnotherClick(View view) {
        DriverDetails driverDetails = new DriverDetails(nameEdit.getText().toString(),dobEdit.getText().toString(),vMakeEdit.getText().toString(),vModelEdit.getText().toString(),fuelType,licensePlateEdit.getText().toString(),insNumEdit.getText().toString(),insExpEdit.getText().toString(),distanceIn);
        DBHelper dbHelper = new DBHelper(getBaseContext());
        dbHelper.ModelDB(driverDetails);
        nameEdit.setEnabled(false);
        dobEdit.setEnabled(false);
        vMakeEdit.setText(null);
        vModelEdit.setText(null);
        licensePlateEdit.setText(null);
        insExpEdit.setText(null);
        insNumEdit.setText(null);
        petrolRadioButton.setChecked(true);
        fuelType = "petrol";
        kmRadioButton.setChecked(true);
        distanceIn = "km";
        editor.putBoolean("flag",true);
        editor.commit();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        sharedPreferences = this.getSharedPreferences("ActivityLoader", MODE_PRIVATE);
        editor = sharedPreferences.edit();



        dbHelper=new DBHelper(this);
        driverDetailsArrayList = new ArrayList<DriverDetails>();
        nameEdit = (EditText) findViewById(R.id.nameEdit);
        dobEdit= (EditText) findViewById(R.id.dobEdit);
        vMakeEdit = (EditText) findViewById(R.id.vMakeEdit);
        vModelEdit = (EditText) findViewById(R.id.vModelEdit);
        licensePlateEdit = (EditText) findViewById(R.id.licensePlateEdit);
        insNumEdit = (EditText) findViewById(R.id.insNumEdit);
        insExpEdit = (EditText) findViewById(R.id.insExpEdit);

        petrolRadioButton = (RadioButton) findViewById(R.id.petrolCheckBox);
        kmRadioButton = (RadioButton) findViewById(R.id.kmRadioButton);

        resetButton = (Button) findViewById(R.id.resetButton);
//        submitButton = (Button) findViewById(R.id.submitButton);
        addAnotherButton = (Button) findViewById(R.id.addAnotherButton);



        fuelTypeRadioGroup = (RadioGroup) findViewById(R.id.fuelTypeRadioGroup);
        distanceRadioGroup = (RadioGroup) findViewById(R.id.distanceRadioGroup);
        fuelTypeRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId) {
                    case R.id.petrolCheckBox:
                        fuelType="petrol";
                        break;
                    case R.id.dieselCheckBox:
                        fuelType="diesel";
                        break;
                }

            }
        });
        distanceRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId){
                    case R.id.kmRadioButton:
                        distanceIn = "km";
                        break;
                    case R.id.milesRadioButton:
                        distanceIn="miles";
                        break;
                }
            }
        });

        dobEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(DetailsActivity.this, onDateSetListener, calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH)).show();
                dobEdit.setText(simpleDateFormat.format(calendar.getTime()));
            }
        });


        onDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH,month);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                dobEdit.setText(simpleDateFormat.format(calendar.getTime()));
            }
        };


        insExpEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(DetailsActivity.this, onDateSetListener1, calendar1.get(Calendar.YEAR),calendar1.get(Calendar.MONTH),calendar1.get(Calendar.DAY_OF_MONTH)).show();
                insExpEdit.setText(simpleDateFormat1.format(calendar1.getTime()));
            }
        });
        onDateSetListener1 = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar1.set(Calendar.YEAR, year);
                calendar1.set(Calendar.MONTH,month);
                calendar1.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                insExpEdit.setText(simpleDateFormat1.format(calendar1.getTime()));
            }
        };

        if(sharedPreferences.getBoolean("flag",false) == true) {
            Intent intent = getIntent();
            String profName = intent.getStringExtra("profName");
            String profDob = intent.getStringExtra("profDob");
            addAnotherVehicle(profName, profDob);
        }


//        DriverDetails driverDetailsEdit = (DriverDetails) getIntent().getSerializableExtra("driverDetails");

    }

    public void addAnotherVehicle(String profName, String profDob) {
        nameEdit.setText(profName);
        dobEdit.setText(profDob);
        vMakeEdit.setText(null);
        vModelEdit.setText(null);
        licensePlateEdit.setText(null);
        insExpEdit.setText(null);
        insNumEdit.setText(null);
        petrolRadioButton.setChecked(true);
        fuelType = "petrol";
        kmRadioButton.setChecked(true);
        distanceIn = "km";
    }
}