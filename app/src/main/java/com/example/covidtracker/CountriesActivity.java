package com.example.covidtracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.covidtracker.Adapters.CountriesAdapter;
import com.example.covidtracker.CovidModels.GlobalModels.CountriesItem;
import com.example.covidtracker.Fragments.GlobalFragment;

import java.util.ArrayList;
import java.util.List;

public class CountriesActivity extends AppCompatActivity {

    private RecyclerView countriesRecyclerView;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_countries);
        List<CountriesItem> countries = new ArrayList<CountriesItem>();
        countries= GlobalFragment.countriesItems;
        countriesRecyclerView=findViewById(R.id.countriesRecyclerView);
        countriesRecyclerView.setHasFixedSize(true);
        countriesRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        CountriesAdapter adapter=new CountriesAdapter(CountriesActivity.this, countries);
        countriesRecyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }
}