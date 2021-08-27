package com.example.covidtracker.Network.Vaccination;


import com.example.covidtracker.CovidModels.VaccinationModels.VaccResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface VaccinationApiHolder {

    @GET("appointment/sessions/public/calendarByPin/")
    Call<VaccResponse>  getVacResponse(@Query("pincode") String pincode,@Query("date") String date);

}
