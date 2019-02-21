package com.example.abishekg.mileagecalculator.Activities;


import android.content.Context;
        import android.content.Intent;
        import android.content.SharedPreferences;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.support.v7.widget.LinearLayoutManager;
        import android.support.v7.widget.RecyclerView;
        import android.support.v7.widget.Toolbar;
        import android.view.View;
        import android.widget.TextView;
        import android.widget.Toast;

import com.example.abishekg.mileagecalculator.Adapters.ProfileDetailsAdapter;
import com.example.abishekg.mileagecalculator.Models.DriverDetails;
import com.example.abishekg.mileagecalculator.Models.VehicleDetails;
import com.example.abishekg.mileagecalculator.R;
import com.example.abishekg.mileagecalculator.Utilities.DBHelper;

import java.util.ArrayList;
        import java.util.List;



public class ProfileDetailsActivity extends AppCompatActivity {

    private DriverDetails driverDetails;
    private static final int requestCode = 0;
    TextView vehicleMake, vehicleModel,licensePlate, insuranceNumber,insuranceExpiry;
    RecyclerView recyclerView;
    List<DriverDetails> driverDetailsList;

    DBHelper dbHelper;
    Intent intent;
    Bundle bundle;
    DetailsActivity detailsActivity;
    Context vehicleContext;
    List<VehicleDetails> vehicleDetailsList;
    VehicleDetails vehicleDetails;
    ProfileDetailsAdapter profileDetailsAdapter;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    int tripLimit = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_details);
        driverDetailsList = new ArrayList<>();
        dbHelper = new DBHelper(getBaseContext());
        Toolbar myChildToolbar = (Toolbar) findViewById(R.id.my_toolbar);


        detailsActivity = new DetailsActivity();

        intent = getIntent();
        bundle = intent.getExtras();
        driverDetails = (DriverDetails) bundle.getSerializable("driverDetails");

        vehicleMake = (TextView) findViewById(R.id.makeText3);
        licensePlate = (TextView) findViewById(R.id.licensePlateText3);
        insuranceNumber = (TextView) findViewById(R.id.insuranceText3);
        insuranceExpiry = (TextView) findViewById(R.id.insuranceExpiryText3);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView3);

        tripLimit = dbHelper.getTripCount(driverDetails.getId());

        vehicleDetailsList = dbHelper.getTripDetails(driverDetails.getId());
        profileDetailsAdapter = new ProfileDetailsAdapter(vehicleContext, vehicleDetailsList);

        vehicleMake.setText(driverDetails.getProfileVehicleMake()+" " + driverDetails.getProfileVehicleModel());
        licensePlate.setText(driverDetails.getProfileLicensePlate());
        insuranceNumber.setText(driverDetails.getProfileInsuranceNumber());
        insuranceExpiry.setText(driverDetails.getGetProfileInsuranceExpiryDate());

        myChildToolbar.setTitle(driverDetails.getProfileName());
        setSupportActionBar(myChildToolbar);
//        recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
//            @Override
//            public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
//                super.onDraw(c, parent, state);
//            }
//        });
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(profileDetailsAdapter);


    }
    public void addTripImageClick(View view) {
        Intent intent = new Intent(ProfileDetailsActivity.this, ReadingActivity.class);
        Bundle b= new Bundle();
        b.putInt("id",driverDetails.getId());
        intent.putExtras(b);
        startActivityForResult(intent,requestCode);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if(resultCode == RESULT_CANCELED){
            Toast.makeText(this, "You cancelled the Trip Entry", Toast.LENGTH_SHORT).show();
        }
        else if(resultCode == RESULT_OK) {
            vehicleDetailsList = dbHelper.getTripDetails(driverDetails.getId());
            profileDetailsAdapter = new ProfileDetailsAdapter(vehicleContext, vehicleDetailsList);
            recyclerView.setAdapter(profileDetailsAdapter);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    public void backPress(View view){
        onBackPressed();
        finish();
    }
}