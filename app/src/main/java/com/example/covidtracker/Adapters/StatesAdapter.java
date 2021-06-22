package com.example.covidtracker.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.covidtracker.CovidModels.IndiaModels.RegionalItem;
import com.example.covidtracker.R;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class StatesAdapter extends RecyclerView.Adapter<StatesAdapter.StateHolder>{

    Context context;
    List<RegionalItem> statesList;

    public StatesAdapter(Context context, List<RegionalItem> statesList) {
        this.context = context;
        this.statesList = statesList;
    }

    @NonNull
    @NotNull
    @Override
    public StateHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.state_item,parent,false);
        return new StateHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull StatesAdapter.StateHolder holder, int position) {

        RegionalItem item=statesList.get(position);
        holder.stateName.setText(item.getLoc());
        holder.confirmed.setText(""+item.getTotalConfirmed());
        holder.active.setText(""+Math.abs(item.getTotalConfirmed()-item.getDischarged()-item.getDeaths()));
        holder.recovered.setText(""+(item.getDischarged()));
        holder.deaths.setText(""+item.getDeaths());


    }

    @Override
    public int getItemCount() {
        return statesList.size();
    }

    public class StateHolder extends RecyclerView.ViewHolder {
        TextView stateName,active,recovered,deaths,confirmed;
        public StateHolder(@NonNull @org.jetbrains.annotations.NotNull View itemView) {
            super(itemView);
            stateName=itemView.findViewById(R.id.stateItemName);
            active=itemView.findViewById(R.id.activeStateItemCount);
            deaths=itemView.findViewById(R.id.deathStateItemCount);
            confirmed=itemView.findViewById(R.id.confirmedStateItemCount);
            recovered=itemView.findViewById(R.id.recoveredStateItemCount);

        }
    }
}
