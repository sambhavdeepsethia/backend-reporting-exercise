package com.averollc.backendreportingexericse.model;

import java.util.ArrayList;

public class FoodCostPercentage extends ReportingAttributes {
	
	private ArrayList<Data> data;

	public FoodCostPercentage(String report, String timeInterval, ArrayList<Data> data) {
		super(report, timeInterval);
		this.data = data;
	}

	public ArrayList<Data> getData() {
		return data;
	}	
	
}
