package com.example.covidtracker.Network.Global;

import com.example.covidtracker.CovidModels.GlobalModels.GlobalData;
import com.example.covidtracker.CovidModels.GlobalModels.ResponseItem;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GlobalApiHolder {

    @GET("/v3/covid-19/countries/")
    Call<List<ResponseItem>> getResponse();

    @GET("/v3/covid-19/all")
    Call<GlobalData> getGlobal();

}
