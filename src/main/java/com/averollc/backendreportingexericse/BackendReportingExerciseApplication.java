package com.averollc.backendreportingexericse;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.averollc.backendreportingexericse.dao.FetchData;
import com.averollc.backendreportingexericse.model.TimeInterval;

@SpringBootApplication
public class BackendReportingExerciseApplication {

	public static void main(String[] args) {
		
//		LocalDateTime startTime = LocalDateTime.parse("2018-05-03T15:00:00.000Z", DateTimeFormatter.ISO_DATE_TIME);
//		LocalDateTime endTime = startTime.plusHours(4);
//		System.out.println("StartTime: " + startTime+ ", EndTime: " + endTime);
//		for(LocalDateTime date = startTime; date.isBefore(endTime); date = date.plusHours(1)) {
//			
//			System.out.println("Start Date: " + date + ", End Date: " +date.plusHours(1));
//			
//		}
		
		//FetchData.getData();
		SpringApplication.run(BackendReportingExerciseApplication.class, args);
		
	}
}
