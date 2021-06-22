package com.example.covidtracker.Network.Global;

import com.example.covidtracker.CovidModels.GlobalModels.Response;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GlobalApiHolder {

    @GET("/summary")
    Call<Response> getResponse();

}
