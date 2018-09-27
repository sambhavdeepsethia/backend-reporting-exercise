package com.averollc.backendreportingexericse.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.averollc.backendreportingexericse.dao.FetchData;
import com.averollc.backendreportingexericse.model.Check;
import com.averollc.backendreportingexericse.model.Data;
import com.averollc.backendreportingexericse.model.EmployeeData;
import com.averollc.backendreportingexericse.model.EmployeeGrossSales;
import com.averollc.backendreportingexericse.model.FoodCostPercentage;
import com.averollc.backendreportingexericse.model.LaborCostPercentage;
import com.averollc.backendreportingexericse.model.LaborEntry;
import com.averollc.backendreportingexericse.model.OrderedItem;
import com.averollc.backendreportingexericse.model.ReportingAttributes;
import com.averollc.backendreportingexericse.model.TimeFrame;
import com.averollc.backendreportingexericse.model.TimeInterval;
import com.google.common.base.Preconditions;

/**
 * The Rest Controller class which handles all the incoming GET request mapped to /reporting
 *
 * @author Sambhav D Sethia
 * @version 1.0
 * @since 9/12/2018
 */
@RestController
public class ReportController
{
    private static final Logger logger = LogManager.getLogger(ReportController.class);

    /**
     * @param business_id
     * @param report
     * @param timeInterval
     * @param startTime
     * @param endTime
     * @return ReportingAttributes
     * @throws Exception
     */
    @RequestMapping(method = RequestMethod.GET, value = "/reporting")
    public ReportingAttributes getReport(@RequestParam("business_id") final String business_id, @RequestParam("report") final String report,
        @RequestParam("timeInterval") final String timeInterval, @RequestParam("start") final String startTime, @RequestParam("end") final String endTime)
        throws Exception
    {
        Preconditions.checkNotNull(business_id);
        Preconditions.checkNotNull(report);
        Preconditions.checkNotNull(timeInterval);
        Preconditions.checkNotNull(startTime);
        Preconditions.checkNotNull(endTime);

        logger.info("Reporting request received for business_id:{} report:{} timeInterval:{} startTime:{} endTime:{}", business_id, report, timeInterval,
            startTime, endTime);
        ReportingAttributes reportingAttributes;
        final TimeInterval timeIntervalEnum = TimeInterval.valueOf(timeInterval.toUpperCase());

        if (report.equalsIgnoreCase("LCP")) {
            reportingAttributes = computeLCP(business_id, report, timeInterval, timeIntervalEnum, startTime, endTime);
        }
        else if (report.equalsIgnoreCase("FCP")) {
            reportingAttributes = computeFCP(business_id, report, timeInterval, timeIntervalEnum, startTime, endTime);
        }
        else if (report.equalsIgnoreCase("EGS")) {
            reportingAttributes = computeEGS(business_id, report, timeInterval, timeIntervalEnum, startTime, endTime);
        }
        else {
            throw new Exception("Incorrect Report type passed");
        }

        return reportingAttributes;
    }

    private ReportingAttributes computeLCP(final String business_id, final String report, final String timeInterval, final TimeInterval timeIntervalEnum,
        final String startTime, final String endTime) throws Exception
    {
        logger.debug("Entering computeLCP...");
        final ReportingAttributes reportingAttributes;
        final List<Data> data = new ArrayList<>();
        final List<TimeFrame> timeframes = ReportService.getTimeFrameList(timeIntervalEnum, startTime, endTime);
        logger.debug("Timeframes {}, StartTime {}", timeframes, startTime);
        for (final TimeFrame t : timeframes) {

            final List<LaborEntry> laborEntires = FetchData.getLaborEntries(business_id, t.getStart(), t.getEnd());
            final double totalLaborCost = ReportService.computeTotalLaborCost(laborEntires, t.getStart(), t.getEnd());
            final List<String> checkIDs = FetchData.getCheckIDs(business_id, t.getStart(), t.getEnd());
            final List<Object> prices = FetchData.getPricesForChecks(business_id, checkIDs);
            final double totalPrice = ReportService.computeSum(prices);
            final double value;

            if ((totalLaborCost == 0) && (totalPrice == 0)) {
                value = 0;
            }
            else if ((totalLaborCost != 0) && (totalPrice == 0)) {
                value = 100;
            }
            else {
                value = ((totalLaborCost / totalPrice) * 100);

            }
            data.add(new Data(t, value));
        }

        reportingAttributes = new LaborCostPercentage(report, timeInterval, data);

        return reportingAttributes;
    }

    private ReportingAttributes computeFCP(final String business_id, final String report, final String timeInterval, final TimeInterval timeIntervalEnum,
        final String startTime, final String endTime) throws Exception
    {

        final ReportingAttributes reportingAttributes;
        final List<Data> data = new ArrayList<>();
        final List<TimeFrame> timeframes = ReportService.getTimeFrameList(timeIntervalEnum, startTime, endTime);

        for (final TimeFrame t : timeframes) {

            final List<String> checkIDs = FetchData.getCheckIDs(business_id, t.getStart(), t.getEnd());
            final List<OrderedItem> orderedItems = FetchData.getOrderedItems(business_id, checkIDs);
            double totalCost = 0;
            double totalPrice = 0;
            double value;

            for (final OrderedItem o : orderedItems) {
                if (!o.isVoided()) {
                    totalPrice += o.getPrice();
                }
                totalCost += o.getCost();
            }
            if ((totalCost == 0) && (totalPrice == 0)) {
                value = 0;
            }
            else if ((totalCost != 0) && (totalPrice == 0)) {
                value = 100;
            }
            else {
                value = (totalCost / totalPrice) * 100;
            }
            data.add(new Data(t, value));
        }
        reportingAttributes = new FoodCostPercentage(report, timeInterval, data);
        return reportingAttributes;

    }

    private ReportingAttributes computeEGS(final String business_id, final String report, final String timeInterval, final TimeInterval timeIntervalEnum,
        final String startTime, final String endTime) throws Exception
    {
        ReportingAttributes reportingAttributes;
        final List<EmployeeData> employeeData = new ArrayList<>();
        final List<TimeFrame> timeframes = ReportService.getTimeFrameList(timeIntervalEnum, startTime, endTime);

        for (final TimeFrame t : timeframes) {

            final List<Check> checks = FetchData.getChecks(business_id, t.getStart(), t.getEnd());
            final Map<String, String> employeeMap = new HashMap<>();
            final List<String> checkIDs = new ArrayList<>();
            checks.forEach(c -> {
                checkIDs.add(c.getId());
                employeeMap.put(c.getEmployee_id(), c.getName());
            });

            final List<OrderedItem> orderedItems = FetchData.getOrderedItems(business_id, checkIDs);
            final Map<String, Double> employeeGrossSalesMap = new HashMap<>();
            for (final OrderedItem o : orderedItems) {
                if (!o.isVoided()) {
                    if (employeeGrossSalesMap.containsKey(o.getEmployee_id())) {
                        final double value = employeeGrossSalesMap.get(o.getEmployee_id());
                        employeeGrossSalesMap.put(o.getEmployee_id(), value + o.getPrice());
                    }
                    else {
                        employeeGrossSalesMap.put(o.getEmployee_id(), o.getPrice());
                    }
                }
            }

            for (final Map.Entry<String, String> entry : employeeMap.entrySet()) {

                final String name = entry.getValue();
                final double egs = employeeGrossSalesMap.get(entry.getKey());

                employeeData.add(new EmployeeData(t, egs, name));

            }
        }
        Collections.sort(employeeData, (o1, o2) -> o1.getEmployee().compareTo(o2.getEmployee()));
        reportingAttributes = new EmployeeGrossSales(report, timeInterval, employeeData);
        return reportingAttributes;
    }
}
