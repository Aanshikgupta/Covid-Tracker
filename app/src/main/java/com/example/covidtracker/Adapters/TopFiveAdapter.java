package com.example.covidtracker.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.covidtracker.CovidModels.GlobalModels.ResponseItem;
import com.example.covidtracker.R;

import java.util.List;

public class TopFiveAdapter extends RecyclerView.Adapter<TopFiveAdapter.TopFiveViewHolder> {

    Context context;
    List<ResponseItem> countriesItemList;

    public TopFiveAdapter(Context context, List<ResponseItem> countriesItemList) {
        this.context = context;
        this.countriesItemList = countriesItemList;
    }

    @NonNull
    @Override
    public TopFiveViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.top_five_item,parent,false);
        return new TopFiveViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull  TopFiveAdapter.TopFiveViewHolder holder, int position) {
        ResponseItem item=countriesItemList.get(position);
        holder.countryItem.setText(""+item.getCountry());
        holder.activeItem.setText(""+item.getCases());
        holder.recoveredItem.setText(""+item.getRecovered());
        holder.deathItem.setText(""+item.getDeaths());
    }

    @Override
    public int getItemCount() {
        return countriesItemList.size();
    }

    public class TopFiveViewHolder extends RecyclerView.ViewHolder {

        TextView countryItem,activeItem,deathItem,recoveredItem;
        public TopFiveViewHolder(@NonNull View itemView) {
            super(itemView);
            countryItem=itemView.findViewById(R.id.countryTopFiveItem);
            activeItem=itemView.findViewById(R.id.activeItemCount);
            deathItem=itemView.findViewById(R.id.deathTopFive);
            recoveredItem=itemView.findViewById(R.id.recoveredTopFive);
        }
    }
}
