package com.example.covidtracker.CovidModels.IndiaModels;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Data{

	@SerializedName("summary")
	private Summary summary;

	@SerializedName("unofficial-summary")
	private List<UnofficialSummaryItem> unofficialSummary;

	@SerializedName("regional")
	private List<RegionalItem> regional;

	public Summary getSummary(){
		return summary;
	}

	public List<UnofficialSummaryItem> getUnofficialSummary(){
		return unofficialSummary;
	}

	public List<RegionalItem> getRegional(){
		return regional;
	}
}