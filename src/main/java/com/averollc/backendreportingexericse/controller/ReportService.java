package com.averollc.backendreportingexericse.controller;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.averollc.backendreportingexericse.model.LaborEntry;
import com.averollc.backendreportingexericse.model.TimeFrame;
import com.averollc.backendreportingexericse.model.TimeInterval;
import com.google.common.base.Preconditions;

/**
 * ReportService contains helper functions which are called by ReportController
 *
 * @author Sambhav D Sethia
 * @version 1.0
 * @since 9/12/2018
 */
public class ReportService
{
    private static final Logger logger = LogManager.getLogger(ReportService.class);

    /**
     * This method accepts start and end datetime and
     * returns a list of TimeFrame objects based on the passed TimeInterval.
     * If an invalid TimeInterval is passed then an IllegalArgumentException will be thrown.
     *
     * @param timeIntervalEnum
     * @param startTime
     * @param endTime
     * @return List<TimeFrame>
     * @throws IllegalArgumentException
     */
    public static List<TimeFrame> getTimeFrameList(final TimeInterval timeIntervalEnum, final String startTime, final String endTime)
        throws IllegalArgumentException
    {
        Preconditions.checkNotNull(timeIntervalEnum);
        Preconditions.checkNotNull(startTime);
        Preconditions.checkNotNull(endTime);

        final DateTimeFormatter formatter = new DateTimeFormatterBuilder().appendInstant(3).toFormatter();
        final ZonedDateTime zonedStartTime = ZonedDateTime.parse(startTime);
        final ZonedDateTime zonedEndTime = ZonedDateTime.parse(endTime);

        Preconditions.checkState(zonedStartTime.isBefore(zonedEndTime), "StartDate %s cannot be after EndDate %s", zonedStartTime, zonedEndTime);

        final List<TimeFrame> timeFrames = new ArrayList<>();
        switch (timeIntervalEnum) {
        case HOUR:
            for (ZonedDateTime date = zonedStartTime; date.isBefore(zonedEndTime); date = date.plusHours(1)) {
                timeFrames.add(new TimeFrame(date.format(formatter).toString(), date.plusHours(1).format(formatter).toString()));
            }

        break;
        case DAY:
            for (ZonedDateTime date = zonedStartTime; date.isBefore(zonedEndTime); date = date.plusDays(1)) {
                timeFrames.add(new TimeFrame(date.format(formatter).toString(), date.plusDays(1).format(formatter).toString()));
            }

        break;
        case WEEK:
            for (ZonedDateTime date = zonedStartTime; date.isBefore(zonedEndTime); date = date.plusWeeks(1)) {
                timeFrames.add(new TimeFrame(date.format(formatter).toString(), date.plusWeeks(1).format(formatter).toString()));
            }

        break;
        case MONTH:
            for (ZonedDateTime date = zonedStartTime; date.isBefore(zonedEndTime); date = date.plusMonths(1)) {
                timeFrames.add(new TimeFrame(date.format(formatter).toString(), date.plusMonths(1).format(formatter).toString()));
            }
        break;

        default:
            // An internal exception is thrown by TimeInterval enum class.

        }
        return timeFrames;
    }

    /**
     * This method adds a List of java.lang.Object and returns their sum in double.
     * It is important to the passed List<Object> should either be Double or Integer
     * else NumberFormartException will be thrown.
     *
     * @return double
     * @throws NumberFormatException
     */
    public static double computeSum(final List<Object> objectList) throws NumberFormatException
    {
        logger.debug("Compute Sum, input list:{}", objectList);
        double sum = 0;
        for (final Object o : CollectionUtils.emptyIfNull(objectList)) {
            sum += Double.parseDouble(o.toString());
        }
        logger.debug("Sum:{}", sum);
        return sum;
    }

    /**
     * This method computes total cost of labor for the give datetime range.
     * 
     * @param        List<LaborEntry>
     * @param String
     *               startTime
     * @param String
     *               endTime
     * @return double
     */
    public static double computeTotalLaborCost(final List<LaborEntry> laborEntires, final String startTime, final String endTime)
    {

        Preconditions.checkNotNull(laborEntires);
        Preconditions.checkNotNull(startTime);
        Preconditions.checkNotNull(endTime);

        final ZonedDateTime zonedStartTime = ZonedDateTime.parse(startTime);
        final ZonedDateTime zonedEndTime = ZonedDateTime.parse(endTime);
        Preconditions.checkState(zonedStartTime.isBefore(zonedEndTime), "StartDate %s cannot be after EndDate %s", zonedStartTime, zonedEndTime);

        double totalLaborCost = 0;

        for (final LaborEntry l : laborEntires) {
            double hours = 0;
            LocalDateTime cin = l.getClock_in().toLocalDateTime();
            LocalDateTime cout = l.getClock_out().toLocalDateTime();

            if (l.getClock_in().isBefore(zonedStartTime)) {
                cin = zonedStartTime.toLocalDateTime();
            }
            if (l.getClock_out().isAfter(zonedEndTime)) {
                cout = zonedEndTime.toLocalDateTime();
            }

            final Duration d = Duration.between(cin, cout);
            hours = d.toHours();
            totalLaborCost += (hours * l.getPay_rate());

        }
        return totalLaborCost;
    }

}
