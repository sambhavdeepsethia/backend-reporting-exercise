package com.averollc.backendreportingexericse.controller;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.averollc.backendreportingexericse.model.EmployeeGrossSales;
import com.averollc.backendreportingexericse.model.FoodCostPercentage;
import com.averollc.backendreportingexericse.model.ReportingAttributes;

@RestController
public class ReportController {
	
	@RequestMapping(method=RequestMethod.GET, value="/reporting")
	public ReportingAttributes getLaborCostPercentage(@RequestParam("business_id") String business_id, 
										 @RequestParam("report") String report,
										 @RequestParam("timeInterval") String timeInterval,
										 @RequestParam("start")@DateTimeFormat(iso=DateTimeFormat.ISO.DATE_TIME) LocalDateTime startTime,
										 @RequestParam("end")@DateTimeFormat(iso=DateTimeFormat.ISO.DATE_TIME) LocalDateTime endTime){
		
		System.out.println("business_id: "+business_id);
		System.out.println("report: "+report);
		System.out.println("timeInterval: "+timeInterval);
		System.out.println("start: "+ startTime);
		System.out.println("end: "+ endTime);

		return new FoodCostPercentage(timeInterval, timeInterval, null);
	}

}
