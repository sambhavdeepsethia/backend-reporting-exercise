package com.averollc.backendreportingexericse.model;

public class LaborCostPercentage extends ReportingAttributes{

	private Data[] data;

	public LaborCostPercentage(String report, String timeInterval, Data[] data) {
		super(report, timeInterval);
		this.data = data;
	}

	public Data[] getData() {
		return data;
	}
}
