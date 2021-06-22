package com.example.covidtracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.airbnb.lottie.L;
import com.example.covidtracker.Adapters.StatesAdapter;
import com.example.covidtracker.Fragments.IndiaFragment;

public class StateActivity extends AppCompatActivity {

    private RecyclerView statesRecyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_state);

        statesRecyclerView=findViewById(R.id.statesRecyclerView);
        statesRecyclerView.setHasFixedSize(true);
        statesRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        StatesAdapter adapter=new StatesAdapter(this, IndiaFragment.stateList);
        statesRecyclerView.setAdapter(adapter);
    }
}