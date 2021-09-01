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

import java.util.List;

public class TopFiveIndiaAdapter extends RecyclerView.Adapter<TopFiveIndiaAdapter.TopFiveIndiaViewHolder> {

    Context context;
    List<RegionalItem> statesList;

    public TopFiveIndiaAdapter(Context context, List<RegionalItem> statesList) {
        this.context = context;
        this.statesList = statesList;
    }

    @NonNull
    @Override
    public TopFiveIndiaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.top_five_item,parent,false);
        return new TopFiveIndiaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull  TopFiveIndiaAdapter.TopFiveIndiaViewHolder holder, int position) {
        RegionalItem item=statesList.get(position);
        holder.countryItem.setText(""+item.getLoc());
        holder.activeItem.setText(""+Math.abs(item.getTotalConfirmed()-item.getDischarged()-item.getDeaths()));
        holder.recoveredItem.setText(""+(item.getDischarged()));
        holder.deathItem.setText(""+item.getDeaths());
    }

    @Override
    public int getItemCount() {
        return statesList.size();
    }

    public class TopFiveIndiaViewHolder extends RecyclerView.ViewHolder {

        TextView countryItem,activeItem,deathItem,recoveredItem;
        public TopFiveIndiaViewHolder(@NonNull View itemView) {
            super(itemView);
            countryItem=itemView.findViewById(R.id.countryTopFiveItem);
            activeItem=itemView.findViewById(R.id.activeItemCount);
            deathItem=itemView.findViewById(R.id.deathTopFive);
            recoveredItem=itemView.findViewById(R.id.recoveredTopFive);
        }
    }
}

