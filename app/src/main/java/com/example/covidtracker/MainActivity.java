package com.example.covidtracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.covidtracker.Adapters.FragmentAdapter;
import com.example.covidtracker.CovidModels.GlobalModels.CountriesItem;
import com.example.covidtracker.Network.Global.GlobalApiHolder;
import com.google.android.material.tabs.TabLayout;

import org.eazegraph.lib.charts.PieChart;

import java.util.List;

import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {
    TabLayout tabLayout;
    public Retrofit retrofit;
    GlobalApiHolder globalApiHolder;
    PieChart pieChart;
    ViewPager2 viewPager;
    public static List<CountriesItem> countriesItems;
    RecyclerView topFiveRecyclerView;
    Button showCountries;
    TextView activeCount, deathCount, recoveredCount, confirmedCount, activeInc, deathInc, recoveredInc, confirmedInc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTabs();


    }

    private void setTabs() {
        viewPager=findViewById(R.id.viewPager);
        tabLayout = findViewById(R.id.tabLayout);
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentAdapter adapter=new FragmentAdapter(fragmentManager,getLifecycle());
        viewPager.setAdapter(adapter);
        tabLayout.setTabIndicatorFullWidth(true);
        TabLayout.Tab global=tabLayout.newTab();
        global.setText("Global");
        TabLayout.Tab ind=tabLayout.newTab();
        ind.setText("India");
        tabLayout.addTab(global,true);
        tabLayout.addTab(ind);



        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition(),false);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                tabLayout.selectTab(tabLayout.getTabAt(position));
            }
        });
    }






}