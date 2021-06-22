package com.example.covidtracker.CovidModels.GlobalModels;

import com.google.gson.annotations.SerializedName;

public class Global{

	@SerializedName("NewRecovered")
	private String newRecovered;

	@SerializedName("NewDeaths")
	private String newDeaths;

	@SerializedName("TotalRecovered")
	private String totalRecovered;

	@SerializedName("TotalConfirmed")
	private String totalConfirmed;

	@SerializedName("NewConfirmed")
	private String newConfirmed;

	@SerializedName("TotalDeaths")
	private String totalDeaths;

	@SerializedName("Date")
	private String date;

	public String getNewRecovered(){
		return newRecovered;
	}

	public String getNewDeaths(){
		return newDeaths;
	}

	public String getTotalRecovered(){
		return totalRecovered;
	}

	public String getTotalConfirmed(){
		return totalConfirmed;
	}

	public String getNewConfirmed(){
		return newConfirmed;
	}

	public String getTotalDeaths(){
		return totalDeaths;
	}

	public String getDate(){
		return date;
	}
}