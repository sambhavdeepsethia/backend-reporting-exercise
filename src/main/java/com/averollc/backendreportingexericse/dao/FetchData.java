package com.averollc.backendreportingexericse.dao;

import java.io.File;

public class FetchData {

	public static void getData() {

		final String baseDir = System.getProperty("user.dir") + File.separator + "data" + File.separator;
		final String baseURI = "https://secret-lake-26389.herokuapp.com/";
		// final String[] posAPIs = {"businesses", "menuItems", "checks",
		// "orderedItems", "employees", "laborEntries"};
		final String[] posAPIs = { "businesses", "menuItems", "checks" };
		final String headerFieldName = "Authorization";
		final String headerFieldValue = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpYXQiOjE1MzYyNjE2NDUsImV4cCI6MTUzODg1MzY0NX0.2VYZV5uW24EjdrCC-kJVfYJXSrf5qw4qSfJbqWl7q6o";

		CreateDirectories.createDirectories(baseDir, posAPIs);
		DataConsumer.dataProcessor(baseDir, baseURI, posAPIs, headerFieldName, headerFieldValue);

	}

}
