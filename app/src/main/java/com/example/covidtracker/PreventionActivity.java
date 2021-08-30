package com.example.covidtracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.covidtracker.Fragments.PreventionFragment;

public class PreventionActivity extends AppCompatActivity {

    private ImageButton goBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prevention);
        goBack=findViewById(R.id.goBack);

        getSupportFragmentManager().beginTransaction().replace(R.id.frame_options,new PreventionFragment()).commit();

        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}