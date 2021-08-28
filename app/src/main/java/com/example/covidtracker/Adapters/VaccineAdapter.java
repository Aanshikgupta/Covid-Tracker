package com.example.covidtracker.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.covidtracker.CovidModels.VaccineModels.SessionsItem;
import com.example.covidtracker.R;

import java.util.List;

public class VaccineAdapter extends RecyclerView.Adapter<VaccineAdapter.VaccineViewHolder> {

    List<SessionsItem> centersItems;
    Context context;

    public VaccineAdapter(List<SessionsItem> centersItems, Context context) {
        this.centersItems = centersItems;
        this.context = context;
    }

    @NonNull
    @Override
    public VaccineViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.vaccination_item, parent, false);
        return new VaccineViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VaccineViewHolder holder, int position) {

        SessionsItem item= centersItems.get(position);

        String completeAddress = item.getBlockName() + ", " + item.getAddress() + ", " + item.getDistrictName() + ", " + item.getStateName();
        String timing = "From : " + item.getFrom() + " to " + item.getTo();

        holder.centreName.setText(item.getName());
        holder.centreAddress.setText(completeAddress);
        holder.timings.setText(timing);
        holder.priceType.setText(item.getFeeType());

        holder.vaccineType.setText(item.getVaccine());
        holder.ageLimit.setText("Min. Age limit : " + item.getMinAgeLimit());

        holder.availability.setText("Avalilability : " + item.getAvailableCapacity() + "");

    }

    @Override
    public int getItemCount() {
        return centersItems.size();
    }

    public class VaccineViewHolder extends RecyclerView.ViewHolder {
        TextView centreName, centreAddress, timings, vaccineType, priceType, ageLimit, availability;

        public VaccineViewHolder(@NonNull View itemView) {
            super(itemView);

            centreName = itemView.findViewById(R.id.centerName);
            centreAddress = itemView.findViewById(R.id.address);
            timings = itemView.findViewById(R.id.timings);
            vaccineType = itemView.findViewById(R.id.vaccineType);
            priceType = itemView.findViewById(R.id.priceType);
            ageLimit = itemView.findViewById(R.id.ageLimit);
            availability = itemView.findViewById(R.id.availability);
        }
    }
}
