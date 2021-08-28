package com.example.covidtracker.CovidModels.VaccineModels;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Response{

	@SerializedName("sessions")
	private List<SessionsItem> sessions;

	public List<SessionsItem> getSessions(){
		return sessions;
	}
}