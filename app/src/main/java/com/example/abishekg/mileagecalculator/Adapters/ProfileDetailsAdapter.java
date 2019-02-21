package com.example.abishekg.mileagecalculator.Adapters;



        import android.content.Context;
        import android.support.v7.widget.RecyclerView;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.TextView;


        import com.example.abishekg.mileagecalculator.Models.VehicleDetails;
        import com.example.abishekg.mileagecalculator.R;

        import java.util.List;

/**
 * Created by abishek.g on 1/11/2018.
 */

public class ProfileDetailsAdapter extends RecyclerView.Adapter<ProfileDetailsAdapter.ViewHolder> {

    private Context vehicleDetailsContext;
    private List<VehicleDetails> vehicleDetailsList;

    public ProfileDetailsAdapter(Context vehicleDetailsContext, List<VehicleDetails> vehicleDetailsList) {
        this.vehicleDetailsContext = vehicleDetailsContext;
        this.vehicleDetailsList = vehicleDetailsList;
    }


    @Override
    public ProfileDetailsAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.p4_list_layout, viewGroup, false);
        return new ProfileDetailsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProfileDetailsAdapter.ViewHolder holder, int position) {
        holder.fuelText.setText(vehicleDetailsList.get(position).getFuelQuantity().toString());
        holder.odometerText.setText(vehicleDetailsList.get(position).getOdometerReading().toString());
        holder.tripCountText.setText(String.valueOf(position+1));
        holder.currentMileageText.setText(String.format("%.02f",vehicleDetailsList.get(position).getCurrrentMileage()));
    }

    @Override
    public int getItemCount() {
        return vehicleDetailsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tripCountText, odometerText, fuelText, currentMileageText;
        public ViewHolder(View itemView) {
            super(itemView);
            tripCountText = (TextView) itemView.findViewById(R.id.tripNumberText4);
            odometerText = (TextView) itemView.findViewById(R.id.odoReadText4);
            fuelText = (TextView) itemView.findViewById(R.id.fuelAddedText4);
            currentMileageText = (TextView) itemView.findViewById(R.id.currentMileageText4);
        }
    }
}