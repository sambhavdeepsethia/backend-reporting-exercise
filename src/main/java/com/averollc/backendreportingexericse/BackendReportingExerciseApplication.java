package com.averollc.backendreportingexericse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import dao.FetchData;

@SpringBootApplication
public class BackendReportingExerciseApplication {

	public static void main(String[] args) {
		FetchData.getData();
		SpringApplication.run(BackendReportingExerciseApplication.class, args);
		
	}
}
