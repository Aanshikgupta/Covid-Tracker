package com.example.covidtracker.Fragments;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.covidtracker.Adapters.TopFiveAdapter;
import com.example.covidtracker.Activities.CountriesActivity;
import com.example.covidtracker.CovidModels.GlobalModels.GlobalData;
import com.example.covidtracker.CovidModels.GlobalModels.ResponseItem;
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
import retrofit2.Response;
import retrofit2.Retrofit;


public class GlobalFragment extends Fragment {

    private View view;
    private Retrofit retrofit;
    private GlobalApiHolder globalApiHolder;
    private PieChart pieChart;
    private ViewPager2 viewPager;
    public static List<ResponseItem> countriesItems;
    private RecyclerView topFiveRecyclerView;
    private Button showCountries;
    private TextView activeCount, deathCount, recoveredCount, confirmedCount, activeInc, deathInc, recoveredInc, confirmedInc;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_global, container, false);
        setView(view);
        setButtonListener();
        getGlobalData();


        return view;
    }

    private void getGlobalData() {
        retrofit = RetrofitClass.getInstance("https://disease.sh/");
        globalApiHolder = retrofit.create(GlobalApiHolder.class);
        Call<GlobalData> responseCall = globalApiHolder.getGlobal();

        responseCall.enqueue(new Callback<GlobalData>() {
            @Override
            public void onResponse(Call<GlobalData> call, Response<GlobalData> response) {

                GlobalData global = response.body();

                Log.i("MSG", response.body().getCases() + "");

                setGlobalData(global);

                pieChart.addPieSlice(
                        new PieModel(
                                "Confirmed",
                                Integer.parseInt(confirmedCount.getText().toString()),
                                Color.parseColor("#1B98F5")));
                pieChart.addPieSlice(
                        new PieModel(
                                "Active",
                                Integer.parseInt(activeCount.getText().toString()),
                                Color.parseColor("#FFA000")));
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


                setCountriesList();


//                List<CountriesItem> countriesItemList = new ArrayList<>();
//                countriesItemList = response.body().getCountries();


            }

            @Override
            public void onFailure(Call<GlobalData> call, Throwable t) {
                Log.i("MSG", t.toString() + "");

            }
        });


    }

    private void setCountriesList() {

        retrofit = RetrofitClass.getInstance("https://disease.sh/");
        globalApiHolder = retrofit.create(GlobalApiHolder.class);
        Call<List<ResponseItem>> responseCall = globalApiHolder.getResponse();

        responseCall.enqueue(new Callback<List<ResponseItem>>() {
            @Override
            public void onResponse(Call<List<ResponseItem>> call, Response<List<ResponseItem>> response) {

                assert response.body() != null;
                countriesItems = response.body();

                Log.i("HERE", countriesItems + "");
                for (ResponseItem item : countriesItems) {
                    Log.i("MSG", item.getCountry());
                }

                Collections.sort(countriesItems, new Comparator<ResponseItem>() {
                    @Override
                    public int compare(ResponseItem o1, ResponseItem o2) {
                        return o2.getActive() - o1.getActive();
                    }
                });
                List<ResponseItem> topFive = new ArrayList<>();

                topFive = countriesItems.subList(0, 5);
                TopFiveAdapter adapter = new TopFiveAdapter(getContext(), topFive);
                topFiveRecyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<ResponseItem>> call, Throwable t) {
                Log.i("HERE", t.getMessage());
            }

        });


    }

    private void setGlobalData(GlobalData global) {
        deathCount.setText(global.getDeaths() + "");
        confirmedCount.setText("" + (Integer.valueOf(global.getCases())));
        recoveredCount.setText(global.getRecovered() + "");
        activeCount.setText("" + global.getActive());
        deathInc.setText(global.getTodayDeaths() + "");
        activeInc.setText("" + global.getTodayCases());
        confirmedInc.setText("" + (Integer.valueOf(global.getTodayCases())));
        recoveredInc.setText("" + global.getTodayRecovered());
    }


    private void setButtonListener() {
        showCountries.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), CountriesActivity.class);
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
        countriesItems = new ArrayList<>();
    }
}