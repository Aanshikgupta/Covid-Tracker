package com.example.covidtracker.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.covidtracker.CovidModels.GlobalModels.CountriesItem;
import com.example.covidtracker.R;

import java.util.List;

public class CountriesAdapter extends RecyclerView.Adapter<CountriesAdapter.CountryHolder> {

    public CountriesAdapter(Context context, List<CountriesItem> countriesItems) {
        this.context = context;
        this.countriesItems = countriesItems;
    }

    Context context;
    List<CountriesItem> countriesItems;


    @NonNull
    @org.jetbrains.annotations.NotNull
    @Override
    public CountryHolder onCreateViewHolder(@NonNull @org.jetbrains.annotations.NotNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.countries_item,parent,false);
        return new CountryHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @org.jetbrains.annotations.NotNull CountriesAdapter.CountryHolder holder, int position) {
        CountriesItem countriesItem=countriesItems.get(position);

        long confirmedCases=Long.parseLong(countriesItem.getTotalConfirmed());
        long confirmedCasesInc=Long.parseLong(countriesItem.getNewConfirmed());

        long recoveredCases=Long.parseLong(countriesItem.getTotalRecovered());
        long recoveredCasesInc=Long.parseLong(countriesItem.getNewRecovered());

        long deathCases=Long.parseLong(countriesItem.getTotalDeaths());
        long deathCasesInc=Long.parseLong(countriesItem.getNewDeaths());


        holder.active.setText(""+Math.abs(confirmedCases-recoveredCases-deathCases));
        holder.activeInc.setText(""+Math.abs(confirmedCasesInc-recoveredCasesInc-deathCasesInc));

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
