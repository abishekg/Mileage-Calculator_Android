package com.example.abishekg.mileagecalculator.Activities;

import android.app.DatePickerDialog;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.abishekg.mileagecalculator.Models.DriverDetails;
import com.example.abishekg.mileagecalculator.R;
import com.example.abishekg.mileagecalculator.Utilities.ApplicationConstants;
import com.example.abishekg.mileagecalculator.Utilities.DBHelper;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class EditActivity extends AppCompatActivity {


    Intent intent;
    String profileName;
    DetailsActivity detailsActivity;
    TextView makeText, modelText, licensePlate, insuranceNumber, insuranceExpiryDate, toolbarProfileName;
    DriverDetails driverDetails;
    DBHelper dbHelper;
    Calendar calendar = Calendar.getInstance();
    String myFormat ="dd/MM/yy";
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(myFormat);
    DatePickerDialog.OnDateSetListener onDateSetListener;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    int flag1 = 0;

    Button licensePlateEditButton, insuranceNumberEditButton,insuranceNumberDeleteButton, insuranceExpiryEditButton,insuranceExpiryDeleteButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        dbHelper = new DBHelper(this);
        intent = getIntent();
        profileName = intent.getStringExtra("profileName");
        Toast.makeText(this, profileName, Toast.LENGTH_SHORT).show();
        toolbarProfileName = (TextView) findViewById(R.id.toolbarProfileName);
        driverDetails = dbHelper.getProfileDetails(profileName);
        makeText = (TextView) findViewById(R.id.makeText5);
        modelText = (TextView) findViewById(R.id.modelText5);
        licensePlate = (TextView) findViewById(R.id.licensePlateText5);
        insuranceNumber = (TextView) findViewById(R.id.insuranceNumberText5);
        insuranceExpiryDate = (TextView) findViewById(R.id.insuranceExpiryText5);
        sharedPreferences = this.getSharedPreferences("ActivityLoader" , MODE_PRIVATE);
        editor = sharedPreferences.edit();


        toolbarProfileName.setText(driverDetails.getProfileName());
        makeText.setText(driverDetails.getProfileVehicleMake());
        modelText.setText(driverDetails.getProfileVehicleModel());
        licensePlate.setText(driverDetails.getProfileLicensePlate());
        insuranceNumber.setText(driverDetails.getProfileInsuranceNumber());
        insuranceExpiryDate.setText(driverDetails.getGetProfileInsuranceExpiryDate());

        licensePlateEditButton = (Button) findViewById(R.id.licensePlateEditButton5);
        insuranceExpiryEditButton = (Button) findViewById(R.id.insuranceExpiryEditButton5);
        insuranceExpiryDeleteButton = (Button) findViewById(R.id.insuranceExpiryDeleteButton5);
        insuranceNumberEditButton = (Button) findViewById(R.id.insuranceNumberEditButton5);
        insuranceNumberDeleteButton = (Button) findViewById(R.id.insuranceNumberDeleteButton5);

        if(driverDetails.getProfileInsuranceNumber() == "" || driverDetails.getProfileInsuranceNumber() == null){
            insuranceNumber.setText("Insurance Number");
            insuranceNumberDeleteButton.setVisibility(View.INVISIBLE);
            insuranceNumberEditButton.setText("Enter");
        }else{
            insuranceNumber.setText(driverDetails.getProfileInsuranceNumber());
            insuranceNumberEditButton.setText("edit");
            insuranceNumberDeleteButton.setVisibility(View.VISIBLE);
        }

        if(driverDetails.getGetProfileInsuranceExpiryDate()== "" || driverDetails.getProfileInsuranceNumber() == null){
            insuranceExpiryDate.setText("Insurance Expiry Date");
            insuranceExpiryDeleteButton.setVisibility(View.GONE);
            insuranceExpiryEditButton.setText("Enter");
        }else {
            insuranceExpiryDate.setText(driverDetails.getGetProfileInsuranceExpiryDate());
            insuranceExpiryEditButton.setText("edit");
            insuranceExpiryDeleteButton.setVisibility(View.VISIBLE);
        }

    }
    public void addAnotherClick5 (View view) {
        Intent intent = new Intent(this, DetailsActivity.class);
        intent.putExtra("profName", driverDetails.getProfileName());
        intent.putExtra("profDob", driverDetails.getProfileDob());
        editor.putBoolean("flag", true);
        startActivity(intent);
    }

    public void licensePlateButtonClick(final View view) {
        final AlertDialog.Builder builder;
        final LayoutInflater inflater = getLayoutInflater();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(EditActivity.this, android.R.style.Theme_Material_Dialog_Alert);
        } else {
            builder = new AlertDialog.Builder(EditActivity.this);
        }
        builder.setTitle("Edit License Plate")
                .setMessage("Enter the new Edit License Plate?");
        final EditText texe= new EditText(EditActivity.this);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        texe.setLayoutParams(lp);
        builder.setView(texe);


        builder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
            String editname;
            public void onClick(DialogInterface dialog, int which) {
                editname = texe.getText().toString();
                licensePlate.setText(editname);
                dbHelper.updateDetails(ApplicationConstants.licensePlate, editname, driverDetails.getId());

            }

        })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(EditActivity.this, "pressed negative", Toast.LENGTH_SHORT).show();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();

    }

    public void insuranceNumberEditButton(View view){
        final AlertDialog.Builder builder;
        final LayoutInflater inflater = getLayoutInflater();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(EditActivity.this, android.R.style.Theme_Material_Dialog_Alert);
        } else {
            builder = new AlertDialog.Builder(EditActivity.this);
        }
        builder.setTitle("Edit Insurance Number")
                .setMessage("Enter the new Insurance Number?");
        final EditText texe= new EditText(EditActivity.this);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        texe.setLayoutParams(lp);
        texe.setTextColor(Color.parseColor("#FFFFFF"));
        builder.setView(texe);

        builder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
            String editname;
            public void onClick(DialogInterface dialog, int which) {
                editname = texe.getText().toString();
                insuranceNumber.setText(editname);
                dbHelper.updateDetails(ApplicationConstants.insuranceNumber, editname, driverDetails.getId());
                insuranceNumberEditButton.setText("edit");
                insuranceNumberDeleteButton.setVisibility(View.VISIBLE);

            }

        })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(EditActivity.this, "pressed negative", Toast.LENGTH_SHORT).show();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    public void insuranceNumberDeleteButton(View view) {
        AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(this, android.R.style.Theme_Material_Dialog_Alert);
        } else {
            builder = new AlertDialog.Builder(this);
        }
        builder.setTitle("Delete Insurnace Number")
                .setMessage("Are you sure you want to delete?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dbHelper.updateDetails(ApplicationConstants.insuranceNumber, null, driverDetails.getId());
                        insuranceNumber.setText("Insurance Number");
                        insuranceNumberDeleteButton.setVisibility(View.INVISIBLE);
                        insuranceNumberEditButton.setText("Enter");

                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(EditActivity.this, "Cancelled Delete", Toast.LENGTH_SHORT).show();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();

    }


    public void insuranceExpiryEditButton(View view) {
        final AlertDialog.Builder builder;
        final LayoutInflater inflater = getLayoutInflater();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(EditActivity.this, android.R.style.Theme_Material_Dialog_Alert);
        } else {
            builder = new AlertDialog.Builder(EditActivity.this);
        }
        builder.setTitle("Edit Insurance Expiry Date")
                .setMessage("Select the New Expiry Date?");
        onDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH,month);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                insuranceExpiryDate.setText(simpleDateFormat.format(calendar.getTime()));
                dbHelper.updateDetails(ApplicationConstants.insuranceExpiry, simpleDateFormat.format(calendar.getTime()), driverDetails.getId());
            }
        };

        builder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
            String editName;
            public void onClick(DialogInterface dialog, int which) {
                new DatePickerDialog(EditActivity.this, onDateSetListener, calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH)).show();
                editName = simpleDateFormat.format(calendar.getTime());
                insuranceExpiryDate.setText(editName);
                dbHelper.updateDetails(ApplicationConstants.insuranceExpiry, editName, driverDetails.getId());
                insuranceExpiryEditButton.setText("edit");
                insuranceExpiryDeleteButton.setVisibility(View.VISIBLE);
            }

        })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(EditActivity.this, "pressed negative", Toast.LENGTH_SHORT).show();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();    }

    public void insuranceExpiryDeleteButton(View view) {
        Toast.makeText(this, "Insunrace Expiry Date Delete", Toast.LENGTH_SHORT).show();
        AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(this, android.R.style.Theme_Material_Dialog_Alert);
        } else {
            builder = new AlertDialog.Builder(this);
        }
        builder.setTitle("Delete Insurance Expiry Date")
                .setMessage("Are you sure you want to delete?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dbHelper.updateDetails(ApplicationConstants.insuranceExpiry, null, driverDetails.getId());
                        insuranceExpiryDate.setText("Insurance Expiry Date");
                        insuranceExpiryDeleteButton.setVisibility(View.GONE);
                        insuranceExpiryEditButton.setText("Enter");
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(EditActivity.this, "Cancelled Delete", Toast.LENGTH_SHORT).show();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();

    }
}