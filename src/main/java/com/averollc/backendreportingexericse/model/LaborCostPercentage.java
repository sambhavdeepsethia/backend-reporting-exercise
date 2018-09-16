package com.averollc.backendreportingexericse.model;

import java.util.ArrayList;

public class LaborCostPercentage extends ReportingAttributes{

	private ArrayList<Data> data;

	public LaborCostPercentage(String report, String timeInterval, ArrayList<Data> data) {
		super(report, timeInterval);
		this.data = data;
	}

	public ArrayList<Data> getData() {
		return data;
	}	
}
