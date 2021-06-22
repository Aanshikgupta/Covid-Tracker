package com.example.covidtracker.CovidModels.GlobalModels;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Response{

	@SerializedName("Message")
	private String message;

	@SerializedName("Countries")
	private List<CountriesItem> countries;

	@SerializedName("ID")
	private String iD;

	@SerializedName("Global")
	private Global global;

	@SerializedName("Date")
	private String date;

	public String getMessage(){
		return message;
	}

	public List<CountriesItem> getCountries(){
		return countries;
	}

	public String getID(){
		return iD;
	}

	public Global getGlobal(){
		return global;
	}

	public String getDate(){
		return date;
	}
}