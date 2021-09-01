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

public class CountriesAdapter extends RecyclerView.Adapter<CountriesAdapter.CountryHolder> {

    public CountriesAdapter(Context context, List<ResponseItem> countriesItems) {
        this.context = context;
        this.countriesItems = countriesItems;
    }

    Context context;
    List<ResponseItem> countriesItems;


    @NonNull
    @org.jetbrains.annotations.NotNull
    @Override
    public CountryHolder onCreateViewHolder(@NonNull @org.jetbrains.annotations.NotNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.countries_item,parent,false);
        return new CountryHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @org.jetbrains.annotations.NotNull CountriesAdapter.CountryHolder holder, int position) {
        ResponseItem countriesItem=countriesItems.get(position);

        long confirmedCases=countriesItem.getCases();
        long confirmedCasesInc=countriesItem.getTodayCases();

        long recoveredCases=countriesItem.getRecovered();
        long recoveredCasesInc=countriesItem.getTodayRecovered();

        long deathCases=countriesItem.getDeaths();
        long deathCasesInc=countriesItem.getTodayDeaths();


        holder.active.setText(""+countriesItem.getActive());
        holder.activeInc.setText(""+(countriesItem.getTodayCases()-countriesItem.getTodayRecovered()-countriesItem.getTodayDeaths()));

        holder.deaths.setText(""+deathCases);
        holder.deathInc.setText(""+deathCasesInc);

        holder.countryName.setText(countriesItem.getCountry());

        holder.recovered.setText(""+recoveredCases);
        holder.recoveredInc.setText(""+recoveredCasesInc);

        holder.confirmed.setText(""+confirmedCases);
        holder.confirmedInc.setText(""+confirmedCasesInc);


    }

    @Override
    public int getItemCount() {
        return countriesItems.size();
    }

    public class CountryHolder extends RecyclerView.ViewHolder {
        TextView countryName,active,recovered,deaths,confirmed, activeInc,deathInc,recoveredInc,confirmedInc;
        public CountryHolder(@NonNull @org.jetbrains.annotations.NotNull View itemView) {
            super(itemView);
            countryName=itemView.findViewById(R.id.countryItemName);
            active=itemView.findViewById(R.id.activeItemCount);
            deaths=itemView.findViewById(R.id.deathItemCount);
            confirmed=itemView.findViewById(R.id.confirmedItemCount);
            recovered=itemView.findViewById(R.id.recoveredItemCount);
            activeInc=itemView.findViewById(R.id.activeItemInc);
            recoveredInc=itemView.findViewById(R.id.recoveredItemInc);
            deathInc=itemView.findViewById(R.id.deathItemInc);
            confirmedInc=itemView.findViewById(R.id.confirmedItemInc);
        }
    }
}
