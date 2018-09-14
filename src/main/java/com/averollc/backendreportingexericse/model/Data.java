package com.averollc.backendreportingexericse.model;

public class Data {

	private final  String value;

	private final TimeFrame timeFrame;
	
	public Data(String value, TimeFrame timeFrame) {
		this.value = value;
		this.timeFrame = timeFrame;
	}

	public String getValue() {
		return value;
	}

	public TimeFrame getTimeFrame() {
		return timeFrame;
	}

	@Override
	public String toString() {
		return "DataPojo [value = " + value + ", timeFrame = " + timeFrame + "]";
	}

}
