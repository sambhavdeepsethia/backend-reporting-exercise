package com.averollc.backendreportingexericse.model;

public class ReportingAttributes {

	private final String report;
	private final String timeInterval;
	private final Data[] data;
	
	
	public ReportingAttributes(String report, String timeInterval, Data[] data) {
		super();
		this.report = report;
		this.timeInterval = timeInterval;
		this.data = data;
	}
	public String getReport() {
		return report;
	}
	public String getTimeInterval() {
		return timeInterval;
	}
	public Data[] getData() {
		return data;
	}
	
	@Override
	public String toString() {
		return "ReportingAttributes: [timeInterval = " + timeInterval + ", data = " + data + ", report = " + report + "]";
	}
}
