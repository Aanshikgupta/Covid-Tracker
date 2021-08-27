package com.example.covidtracker.CovidModels.VaccinationModels;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class CentersItem{

	@SerializedName("pincode")
	private int pincode;

	@SerializedName("sessions")
	private List<SessionsItem> sessions;

	@SerializedName("address")
	private String address;

	@SerializedName("fee_type")
	private String feeType;

	@SerializedName("long")
	private int jsonMemberLong;

	@SerializedName("district_name")
	private String districtName;

	@SerializedName("block_name")
	private String blockName;

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

	@SerializedName("lat")
	private int lat;

	public int getPincode(){
		return pincode;
	}

	public List<SessionsItem> getSessions(){
		return sessions;
	}

	public String getAddress(){
		return address;
	}

	public String getFeeType(){
		return feeType;
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

	public int getLat(){
		return lat;
	}
}