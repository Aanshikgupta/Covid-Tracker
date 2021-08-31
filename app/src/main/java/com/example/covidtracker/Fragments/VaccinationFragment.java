package com.example.covidtracker.Fragments;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.covidtracker.Adapters.VaccineAdapter;
import com.example.covidtracker.CovidModels.VaccineModels.Response;
import com.example.covidtracker.CovidModels.VaccineModels.SessionsItem;
import com.example.covidtracker.Network.RetrofitClass;
import com.example.covidtracker.Network.Vaccination.VaccinationApiHolder;
import com.example.covidtracker.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;


public class VaccinationFragment extends Fragment {

    private View view;
    private final String BASE_URL = "https://cdn-api.co-vin.in/api/v2/";
    private Retrofit retrofit;
    private VaccinationApiHolder apiHolder;
    private RecyclerView vaccinationRecyclerView;
    private List<SessionsItem> centresList;
    private ImageButton searchButton;
    private String date, pincode;
    private EditText pincodeET;
    private ProgressDialog pd;
    private TextView noData;
    private VaccineAdapter adapter;
    private CardView register_vaccine_cardview;

    public VaccinationFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_vaccination, container, false);

        pd = new ProgressDialog(getContext());
        vaccinationRecyclerView = view.findViewById(R.id.vaccinationRecyclerView);
        vaccinationRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        searchButton = view.findViewById(R.id.searchVaccinationBtn);
        pincodeET = view.findViewById(R.id.pincodeEditText);
        noData = view.findViewById(R.id.noData);
        register_vaccine_cardview = view.findViewById(R.id.register_vaccine_cardView);

        register_vaccine_cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://selfregistration.cowin.gov.in/"));
                startActivity(intent);
            }
        });
        centresList = new ArrayList<>();


        noData.setVisibility(View.INVISIBLE);

        ImageView gifImg = view.findViewById(R.id.registerVaccineIV);
        Glide.with(this).load(R.raw.register_vaccine).into(gifImg);


        adapter = new VaccineAdapter(centresList, getContext());
        vaccinationRecyclerView.setAdapter(adapter);


        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                pincode = pincodeET.getText().toString().trim();

                if (pincode.length() == 0) {
                    pincodeET.setError("Please enter valid pincode!");
                    return;
                }

                pushSoftKeyBoardDown();
                getDateDialog();

            }
        });


        return view;
    }

    private void pushSoftKeyBoardDown() {
        InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    private void defaultDate() {
        final Calendar calendar = Calendar.getInstance();
        final Date date = calendar.getTime();
        String day = new SimpleDateFormat("dd").format(date);
        String month = new SimpleDateFormat("MM").format(date);
        String year = new SimpleDateFormat("yyyy").format(date);

        this.date = day + "-" + month + "-" + year;
    }

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());

    private void getDateDialog() {
        final Calendar currentDate = Calendar.getInstance();
        final Calendar dates = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                dates.set(year, month, dayOfMonth);
                date = dayOfMonth + "-" + (month + 1) + "-" + year;

                getAndSetData();


            }
        }, currentDate.get(Calendar.YEAR), currentDate.get(Calendar.MONTH), currentDate.get(Calendar.DATE));
        datePickerDialog.show();


    }


    private void getAndSetData() {

        pincode = pincodeET.getText().toString().trim();

        if (pincode.length() == 0) {
            pincodeET.setError("Please enter valid pincode!");
            return;
        }

        pd.setMessage("Searching..." + date);
        pd.show();


        retrofit = RetrofitClass.getInstance(BASE_URL);
        apiHolder = retrofit.create(VaccinationApiHolder.class);
        Call<com.example.covidtracker.CovidModels.VaccineModels.Response> responseCall = apiHolder.getVacResponse(pincode, date);

        responseCall.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                if (response.isSuccessful()) {
                    pd.dismiss();

                    centresList.clear();
                    centresList = new ArrayList<>(response.body().getSessions());


                    if (centresList.size() != 0) {
                        vaccinationRecyclerView.setVisibility(View.VISIBLE);
                        noData.setVisibility(View.GONE);
                        adapter = new VaccineAdapter(centresList, getContext());
                        vaccinationRecyclerView.setAdapter(adapter);

                    } else {
                        noData.setVisibility(View.VISIBLE);
                        vaccinationRecyclerView.setVisibility(View.GONE);
                    }


                } else {
                    Log.i("MSG", "Error..." + response.toString() + "  " + BASE_URL);
                    noData.setVisibility(View.VISIBLE);
                    vaccinationRecyclerView.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                Log.i("MSG", t.getMessage() + " " + BASE_URL);
                noData.setVisibility(View.VISIBLE);
                vaccinationRecyclerView.setVisibility(View.GONE);
            }
        });
        adapter.notifyDataSetChanged();

    }
}