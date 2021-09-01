package com.example.covidtracker.Fragments;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.covidtracker.Adapters.TopFiveIndiaAdapter;
import com.example.covidtracker.CovidModels.IndiaModels.Data;
import com.example.covidtracker.CovidModels.IndiaModels.India;
import com.example.covidtracker.CovidModels.IndiaModels.RegionalItem;
import com.example.covidtracker.CovidModels.IndiaModels.Summary;
import com.example.covidtracker.Network.India.IndiaApiHolder;
import com.example.covidtracker.Network.RetrofitClass;
import com.example.covidtracker.R;
import com.example.covidtracker.StateActivity;

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


public class IndiaFragment extends Fragment {

    View view;
    private Retrofit retrofit;
    private IndiaApiHolder indiaApiHolder;
    private PieChart pieChart;
    private RecyclerView topFiveRecyclerView;
    private Button showStates;
    private TextView activeCount, deathCount, recoveredCount, confirmedCount;
    public static List<RegionalItem> stateList;
    private List<RegionalItem> top5States;
    private TopFiveIndiaAdapter adapter;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view =inflater.inflate(R.layout.fragment_india, container, false);
        setView(view);

        setButtonListener();
        getIndianData();

        return view;
    }


    private void getIndianData() {
        Log.i("TAG","ENTRY");
        retrofit = RetrofitClass.getInstance("https://api.rootnet.in/covid19-in/stats/");
        indiaApiHolder= retrofit.create(IndiaApiHolder.class);
        Call<India> responseCall = indiaApiHolder.getResponseData();
        Log.i("TAG","ENTRY2");
        responseCall.enqueue(new Callback<India>() {
            @Override
            public void onResponse(Call<India> call, Response<India> response) {
                India india=response.body();
                if(india==null){
                    Log.i("TAG","YES");
                }
                if(india!=null) {
                    Data data = india.getData();
                    Summary indiaSummary = data.getSummary();
                    Log.i("TAG","HERE");
                    top5States=data.getRegional();
                    stateList=top5States;
                    Collections.sort(top5States, new Comparator<RegionalItem>() {
                        @Override
                        public int compare(RegionalItem o1, RegionalItem o2) {
                            return o2.getTotalConfirmed()-o1.getTotalConfirmed();
                        }
                    });

                    top5States=top5States.subList(0,5);
                    adapter=new TopFiveIndiaAdapter(getContext(),top5States);
                    topFiveRecyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();


                    setIndianData(indiaSummary);
                }

                Log.i("TAG","HERE2");

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

            }

            @Override
            public void onFailure(Call<India> call, Throwable t) {
                Log.i("TAG","YES"+t.toString());
            }
        });
    }

    private void setIndianData(Summary indianData) {
        deathCount.setText(""+indianData.getDeaths()+"");
        confirmedCount.setText(""+indianData.getTotal()+"");
        recoveredCount.setText(""+indianData.getDischarged()+"");
        activeCount.setText(""+Math.abs(indianData.getTotal() - (indianData.getDischarged()) - (indianData.getDeaths())));

    }


    private void setButtonListener() {
        showStates.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), StateActivity.class);
                getContext().startActivity(intent);
            }
        });
    }

    private void setView(View view) {
        deathCount = view.findViewById(R.id.deathsIndia);
        activeCount = view.findViewById(R.id.activeIndia);
        recoveredCount = view.findViewById(R.id.recoveredIndia);
        confirmedCount = view.findViewById(R.id.confirmedIndia);
        topFiveRecyclerView = view.findViewById(R.id.topFiveIndianRecyclerView);
        topFiveRecyclerView.setHasFixedSize(true);
        topFiveRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        pieChart = view.findViewById(R.id.piechart);
        showStates = view.findViewById(R.id.showStates);
        stateList=new ArrayList<>();
        top5States=new ArrayList<>();

    }
}