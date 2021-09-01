package com.example.covidtracker.CovidModels.GlobalModels;

import com.google.gson.annotations.SerializedName;

public class ResponseItem{

	@SerializedName("continent")
	private String continent;

	@SerializedName("country")
	private String country;



	@SerializedName("cases")
	private int cases;

	@SerializedName("critical")
	private int critical;



	@SerializedName("active")
	private int active;





	@SerializedName("recovered")
	private int recovered;



	@SerializedName("todayRecovered")
	private int todayRecovered;




	@SerializedName("updated")
	private long updated;

	@SerializedName("deaths")
	private int deaths;



	@SerializedName("todayCases")
	private int todayCases;

	@SerializedName("todayDeaths")
	private int todayDeaths;

	public String getContinent(){
		return continent;
	}

	public String getCountry(){
		return country;
	}



	public int getCases(){
		return cases;
	}

	public int getCritical(){
		return critical;
	}



	public int getActive(){
		return active;
	}




	public int getRecovered(){
		return recovered;
	}






	public int getTodayRecovered(){
		return todayRecovered;
	}



	public long getUpdated(){
		return updated;
	}

	public int getDeaths(){
		return deaths;
	}


	public int getTodayCases(){
		return todayCases;
	}

	public int getTodayDeaths(){
		return todayDeaths;
	}
}