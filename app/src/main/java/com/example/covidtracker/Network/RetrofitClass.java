package com.example.covidtracker.Network;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClass {

    static Retrofit retrofit=null;


    public static Retrofit getInstance(String baseurl){

        // core will be shared across both clients
        OkHttpClient baseOkHttpClient = new OkHttpClient();

// customize client for first Retrofit instance for API v1
        OkHttpClient okHttpClientV1 = baseOkHttpClient
                .newBuilder()
                .followRedirects(false)
                .build();


        retrofit= new Retrofit.Builder().baseUrl(baseurl).addConverterFactory(GsonConverterFactory.create()).build();

        return retrofit;

    }
}
