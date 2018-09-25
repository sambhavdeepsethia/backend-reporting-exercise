package com.averollc.backendreportingexericse.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.CoreMatchers;
import org.junit.Test;

import com.averollc.backendreportingexericse.model.TimeFrame;
import com.averollc.backendreportingexericse.model.TimeInterval;

public class ReportServiceTest
{

    @Test(expected = NumberFormatException.class)
    public void computeSumTest()
    {
        double sum = ReportService.computeSum(null);
        assertEquals(0, sum, 0);

        final List<Object> objectList = new ArrayList<>();
        objectList.add(1);
        objectList.add(2.2);
        sum = ReportService.computeSum(objectList);
        assertEquals(3.2, sum, 0);

        objectList.add("Sambhav");
        sum = ReportService.computeSum(objectList);
        // assertEquals(3.2, sum, 0);
    }

    @Test
    public void getTimeFrameListTest() throws Exception
    {
        // ReportService.getTimeFrameList(TimeInterval.DAY, "startTime", "endTime");

        final List<TimeFrame> expected = new ArrayList<>();
        expected.add(new TimeFrame("2018-06-02T00:00:00.000Z", "2018-06-02T01:00:00.000Z"));
        expected.add(new TimeFrame("2018-06-02T01:00:00.000Z", "2018-06-02T02:00:00.000Z"));
        expected.add(new TimeFrame("2018-06-02T02:00:00.000Z", "2018-06-02T03:00:00.000Z"));

        final List<TimeFrame> actual = ReportService.getTimeFrameList(TimeInterval.HOUR, "2018-06-02T00:00:00.000Z", "2018-06-02T03:00:00.000Z");
        System.out.println(actual);
        assertThat(actual, CoreMatchers.is(expected));
    }

}
