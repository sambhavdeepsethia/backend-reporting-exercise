package com.averollc.backendreportingexericse.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.averollc.backendreportingexericse.dao.FetchData;
import com.averollc.backendreportingexericse.model.Data;
import com.averollc.backendreportingexericse.model.LaborCostPercentage;
import com.averollc.backendreportingexericse.model.LaborEntry;
import com.averollc.backendreportingexericse.model.ReportingAttributes;
import com.averollc.backendreportingexericse.model.TimeFrame;
import com.averollc.backendreportingexericse.model.TimeInterval;

@RestController
public class ReportController
{
    Logger logger = LogManager.getLogger(ReportController.class);

    @RequestMapping(method = RequestMethod.GET, value = "/reporting")
    public ReportingAttributes getLaborCostPercentage(@RequestParam("business_id") final String business_id, @RequestParam("report") final String report,
        @RequestParam("timeInterval") final String timeInterval, @RequestParam("start") final String startTime, @RequestParam("end") final String endTime)
        throws Exception
    {
        logger.info("Reporting request received for business_id:{} report:{} timeInterval:{} startTime:{} endTime:{}", business_id, report, timeInterval,
            startTime, endTime);
        ReportingAttributes reportingAttributes;

        if (report.equalsIgnoreCase("LCP")) {
            final TimeInterval timeIntervalEnum = TimeInterval.valueOf(timeInterval.toUpperCase());
            reportingAttributes = computeLCP(business_id, report, timeInterval, timeIntervalEnum, startTime, endTime);
        }
        else if (report.equalsIgnoreCase("FCP")) {
            reportingAttributes = computeFCP(business_id, timeInterval, startTime, endTime);
        }
        else if (report.equalsIgnoreCase("EGS")) {
            reportingAttributes = computeEGS(business_id, timeInterval, startTime, endTime);
        }
        else {
            throw new Exception("Incorrect Report type passed");
        }

        return reportingAttributes;
    }

    // private ReportingAttributes computeLCP(final String business_id, final String report, final String timeInterval, final String startTime,
    // final String endTime) throws Exception
    // {
    // ReportingAttributes reportingAttributes;
    // final TimeInterval timeIntervalEnum = TimeInterval.valueOf(timeInterval.toUpperCase());
    // switch (timeIntervalEnum) {
    // case HOUR:
    // reportingAttributes = computeLCP(business_id, report, timeInterval, timeIntervalEnum, startTime, endTime);
    // break;
    // case DAY:
    // reportingAttributes = computeLCP(business_id, report, timeInterval, timeIntervalEnum, startTime, endTime);
    // break;
    // case WEEK:
    // reportingAttributes = computeLCP(business_id, report, timeInterval, timeIntervalEnum, startTime, endTime);
    // break;
    // case MONTH:
    // reportingAttributes = computeLCP(business_id, report, timeInterval, timeIntervalEnum, startTime, endTime);
    // break;
    // default:
    // throw new Exception("Invalid timeInterval");
    //
    // }
    // return reportingAttributes;
    // }

    private ReportingAttributes computeFCP(final String business_id, final String timeInterval, final String startTime, final String endTime) throws Exception
    {

        return null;
    }

    private ReportingAttributes computeEGS(final String business_id, final String timeInterval, final String startTime, final String endTime) throws Exception
    {
        return null;
    }

    private ReportingAttributes computeLCPByHour(final String business_id, final String report, final String timeInterval, final TimeInterval timeIntervalEnum,
        final String startTime, final String endTime) throws Exception
    {
        final ReportingAttributes reportingAttributes;
        final List<Data> data = new ArrayList<>();
        final List<TimeFrame> timeframes = ReportService.getTimeFrameList(timeIntervalEnum, startTime, endTime);

        for (final TimeFrame t : timeframes) {

            final List<Object> payRates = FetchData.getPayRateByHour(business_id, t.getStart(), t.getEnd());
            final List<String> checkIDs = FetchData.getCheckIDs(business_id, t.getStart(), t.getEnd());
            final List<Object> prices = FetchData.getPricesForChecks(business_id, checkIDs);

            final double totalPay = ReportService.computeSum(payRates);
            final double totalPrice = ReportService.computeSum(prices);
            final double value;
            if ((totalPrice == 0) || (totalPay == 0)) {
                value = 0;
            }
            else {
                value = ((totalPay / totalPrice) * 100);

            }
            System.out.println("totalLaborCost: " + totalPay);
            System.out.println("totalPrice: " + totalPrice);
            data.add(new Data(t, Math.round(value)));
        }

        reportingAttributes = new LaborCostPercentage(report, timeInterval, data);

        return reportingAttributes;

    }

    private ReportingAttributes computeLCP(final String business_id, final String report, final String timeInterval, final TimeInterval timeIntervalEnum,
        final String startTime, final String endTime) throws Exception
    {
        final ReportingAttributes reportingAttributes;
        final List<Data> data = new ArrayList<>();
        final List<TimeFrame> timeframes = ReportService.getTimeFrameList(timeIntervalEnum, startTime, endTime);

        for (final TimeFrame t : timeframes) {

            final List<LaborEntry> totalLaborEntries = FetchData.getLaborEntries(business_id, t.getStart(), t.getEnd());
            final double totalLaborCost = ReportService.computeTotalLaborCost(totalLaborEntries, t.getStart(), t.getEnd());
            final List<String> checkIDs = FetchData.getCheckIDs(business_id, t.getStart(), t.getEnd());
            final List<Object> prices = FetchData.getPricesForChecks(business_id, checkIDs);
            final double totalPrice = ReportService.computeSum(prices);
            final double value;

            if ((totalLaborCost == 0) || (totalPrice == 0)) {
                value = 0;
            }
            else {
                value = ((totalLaborCost / totalPrice) * 100);

            }
            System.out.println("totalLaborCost: " + totalLaborCost);
            System.out.println("totalPrice: " + totalPrice);
            data.add(new Data(t, value));
        }

        reportingAttributes = new LaborCostPercentage(report, timeInterval, data);

        return reportingAttributes;
    }

}
