package com.example.abishekg.mileagecalculator.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.abishekg.mileagecalculator.Activities.DetailsActivity;
import com.example.abishekg.mileagecalculator.R;

/**
 * Created by abishek.g on 1/18/2018.
 */

public class SplashActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    Boolean b;


    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        sharedPreferences = this.getSharedPreferences("ActivityLoader" , MODE_PRIVATE);
        b= sharedPreferences.getBoolean("flag", false);

            if(b==true) {
                Intent mainIntent = new Intent(this,ProfileActivity.class);
                startActivity(mainIntent);
                finish();
            }
                else {
                Intent mainIntent = new Intent(this, DetailsActivity.class);
                startActivity(mainIntent);
                finish();
            }
    }
}
