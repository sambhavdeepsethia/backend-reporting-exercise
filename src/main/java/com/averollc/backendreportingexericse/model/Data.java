package com.averollc.backendreportingexericse.model;

public class Data {

	private final TimeFrame timeFrame;
	private final  String value;
	
	
	public Data(TimeFrame timeFrame, String value) {
		this.timeFrame = timeFrame;
		this.value = value;
	}

	public TimeFrame getTimeFrame() {
		return timeFrame;
	}
	
	public String getValue() {
		return value;
	}

	@Override
	public String toString() {
		return "DataPojo [timeFrame = " + timeFrame + ", value = " + value + "]";
	}

}
