package com.averollc.backendreportingexericse.model;

public class FoodCostPercentage extends ReportingAttributes {
	
	private Data[] data;

	public FoodCostPercentage(String report, String timeInterval, Data[] data) {
		super(report, timeInterval);
		this.data = data;
	}

	public Data[] getData() {
		return data;
	}	
	
}
