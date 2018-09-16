package com.averollc.backendreportingexericse.dao;

import java.io.File;
import java.time.LocalDateTime;

public class FetchData {
	
	private static final String baseDir = System.getProperty("user.dir") + File.separator + "data" + File.separator;
	private static final String baseURI = "https://secret-lake-26389.herokuapp.com/";
	private static final String[] posAPIs = {"businesses", "menuItems", "checks", "orderedItems", "employees", "laborEntries"};
	//final String[] posAPIs = { "businesses", "menuItems", "checks" };
	private static final String headerFieldName = "Authorization";
	private static final String headerFieldValue = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpYXQiOjE1MzYyNjE2NDUsImV4cCI6MTUzODg1MzY0NX0.2VYZV5uW24EjdrCC-kJVfYJXSrf5qw4qSfJbqWl7q6o";

	public static void getData() {

		
		CreateDirectories.createDirectories(baseDir, posAPIs);
		DataConsumer.dataProcessor(baseDir, baseURI, posAPIs, headerFieldName, headerFieldValue);

	}
	
	public static String getChecks(String business_id, LocalDateTime startTime, LocalDateTime endTime) {
		return null;
	}

}
