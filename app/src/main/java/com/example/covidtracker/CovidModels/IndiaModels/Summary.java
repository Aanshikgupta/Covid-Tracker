package com.example.covidtracker.CovidModels.IndiaModels;

import com.google.gson.annotations.SerializedName;

public class Summary{

	@SerializedName("total")
	private int total;

	@SerializedName("confirmedButLocationUnidentified")
	private int confirmedButLocationUnidentified;

	@SerializedName("confirmedCasesForeign")
	private int confirmedCasesForeign;

	@SerializedName("discharged")
	private int discharged;

	@SerializedName("confirmedCasesIndian")
	private int confirmedCasesIndian;

	@SerializedName("deaths")
	private int deaths;

	public int getTotal(){
		return total;
	}

	public int getConfirmedButLocationUnidentified(){
		return confirmedButLocationUnidentified;
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
}