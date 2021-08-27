package com.example.covidtracker.Fragments;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TimePicker;

import com.example.covidtracker.Adapters.VaccineAdapter;
import com.example.covidtracker.CovidModels.VaccinationModels.CentersItem;
import com.example.covidtracker.CovidModels.VaccinationModels.VaccResponse;
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
import retrofit2.Response;
import retrofit2.Retrofit;


public class VaccinationFragment extends Fragment {

    private View view;
    private final String BASE_URL = "https://cdn-api.co-vin.in/api/v2/";
    private Retrofit retrofit;
    private VaccinationApiHolder apiHolder;
    private RecyclerView vaccinationRecyclerView;
    private VaccineAdapter adapter;
    private List<CentersItem> centersItems;
    private ImageButton searchButton;
    private String date,pincode;
    private EditText pincodeET;
    private ProgressDialog pd;

    public VaccinationFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_vaccination, container, false);
        centersItems = new ArrayList<>();
        pd=new ProgressDialog(getContext());
        vaccinationRecyclerView = view.findViewById(R.id.vaccinationRecyclerView);
        vaccinationRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        searchButton=view.findViewById(R.id.searchVaccinationBtn);
        pincodeET=view.findViewById(R.id.pincodeEditText);

        defaultDate();
        getAndSetData();

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDateDialog();


                getAndSetData();

            }
        });


        return view;
    }

    private void defaultDate() {
        final Calendar calendar = Calendar.getInstance();
        final Date date = calendar.getTime();
        String day = new SimpleDateFormat("dd").format(date);    // always 2 digits
        String month = new SimpleDateFormat("MM").format(date);  // always 2 digits
        String year = new SimpleDateFormat("yyyy").format(date); // 4 digit year

        this.date=day+"-"+month+"-"+year;
    }

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());
    private void getDateDialog() {
        final Calendar currentDate = Calendar.getInstance();
        final Calendar dates = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                dates.set(year, month, dayOfMonth);
                date=dayOfMonth+"-"+month+"-"+year;


            }
        }, currentDate.get(Calendar.YEAR), currentDate.get(Calendar.MONTH), currentDate.get(Calendar.DATE));
        datePickerDialog.show();

        pd.setMessage("Searching..."+date);
        pd.show();





    }



    private void getAndSetData() {

        pincode=pincodeET.getText().toString();

        if(pincode.length()==0){
            pincodeET.setError("Please enter valid pincode!");
            return;
        }


        retrofit = RetrofitClass.getInstance(BASE_URL);
        apiHolder = retrofit.create(VaccinationApiHolder.class);
        Call<VaccResponse> responseCall = apiHolder.getVacResponse(this.pincode, this.date);

        responseCall.enqueue(new Callback<VaccResponse>() {
            @Override
            public void onResponse(Call<VaccResponse> call, Response<VaccResponse> response) {
                if (response.isSuccessful()) {
                    pd.dismiss();
                    List<CentersItem> centersItems = response.body().getCenters();
                    adapter = new VaccineAdapter(centersItems, getContext());
                    vaccinationRecyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();


                } else {
                    pd.setMessage("Error occurred.."+response);
                    pd.dismiss();

                }
            }

            @Override
            public void onFailure(Call<VaccResponse> call, Throwable t) {
                pd.setMessage("Error occurred.."+t.getMessage());
                pd.dismiss();
            }
        });


    }
}