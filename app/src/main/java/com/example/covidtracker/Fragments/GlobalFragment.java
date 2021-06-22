package com.example.covidtracker.Fragments;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.covidtracker.Adapters.TopFiveAdapter;
import com.example.covidtracker.CountriesActivity;
import com.example.covidtracker.CovidModels.GlobalModels.CountriesItem;
import com.example.covidtracker.CovidModels.GlobalModels.Global;
import com.example.covidtracker.CovidModels.GlobalModels.Response;
import com.example.covidtracker.Network.Global.GlobalApiHolder;
import com.example.covidtracker.Network.RetrofitClass;
import com.example.covidtracker.R;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;


public class GlobalFragment extends Fragment {

    private View view;
    private Retrofit retrofit;
    private GlobalApiHolder globalApiHolder;
    private PieChart pieChart;
    private ViewPager2 viewPager;
    public static List<CountriesItem> countriesItems;
    private RecyclerView topFiveRecyclerView;
    private Button showCountries;
    private TextView activeCount, deathCount, recoveredCount, confirmedCount, activeInc, deathInc, recoveredInc, confirmedInc;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view= inflater.inflate(R.layout.fragment_global, container, false);
        setView(view);
        setButtonListener();
        getGlobalData();




        return view;
    }

    private void getGlobalData() {
        retrofit = RetrofitClass.getInstance("https://api.covid19api.com/");
        globalApiHolder = retrofit.create(GlobalApiHolder.class);
        Call<Response> responseCall = globalApiHolder.getResponse();
        responseCall.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                assert response.body() != null;
                Global global = response.body().getGlobal();
                setGlobalData(global);
                countriesItems=response.body().getCountries();

                pieChart.addPieSlice(
                        new PieModel(
                                "Confirmed",
                                Integer.parseInt(confirmedCount.getText().toString()),
                                Color.parseColor("#1B98F5")));
                pieChart.addPieSlice(
                        new PieModel(
                                "Active",
                                Integer.parseInt(activeCount.getText().toString()),
                                Color.parseColor("#FFFF00")));
                pieChart.addPieSlice(
                        new PieModel(
                                "Recovered",
                                Integer.parseInt(recoveredCount.getText().toString()),
                                Color.parseColor("#38CC77")));
                pieChart.addPieSlice(
                        new PieModel(
                                "Deaths",
                                Integer.parseInt(deathCount.getText().toString()),
                                Color.parseColor("#EF5354")));
                pieChart.startAnimation();


                List<CountriesItem> countriesItemList = new ArrayList<>();
                countriesItemList = response.body().getCountries();
                Collections.sort(countriesItemList, new Comparator<CountriesItem>() {
                    @Override
                    public int compare(CountriesItem o1, CountriesItem o2) {
                        return Math.abs(Integer.parseInt(o2.getTotalConfirmed())-Integer.parseInt(o2.getTotalDeaths())-Integer.parseInt(o2.getTotalRecovered())) - Math.abs(Integer.parseInt(o1.getTotalConfirmed())-Integer.parseInt(o1.getTotalDeaths())-Integer.parseInt(o1.getTotalRecovered()));
                    }
                });
                List<CountriesItem> topFive = new ArrayList<>();
                topFive = countriesItemList.subList(0, 5);
                TopFiveAdapter adapter = new TopFiveAdapter(getContext(), topFive);
                topFiveRecyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();


            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {

            }
        });
    }

    private void setGlobalData(Global global) {
        deathCount.setText(global.getTotalDeaths());
        confirmedCount.setText(global.getTotalConfirmed());
        recoveredCount.setText(global.getTotalRecovered());
        activeCount.setText("" + Math.abs((Integer.parseInt(global.getTotalConfirmed()) - Integer.parseInt(global.getTotalRecovered()) - Integer.parseInt(global.getTotalDeaths()))));
        deathInc.setText(global.getNewDeaths());
        activeInc.setText("" + Math.abs((Integer.parseInt(global.getNewConfirmed()) - Integer.parseInt(global.getNewRecovered()) - Integer.parseInt(global.getNewDeaths()))));
        confirmedInc.setText(global.getNewConfirmed());
        recoveredInc.setText(global.getNewRecovered());
    }


    private void setButtonListener() {
        showCountries.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), CountriesActivity.class);
                getContext().startActivity(intent);
            }
        });
    }

    private void setView(View view) {
        deathCount = view.findViewById(R.id.deaths);
        activeCount = view.findViewById(R.id.active);
        recoveredCount = view.findViewById(R.id.recovered);
        confirmedCount = view.findViewById(R.id.confirmed);
        deathInc = view.findViewById(R.id.deathInc);
        confirmedInc = view.findViewById(R.id.confirmedInc);
        recoveredInc = view.findViewById(R.id.recoveredInc);
        activeInc = view.findViewById(R.id.activeInc);
        topFiveRecyclerView = view.findViewById(R.id.topFiveGlobalRecyclerView);
        topFiveRecyclerView.setHasFixedSize(true);
        topFiveRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        pieChart = view.findViewById(R.id.piechart);
        showCountries = view.findViewById(R.id.showCountries);
        countriesItems=new ArrayList<>();
    }
}