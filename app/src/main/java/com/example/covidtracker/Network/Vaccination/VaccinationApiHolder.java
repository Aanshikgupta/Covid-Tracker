package com.example.covidtracker.Network.Vaccination;


import com.example.covidtracker.CovidModels.VaccineModels.Response;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface VaccinationApiHolder {

    @GET("appointment/sessions/public/findByPin")
    Call<Response>  getVacResponse(@Query("pincode") String pincode, @Query("date") String date);

}
