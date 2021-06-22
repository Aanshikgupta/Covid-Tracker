package com.example.covidtracker.CovidModels.IndiaModels;

import com.google.gson.annotations.SerializedName;

public class UnofficialSummaryItem{

	@SerializedName("total")
	private int total;

	@SerializedName("recovered")
	private int recovered;

	@SerializedName("active")
	private int active;

	@SerializedName("source")
	private String source;

	@SerializedName("deaths")
	private int deaths;

	public int getTotal(){
		return total;
	}

	public int getRecovered(){
		return recovered;
	}

	public int getActive(){
		return active;
	}

	public String getSource(){
		return source;
	}

	public int getDeaths(){
		return deaths;
	}
}