package com.averollc.backendreportingexericse.model;

public class ReportingAttributes {

	private final String report;
	private final String timeInterval;

	public ReportingAttributes(String report, String timeInterval) {
		super();
		this.report = report;
		this.timeInterval = timeInterval;
	}
	public String getReport() {
		return report;
	}
	public String getTimeInterval() {
		return timeInterval;
	}

	@Override
	public String toString() {
		return "ReportingAttributes: [report = " + report + ", timeInterval = " + timeInterval + "]";
	}
}
