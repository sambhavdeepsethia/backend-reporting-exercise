package com.averollc.backendreportingexericse.controller;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReportController {
	
	@RequestMapping(method=RequestMethod.GET, value="/reporting")
	public String getLaborCostPercentage(@RequestParam("business_id") String business_id, 
										 @RequestParam("report") String report,
										 @RequestParam("timeInterval") String timeInterval,
										 @RequestParam("date")@DateTimeFormat(iso=DateTimeFormat.ISO.DATE_TIME) LocalDateTime startTime,
										 @RequestParam("end")@DateTimeFormat(iso=DateTimeFormat.ISO.DATE_TIME) LocalDateTime endTime){
		

		return null;
	}

}
