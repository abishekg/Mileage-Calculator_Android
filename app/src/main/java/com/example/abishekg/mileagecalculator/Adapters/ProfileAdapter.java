package com.example.abishekg.mileagecalculator.Adapters;

        import android.content.Context;
        import android.content.Intent;
        import android.os.Bundle;
        import android.support.v7.widget.RecyclerView;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.TextView;


        import com.example.abishekg.mileagecalculator.Models.DriverDetails;
        import com.example.abishekg.mileagecalculator.R;
        import com.example.abishekg.mileagecalculator.Utilities.DBHelper;
        import com.example.abishekg.mileagecalculator.Utilities.OnDetailsClickListener;

        import java.util.List;


public class ProfileAdapter extends RecyclerView.Adapter<ProfileAdapter.ViewHolder> {

    private final List<DriverDetails> driverDetailsList;
    String currentodometer;

    public ProfileAdapter(List<DriverDetails> driverDetailsList, OnDetailsClickListener onDetailsClickListener, Context profileContext) {
        this.driverDetailsList = driverDetailsList;
        this.onDetailsClickListener = onDetailsClickListener;
        this.profileContext = profileContext;
        dbHelper = new DBHelper(profileContext);
    }

    private final OnDetailsClickListener onDetailsClickListener;
    Context profileContext;
    DBHelper dbHelper;
    @Override
    public ProfileAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.p2_list_layout, parent, false);
        return new ProfileAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProfileAdapter.ViewHolder holder, final int i) {
        holder.txtView_profileadapter_licensePlateNumber.setText(driverDetailsList.get(i).getProfileLicensePlate());
        holder.txtView_profileadapter_vehicleMake.setText(driverDetailsList.get(i).getProfileVehicleMake() + " " + driverDetailsList.get(i).getProfileVehicleModel());;
        final DriverDetails driverDetails=driverDetailsList.get(i);
        driverDetails.getTripList();
        if(driverDetailsList.get(i).getTripList().size() == 0){
//            driverDetailsList.get(i).setTripList((List<VehicleDetails>) new VehicleDetails( 0f,0f,0f));
            holder.txtView_profileadapter_avgMileage.setText("Average Mileage:" +" -");
            holder.txtView_profileAdapter_odometerReading.setText("Odometer Reading:"+" -");
        }
        else {
            currentodometer = String.format("%.02f",dbHelper.currentMileageSum(driverDetailsList.get(i).getId()));
            holder.txtView_profileAdapter_odometerReading.setText(String.valueOf("Odometer Reading: "+driverDetailsList.get(i).getTripList().get(driverDetailsList.get(i).getTripList().size()-1).getOdometerReading())+" "+driverDetailsList.get(i).getDistanceIn());
            holder.txtView_profileadapter_avgMileage.setText("Average Mileage: " + currentodometer+" "+driverDetailsList.get(i).getDistanceIn()+"/l");
        }
        holder.txtView_profileadapter_licensePlateNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onDetailsClickListener.DetailsClick(i, v.getId(), driverDetailsList.get(i));
            }
        });
        holder.txtView_profileadapter_vehicleMake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onDetailsClickListener.DetailsClick(i, v.getId(), driverDetailsList.get(i));
            }
        });
        holder.txtView_profileadapter_avgMileage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onDetailsClickListener.DetailsClick(i, v.getId(), driverDetailsList.get(i));
            }
        });
        holder.txtView_profileAdapter_odometerReading.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onDetailsClickListener.DetailsClick(i, v.getId(), driverDetailsList.get(i));
            }
        });
    }

    @Override
    public int getItemCount() {

        return driverDetailsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtView_profileadapter_licensePlateNumber , txtView_profileadapter_vehicleMake, txtView_profileadapter_avgMileage,txtView_profileAdapter_odometerReading;

        public ViewHolder(View itemView){
            super(itemView);
            txtView_profileadapter_licensePlateNumber = (TextView) itemView.findViewById(R.id.licensePlateText2);
            txtView_profileadapter_vehicleMake = (TextView) itemView.findViewById(R.id.makeModelText2);
            txtView_profileadapter_avgMileage = (TextView) itemView.findViewById(R.id.avgMileageText2);
            txtView_profileAdapter_odometerReading = (TextView) itemView.findViewById(R.id.odometerReading2);

        }
    }

//
//    public float averageMileage(int id){
//        float avg;
//        float currrentMileageSum = dbHelper.currentMileageSum(id);
//       avg = currrentMileageSum/1;
//        return currrentMileageSum;
//    }

}