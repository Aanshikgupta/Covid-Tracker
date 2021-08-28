package com.example.covidtracker.CovidModels.VaccineModels;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class SessionsItem{

	@SerializedName("date")
	private String date;

	@SerializedName("pincode")
	private int pincode;

	@SerializedName("address")
	private String address;

	@SerializedName("min_age_limit")
	private int minAgeLimit;

	@SerializedName("fee")
	private String fee;

	@SerializedName("session_id")
	private String sessionId;

	@SerializedName("fee_type")
	private String feeType;

	@SerializedName("available_capacity")
	private int availableCapacity;

	@SerializedName("long")
	private int jsonMemberLong;

	@SerializedName("district_name")
	private String districtName;

	@SerializedName("block_name")
	private String blockName;

	@SerializedName("vaccine")
	private String vaccine;

	@SerializedName("slots")
	private List<String> slots;

	@SerializedName("center_id")
	private int centerId;

	@SerializedName("state_name")
	private String stateName;

	@SerializedName("name")
	private String name;

	@SerializedName("from")
	private String from;

	@SerializedName("to")
	private String to;

	@SerializedName("allow_all_age")
	private boolean allowAllAge;

	@SerializedName("available_capacity_dose2")
	private int availableCapacityDose2;

	@SerializedName("lat")
	private int lat;

	@SerializedName("available_capacity_dose1")
	private int availableCapacityDose1;

	public String getDate(){
		return date;
	}

	public int getPincode(){
		return pincode;
	}

	public String getAddress(){
		return address;
	}

	public int getMinAgeLimit(){
		return minAgeLimit;
	}

	public String getFee(){
		return fee;
	}

	public String getSessionId(){
		return sessionId;
	}

	public String getFeeType(){
		return feeType;
	}

	public int getAvailableCapacity(){
		return availableCapacity;
	}

	public int getJsonMemberLong(){
		return jsonMemberLong;
	}

	public String getDistrictName(){
		return districtName;
	}

	public String getBlockName(){
		return blockName;
	}

	public String getVaccine(){
		return vaccine;
	}

	public List<String> getSlots(){
		return slots;
	}

	public int getCenterId(){
		return centerId;
	}

	public String getStateName(){
		return stateName;
	}

	public String getName(){
		return name;
	}

	public String getFrom(){
		return from;
	}

	public String getTo(){
		return to;
	}

	public boolean isAllowAllAge(){
		return allowAllAge;
	}

	public int getAvailableCapacityDose2(){
		return availableCapacityDose2;
	}

	public int getLat(){
		return lat;
	}

	public int getAvailableCapacityDose1(){
		return availableCapacityDose1;
	}
}