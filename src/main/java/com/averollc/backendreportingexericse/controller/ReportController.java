package com.averollc.backendreportingexericse.controller;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.averollc.backendreportingexericse.model.EmployeeGrossSales;
import com.averollc.backendreportingexericse.model.FoodCostPercentage;
import com.averollc.backendreportingexericse.model.ReportingAttributes;
import com.averollc.backendreportingexericse.model.TimeInterval;
import com.averollc.backendreportingexericse.model.TimeFrame;
@RestController
public class ReportController {
	
	@RequestMapping(method=RequestMethod.GET, value="/reporting")
	public ReportingAttributes getLaborCostPercentage(@RequestParam("business_id") String business_id, 
										 @RequestParam("report") String report,
										 @RequestParam("timeInterval") String timeInterval,
										 @RequestParam("start")String startTime,
										 @RequestParam("end")String endTime) throws Exception{
//										 @RequestParam("start")@DateTimeFormat(iso=DateTimeFormat.ISO.DATE_TIME) LocalDateTime startTime,
//										 @RequestParam("end")@DateTimeFormat(iso=DateTimeFormat.ISO.DATE_TIME) LocalDateTime endTime) throws Exception{
		
		ReportingAttributes reportingAttributes;
		
//		System.out.println("business_id: "+business_id);
//		System.out.println("report: "+report);
//		System.out.println("timeInterval: "+timeInterval);
//		System.out.println("start: "+ startTime);
//		System.out.println("end: "+ endTime);
		
		if(report.equalsIgnoreCase("FCP")) {
			reportingAttributes = computeFCP(business_id, timeInterval, startTime, endTime);
		}
		else if(report.equalsIgnoreCase("LCP")) {
			reportingAttributes = computeLCP(business_id, timeInterval, startTime, endTime);
		}
		else if(report.equalsIgnoreCase("EGS")) {
			reportingAttributes = computeEGS(business_id, timeInterval, startTime, endTime);
		}
		else {
			throw new Exception("Incorrect Report type passed");
		}
		
		return reportingAttributes;
	}


	private ReportingAttributes computeLCP(String business_id, String timeInterval, String startTime,
			String endTime) throws Exception {
		// TODO Auto-generated method stub
		
        final DateTimeFormatter formatter = new DateTimeFormatterBuilder().appendInstant(3).toFormatter();
        final ZonedDateTime zonedStartTime = ZonedDateTime.parse(startTime);
        final ZonedDateTime zonedEndTime = ZonedDateTime.parse(endTime);
        TimeInterval timeIntervalEnum =TimeInterval.valueOf(timeInterval.toUpperCase());
        List<TimeFrame> timeFrames = new ArrayList<TimeFrame>();
        
        if(zonedStartTime.isAfter(zonedEndTime)) {
        	throw new Exception("StartDate cannot be after EndDate");
        }
        
		switch(timeIntervalEnum) {
			case HOUR:
				for(ZonedDateTime date = zonedStartTime; date.isBefore(zonedEndTime); date = date.plusHours(1)) {
					timeFrames.add(new TimeFrame(date.format(formatter).toString(), date.plusHours(1).format(formatter).toString()));
				}
				for(TimeFrame t : timeFrames) {
					System.out.println("StartTime: " + t.getStart() + ", EndTime: " + t.getEnd());
				}
				break;
			case DAY:
				for(ZonedDateTime date = zonedStartTime; date.isBefore(zonedEndTime); date = date.plusDays(1)) {
					timeFrames.add(new TimeFrame(date.format(formatter).toString(), date.plusDays(1).format(formatter).toString()));
				}
				for(TimeFrame t : timeFrames) {
					System.out.println("StartTime: " + t.getStart() + ", EndTime: " + t.getEnd());
				}
				break;
			case WEEK:
				for(ZonedDateTime date = zonedStartTime; date.isBefore(zonedEndTime); date = date.plusWeeks(1)) {
					timeFrames.add(new TimeFrame(date.format(formatter).toString(), date.plusWeeks(1).format(formatter).toString()));
				}
				for(TimeFrame t : timeFrames) {
					System.out.println("StartTime: " + t.getStart() + ", EndTime: " + t.getEnd());
				}
				break;
			case MONTH:
				for(ZonedDateTime date = zonedStartTime; date.isBefore(zonedEndTime); date = date.plusMonths(1)) {
					timeFrames.add(new TimeFrame(date.format(formatter).toString(), date.plusMonths(1).format(formatter).toString()));
				}
				for(TimeFrame t : timeFrames) {
					System.out.println("StartTime: " + t.getStart() + ", EndTime: " + t.getEnd());
				}
				break;

			default:
				System.out.println("Inside defalut");
				throw new Exception("Invalid timeInterval");
		
		}
		return null;
	}

	private Object DateTimeFormatterBuilder() {
		// TODO Auto-generated method stub
		return null;
	}


	private ReportingAttributes computeFCP(String business_id, String timeInterval, String startTime,
			String endTime) throws Exception {
		// TODO Auto-generated method stub
					
		return null;
	}
	
	private ReportingAttributes computeEGS(String business_id, String timeInterval, String startTime,
			String endTime) throws Exception {
				return null;
		
		
	}


}
