package com.example.covidtracker.CovidModels.IndiaModels;

import com.google.gson.annotations.SerializedName;

public class RegionalItem{

	@SerializedName("loc")
	private String loc;

	@SerializedName("confirmedCasesForeign")
	private int confirmedCasesForeign;

	@SerializedName("discharged")
	private int discharged;

	@SerializedName("confirmedCasesIndian")
	private int confirmedCasesIndian;

	@SerializedName("deaths")
	private int deaths;

	@SerializedName("totalConfirmed")
	private int totalConfirmed;

	public String getLoc(){
		return loc;
	}

	public int getConfirmedCasesForeign(){
		return confirmedCasesForeign;
	}

	public int getDischarged(){
		return discharged;
	}

	public int getConfirmedCasesIndian(){
		return confirmedCasesIndian;
	}

	public int getDeaths(){
		return deaths;
	}

	public int getTotalConfirmed(){
		return totalConfirmed;
	}
}