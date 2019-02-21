package com.example.abishekg.mileagecalculator.Fragments;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.example.abishekg.mileagecalculator.Activities.EditActivity;
import com.example.abishekg.mileagecalculator.Adapters.ProfileAdapter;
import com.example.abishekg.mileagecalculator.Models.DriverDetails;
import com.example.abishekg.mileagecalculator.R;
import com.example.abishekg.mileagecalculator.Utilities.ApplicationConstants;
import com.example.abishekg.mileagecalculator.Utilities.DBHelper;
import com.example.abishekg.mileagecalculator.Utilities.OnDetailsClickListener;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */

public class ProfileFragment extends Fragment implements OnDetailsClickListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER


    public static List<DriverDetails> driverDetailsArrayList = new ArrayList<>();
    RecyclerView recyclerViewVehicleDetails;
    OnDetailsClickListener OnDetailsClickListener;
    TextView profileNameText2, licensePlate, avgMileage,makeModel, odoMeter;
    Button editProfileButton2;
    Context profileContext;
    LinearLayoutManager profileLinearLayoutManager;
    int profileId;
    String profileName;
    ProfileAdapter profileAdapter;
    DBHelper dbHelper;
    Button editProfileButton;
    int numberOfButtons;



    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create numberOfButtons new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            profileId = getArguments().getInt(ApplicationConstants.id);
            profileName = getArguments().getString(ApplicationConstants.profileName);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_details, container, false);

        // Inflate the layout for this fragment
        dbHelper = new DBHelper(getContext());
        final String profileName = getArguments().getString("profileName");
        driverDetailsArrayList = dbHelper.getDetails(profileName);
        for (DriverDetails dd : driverDetailsArrayList){
            dd.setTripList(dbHelper.getTripDetails(dd.getId()));
        }
        profileNameText2 = (TextView) view.findViewById(R.id.profileNameText2);
        profileNameText2.setText(profileName);
        editProfileButton = (Button) view.findViewById(R.id.editProfileButton2);
        editProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(getActivity());
                dialog.setContentView(R.layout.alert_layout);
                LinearLayout ll = (LinearLayout) dialog.findViewById(R.id.linearLayout_alertdialog);
                dialog.setTitle("Select the Vehicle!");
                for(numberOfButtons = 0; numberOfButtons < driverDetailsArrayList.size(); numberOfButtons++) {
                    final Button button1 = new Button(getContext());
                    button1.setText(driverDetailsArrayList.get(numberOfButtons).getProfileLicensePlate());
                    button1.setId(numberOfButtons +1);
                    ll.addView(button1);
                    button1.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    button1.setTextColor(getResources().getColor(android.R.color.holo_blue_dark));
                    button1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getActivity(), EditActivity.class);
                            intent.putExtra("profileName", driverDetailsArrayList.get((v.getId())-1).getProfileLicensePlate());
                            startActivity(intent);
                            dialog.hide();
                        }
                    });
                }
                dialog.setCancelable(true);
                dialog.show();
            }
        });
        profileLinearLayoutManager = new LinearLayoutManager(profileContext, LinearLayoutManager.VERTICAL, false);
        recyclerViewVehicleDetails = (RecyclerView) view.findViewById(R.id.recylerViewVehicleDetails);
        recyclerViewVehicleDetails.setLayoutManager(profileLinearLayoutManager);
        profileAdapter = new ProfileAdapter(driverDetailsArrayList, OnDetailsClickListener, getContext());
        recyclerViewVehicleDetails.setAdapter(profileAdapter);
        return view;


    }





    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnDetailsClickListener) {
            OnDetailsClickListener = (OnDetailsClickListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement onDetailsClickListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void DetailsClick(int position, int id, DriverDetails driverDetails) {

    }
}