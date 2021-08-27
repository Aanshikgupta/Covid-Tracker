package com.example.covidtracker.CovidModels.VaccinationModels;

import java.util.List;
import com.google.gson.annotations.SerializedName;

import retrofit2.Retrofit;

public class VaccResponse{

	@SerializedName("centers")
	private List<CentersItem> centers;


	public List<CentersItem> getCenters(){
		return centers;
	}
}