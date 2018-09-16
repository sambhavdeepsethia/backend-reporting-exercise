package com.averollc.backendreportingexericse.model;

public class Data {

	private final TimeFrame timeFrame;
	private final  double value;
	
	
	public Data(TimeFrame timeFrame, double value) {
		this.timeFrame = timeFrame;
		this.value = value;
	}

	public TimeFrame getTimeFrame() {
		return timeFrame;
	}
	
	public double getValue() {
		return value;
	}

	@Override
	public String toString() {
		return "DataPojo [timeFrame = " + timeFrame + ", value = " + value + "]";
	}

}
