package com.example.covidtracker;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.PopupMenu;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.covidtracker.Adapters.FragmentAdapter;
import com.example.covidtracker.Network.Global.GlobalApiHolder;
import com.google.android.material.tabs.TabLayout;

import org.eazegraph.lib.charts.PieChart;


import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {
    private TabLayout tabLayout;
    public Retrofit retrofit;
    private GlobalApiHolder globalApiHolder;
    private PieChart pieChart;
    private ViewPager2 viewPager;
    private RecyclerView topFiveRecyclerView;
    private Button showCountries;
    private TextView activeCount, deathCount, recoveredCount, confirmedCount, activeInc, deathInc, recoveredInc, confirmedInc;
    private ImageView optionButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        optionButton = findViewById(R.id.optionMenu);

        isNetworkConnectionAvailable();


        setTabs();


        optionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showMenu(view);
            }
        });

    }

    public void checkNetworkConnection() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("No internet Connection");
        builder.setMessage("Please turn on internet connection to continue");
        builder.setPositiveButton("Try again", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = getIntent();
                finish();
                startActivity(intent);
            }
        });
        builder.setNegativeButton("close", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                finish();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    public boolean isNetworkConnectionAvailable() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnected();
        if (isConnected) {
            Log.d("Network", "Connected");
            return true;
        } else {
            checkNetworkConnection();
            Log.d("Network", "Not Connected");
            return false;
        }
    }

    public void showMenu(View v) {
        PopupMenu popup = new PopupMenu(this, v);
        popup.setOnMenuItemClickListener(MainActivity.this);
        popup.inflate(R.menu.actions);
        popup.show();
    }


    private void setTabs() {
        viewPager = findViewById(R.id.viewPager);
        tabLayout = findViewById(R.id.tabLayout);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentAdapter adapter = new FragmentAdapter(fragmentManager, getLifecycle());
        viewPager.setAdapter(adapter);
        tabLayout.setTabIndicatorFullWidth(true);
        TabLayout.Tab ind = tabLayout.newTab();
        ind.setText("India");
        TabLayout.Tab global = tabLayout.newTab();
        global.setText("Global");
        TabLayout.Tab vacc = tabLayout.newTab();
        vacc.setText("Vaccine");
        TabLayout.Tab news = tabLayout.newTab();
        news.setText("News");
        tabLayout.addTab(ind, true);
        tabLayout.addTab(global);
        tabLayout.addTab(vacc);
        tabLayout.addTab(news);


        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition(), false);
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


    @Override
    public boolean onMenuItemClick(MenuItem item) {

        Intent i;
        switch (item.getItemId()) {
            case R.id.prevention:
                i = new Intent(MainActivity.this, OptionsActivity.class);
                i.putExtra("menuOptionSelected", 0);
                startActivity(i);
                return true;
            case R.id.symptoms:
                i = new Intent(MainActivity.this, OptionsActivity.class);
                i.putExtra("menuOptionSelected", 1);
                startActivity(i);
                return true;
            case R.id.helpline:
                i = new Intent(MainActivity.this, OptionsActivity.class);
                i.putExtra("menuOptionSelected", 2);
                startActivity(i);
                return true;

            default:
                return false;
        }


    }
}

