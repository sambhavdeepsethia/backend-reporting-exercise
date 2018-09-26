package com.averollc.backendreportingexericse.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import org.hamcrest.CoreMatchers;
import org.junit.Test;

import com.averollc.backendreportingexericse.model.TimeFrame;
import com.averollc.backendreportingexericse.model.TimeInterval;

/**
 * JUnit tests for methods in ReportService
 * 
 * @author Sambhav D Sethia
 * @version 1.0
 * @since 9/12/2018
 */
public class ReportServiceTest
{
    @Test
    public void computeSumTest()
    {
        double sum = ReportService.computeSum(null);
        assertEquals(0, sum, 0);

        final List<Object> objectList = new ArrayList<>();
        objectList.add(1);
        objectList.add(2.2);
        sum = ReportService.computeSum(objectList);
        assertEquals(3.2, sum, 0);

    }

    @Test(expected = NumberFormatException.class)
    public void computeSumTest2()
    {
        final List<Object> objectList = new ArrayList<>();
        objectList.add("Sambhav");
        ReportService.computeSum(objectList);

    }

    @Test
    public void getTimeFrameListTest()
    {

        final List<TimeFrame> hours = new ArrayList<>();
        hours.add(new TimeFrame("2018-06-02T00:00:00.000Z", "2018-06-02T01:00:00.000Z"));
        hours.add(new TimeFrame("2018-06-02T01:00:00.000Z", "2018-06-02T02:00:00.000Z"));
        hours.add(new TimeFrame("2018-06-02T02:00:00.000Z", "2018-06-02T03:00:00.000Z"));

        final List<TimeFrame> days = new ArrayList<>();
        days.add(new TimeFrame("2018-06-01T00:00:00.000Z", "2018-06-02T00:00:00.000Z"));
        days.add(new TimeFrame("2018-06-02T00:00:00.000Z", "2018-06-03T00:00:00.000Z"));
        days.add(new TimeFrame("2018-06-03T00:00:00.000Z", "2018-06-04T00:00:00.000Z"));

        final List<TimeFrame> weeks = new ArrayList<>();
        weeks.add(new TimeFrame("2018-06-01T00:00:00.000Z", "2018-06-08T00:00:00.000Z"));
        weeks.add(new TimeFrame("2018-06-08T00:00:00.000Z", "2018-06-15T00:00:00.000Z"));
        weeks.add(new TimeFrame("2018-06-15T00:00:00.000Z", "2018-06-22T00:00:00.000Z"));

        final List<TimeFrame> months = new ArrayList<>();
        months.add(new TimeFrame("2018-06-01T00:00:00.000Z", "2018-07-01T00:00:00.000Z"));
        months.add(new TimeFrame("2018-07-01T00:00:00.000Z", "2018-08-01T00:00:00.000Z"));
        months.add(new TimeFrame("2018-08-01T00:00:00.000Z", "2018-09-01T00:00:00.000Z"));

        final List<TimeFrame> actualHours = ReportService.getTimeFrameList(TimeInterval.HOUR, "2018-06-02T00:00:00.000Z", "2018-06-02T03:00:00.000Z");
        assertThat(actualHours, CoreMatchers.is(hours));

        final List<TimeFrame> actualDays = ReportService.getTimeFrameList(TimeInterval.DAY, "2018-06-01T00:00:00.000Z", "2018-06-04T00:00:00.000Z");
        assertThat(actualDays, CoreMatchers.is(days));

        final List<TimeFrame> actualWeeks = ReportService.getTimeFrameList(TimeInterval.WEEK, "2018-06-01T00:00:00.000Z", "2018-06-22T00:00:00.000Z");
        assertThat(actualWeeks, CoreMatchers.is(weeks));

        final List<TimeFrame> actualMonths = ReportService.getTimeFrameList(TimeInterval.MONTH, "2018-06-01T00:00:00.000Z", "2018-09-01T00:00:00.000Z");
        assertThat(actualMonths, CoreMatchers.is(months));

    }

    @Test(expected = IllegalStateException.class)
    public void getTimeFrameListTest2()
    {
        ReportService.getTimeFrameList(TimeInterval.DAY, "2018-06-02T00:00:00.000Z", "2018-06-01T00:00:00.000Z");

    }

    @Test(expected = DateTimeParseException.class)
    public void getTimeFrameListTest3()
    {
        ReportService.getTimeFrameList(TimeInterval.DAY, "startTime", "endTime");

    }

}
