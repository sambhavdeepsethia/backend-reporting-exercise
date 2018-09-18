package com.averollc.backendreportingexericse.controller;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.List;

import com.averollc.backendreportingexericse.model.TimeFrame;
import com.averollc.backendreportingexericse.model.TimeInterval;

public class ReportService
{
    public static List<TimeFrame> getTimeFrameList(final TimeInterval timeIntervalEnum, final String startTime, final String endTime) throws Exception
    {

        final DateTimeFormatter formatter = new DateTimeFormatterBuilder().appendInstant(3).toFormatter();
        final ZonedDateTime zonedStartTime = ZonedDateTime.parse(startTime);
        final ZonedDateTime zonedEndTime = ZonedDateTime.parse(endTime);
        // final TimeInterval timeIntervalEnum = TimeInterval.valueOf(timeInterval.toUpperCase());
        final List<TimeFrame> timeFrames = new ArrayList<TimeFrame>();

        if (zonedStartTime.isAfter(zonedEndTime)) {
            throw new Exception("StartDate cannot be after EndDate");
        }

        switch (timeIntervalEnum) {
        case HOUR:
            for (ZonedDateTime date = zonedStartTime; date.isBefore(zonedEndTime); date = date.plusHours(1)) {
                timeFrames.add(new TimeFrame(date.format(formatter).toString(), date.plusHours(1).format(formatter).toString()));
            }
            for (final TimeFrame t : timeFrames) {
                System.out.println("StartTime: " + t.getStart() + ", EndTime: " + t.getEnd());
            }
        break;
        case DAY:
            for (ZonedDateTime date = zonedStartTime; date.isBefore(zonedEndTime); date = date.plusDays(1)) {
                timeFrames.add(new TimeFrame(date.format(formatter).toString(), date.plusDays(1).format(formatter).toString()));
            }
            for (final TimeFrame t : timeFrames) {
                System.out.println("StartTime: " + t.getStart() + ", EndTime: " + t.getEnd());
            }
        break;
        case WEEK:
            for (ZonedDateTime date = zonedStartTime; date.isBefore(zonedEndTime); date = date.plusWeeks(1)) {
                timeFrames.add(new TimeFrame(date.format(formatter).toString(), date.plusWeeks(1).format(formatter).toString()));
            }
            for (final TimeFrame t : timeFrames) {
                System.out.println("StartTime: " + t.getStart() + ", EndTime: " + t.getEnd());
            }
        break;
        case MONTH:
            for (ZonedDateTime date = zonedStartTime; date.isBefore(zonedEndTime); date = date.plusMonths(1)) {
                timeFrames.add(new TimeFrame(date.format(formatter).toString(), date.plusMonths(1).format(formatter).toString()));
            }
            for (final TimeFrame t : timeFrames) {
                System.out.println("StartTime: " + t.getStart() + ", EndTime: " + t.getEnd());
            }
        break;

        default:
            System.out.println("Inside defalut");
            throw new Exception("Invalid timeInterval");

        }
        return timeFrames;
    }

    public static double getSumOfDoubles(final List<Object> objectList)
    {
        double sum = 0;
        for (final Object o : objectList) {
            sum += Double.parseDouble(o.toString());
        }

        return sum;

    }

}
