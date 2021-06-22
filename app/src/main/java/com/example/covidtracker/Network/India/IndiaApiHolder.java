package com.example.covidtracker.Network.India;

import com.example.covidtracker.CovidModels.IndiaModels.India;

import retrofit2.Call;
import retrofit2.http.GET;

public interface IndiaApiHolder {

    @GET("latest")
    Call<India> getResponseData();

}
