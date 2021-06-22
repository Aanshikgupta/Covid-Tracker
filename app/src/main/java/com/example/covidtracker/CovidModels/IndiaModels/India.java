package com.example.covidtracker.CovidModels.IndiaModels;

import com.google.gson.annotations.SerializedName;

public class India{

	@SerializedName("lastRefreshed")
	private String lastRefreshed;

	@SerializedName("data")
	private Data data;

	@SerializedName("success")
	private boolean success;

	@SerializedName("lastOriginUpdate")
	private String lastOriginUpdate;

	public String getLastRefreshed(){
		return lastRefreshed;
	}

	public Data getData(){
		return data;
	}

	public boolean isSuccess(){
		return success;
	}

	public String getLastOriginUpdate(){
		return lastOriginUpdate;
	}
}