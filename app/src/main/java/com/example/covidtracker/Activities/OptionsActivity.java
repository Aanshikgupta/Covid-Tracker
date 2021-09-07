package com.example.covidtracker.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.covidtracker.Fragments.PreventionFragment;
import com.example.covidtracker.Fragments.SymptomsFragment;
import com.example.covidtracker.R;

public class OptionsActivity extends AppCompatActivity{

    private ImageView goBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prevention);
        goBack=findViewById(R.id.goBack);

        int selected=getIntent().getIntExtra("menuOptionSelected",0);

        showFragmentSelected(Integer.valueOf(selected));


        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void showFragmentSelected(int selected) {

        switch (selected){
            case 0:
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_options,new PreventionFragment()).commit();
                break;
            case 1:
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_options,new SymptomsFragment()).commit();
                break;




            default:
                break;


        }

    }
}