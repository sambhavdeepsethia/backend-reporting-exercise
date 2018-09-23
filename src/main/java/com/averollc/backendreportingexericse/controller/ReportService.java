package com.averollc.backendreportingexericse.controller;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.averollc.backendreportingexericse.model.LaborEntry;
import com.averollc.backendreportingexericse.model.TimeFrame;
import com.averollc.backendreportingexericse.model.TimeInterval;

public class ReportService
{
    private static final Logger logger = LogManager.getLogger(ReportService.class);

    public static List<TimeFrame> getTimeFrameList(final TimeInterval timeIntervalEnum, final String startTime, final String endTime) throws Exception
    {
        final DateTimeFormatter formatter = new DateTimeFormatterBuilder().appendInstant(3).toFormatter();
        final ZonedDateTime zonedStartTime = ZonedDateTime.parse(startTime);
        final ZonedDateTime zonedEndTime = ZonedDateTime.parse(endTime);

        if (zonedStartTime.isAfter(zonedEndTime)) {
            logger.error("StartDate {} cannot be after EndDate {}", zonedStartTime, zonedEndTime);
            throw new Exception("StartDate cannot be after EndDate");
        }

        final List<TimeFrame> timeFrames = new ArrayList<>();
        switch (timeIntervalEnum) {
        case HOUR:
            for (ZonedDateTime date = zonedStartTime; date.isBefore(zonedEndTime); date = date.plusHours(1)) {
                timeFrames.add(new TimeFrame(date.format(formatter).toString(), date.plusHours(1).format(formatter).toString()));
            }
            for (final TimeFrame t : timeFrames) {
                logger.debug("StartTime: {}, EndTime: {}", t.getStart(), t.getEnd());
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

    public static double computeSum(final List<Object> objectList)
    {
        logger.debug("Compute Sum, input list:{}", objectList);
        double sum = 0;
        for (final Object o : objectList) {
            sum += Double.parseDouble(o.toString());
        }
        logger.debug("Sum:{}", sum);
        return sum;
    }

    public static double computeTotalLaborCost(final List<LaborEntry> totalLaborEntries, final String startTime, final String endTime)
    {
        double totalLaborCost = 0;
        final ZonedDateTime start = ZonedDateTime.parse(startTime);
        final ZonedDateTime end = ZonedDateTime.parse(endTime);

        for (final LaborEntry l : totalLaborEntries) {
            double hours = 0;
            LocalDateTime cin = l.getClock_in().toLocalDateTime();
            LocalDateTime cout = l.getClock_out().toLocalDateTime();

            if (l.getClock_in().isBefore(start)) {
                cin = start.toLocalDateTime();
            }
            if (l.getClock_out().isAfter(end)) {
                cout = end.toLocalDateTime();
            }

            final Duration d = Duration.between(cin, cout);
            hours = d.toHours();
            totalLaborCost += (hours * l.getPay_rate());

        }

        return totalLaborCost;
    }

}
