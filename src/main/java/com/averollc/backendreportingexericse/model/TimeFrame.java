package com.averollc.backendreportingexericse.model;

public class TimeFrame {

	private String start;

	private String end;

	public String getStart() {
		return start;
	}
	public String getEnd() {
		return end;
	}

	public TimeFrame(String start, String end) {
		this.start = start;
		this.end = end;
	}
	@Override
	public String toString() {
		return "TimeFramePojo [start = " + start + ", end = " + end + "]";
	}
}
