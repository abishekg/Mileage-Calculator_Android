
package com.example.abishekg.mileagecalculator.Activities;


import android.content.Intent;
        import android.support.v4.app.Fragment;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.support.v7.widget.LinearLayoutManager;
        import android.view.View;
        import android.widget.FrameLayout;
        import android.widget.LinearLayout;
        import android.widget.TextView;


import com.example.abishekg.mileagecalculator.Fragments.ProfileFragment;
import com.example.abishekg.mileagecalculator.Models.DriverDetails;
import com.example.abishekg.mileagecalculator.R;
import com.example.abishekg.mileagecalculator.Utilities.DBHelper;
import com.example.abishekg.mileagecalculator.Utilities.OnDetailsClickListener;

import java.util.List;


public class ProfileActivity extends AppCompatActivity implements OnDetailsClickListener {

    DriverDetails driverDetails;
    LinearLayoutManager linearLayoutManager2;
    LinearLayout linearLayout;
    FrameLayout frameLayout;
    TextView profileNameText;
    Bundle bundle;
    DBHelper dbHelper;
    List<String> profileNames;
    LinearLayout.LayoutParams leftMarginParams;
    ProfileFragment profileFragment;
    Fragment fragment;
    android.support.v4.app.FragmentManager fragmentManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        android.support.v7.widget.Toolbar myChildToolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.profile_tooolbar);
        myChildToolbar.setTitle("Mileage Calculator");
        setSupportActionBar(myChildToolbar);

        dbHelper = new DBHelper(getBaseContext());
        profileNames = dbHelper.getProfileNames();
        for (int profileCount = 0; profileCount<profileNames.size();profileCount++) {
            leftMarginParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            bundle = new Bundle();
            profileNameText = (TextView) findViewById(R.id.profileNameText2);
            linearLayout = (LinearLayout) findViewById(R.id.linearLayout_profileactivity);
            fragmentManager = getSupportFragmentManager();
            frameLayout = new FrameLayout(this);
            frameLayout.setLayoutParams(leftMarginParams);
            profileFragment = new ProfileFragment();
            fragment = profileFragment;
            frameLayout.setId(profileCount+1);
            bundle.putString("profileName",profileNames.get(profileCount));
            fragment.setArguments(bundle);
            fragmentManager.beginTransaction().add(profileCount+1, profileFragment).commit();
            linearLayout.addView(frameLayout,leftMarginParams);
        }
    }

    @Override
    public void DetailsClick(int position, int id, DriverDetails driverDetails) {
        switch (id){
            case R.id.licensePlateText2:
            case  R.id.avgMileageText2:
            case R.id.makeModelText2:
            case R.id.odometerReading2:
                Intent intent =new Intent(this, ProfileDetailsActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("driverDetails", driverDetails);
                intent.putExtras(bundle);
                startActivity(intent);
        }
    }

    public void addNewProfileClick(View view) {
        Intent intent = new Intent(this, DetailsActivity.class);
        startActivity(intent);
    }


}