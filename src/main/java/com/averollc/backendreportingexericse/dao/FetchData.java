package com.averollc.backendreportingexericse.dao;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.jayway.jsonpath.Criteria;
import com.jayway.jsonpath.Filter;
import com.jayway.jsonpath.JsonPath;

/**
 * @author Merceed
 */
public class FetchData
{

    private static final String baseDir = System.getProperty("user.dir") + File.separator + "data" + File.separator;
    private static final String baseURI = "https://secret-lake-26389.herokuapp.com/";
    private static final String[] posAPIs = { "businesses", "menuItems", "checks", "orderedItems", "employees", "laborEntries" };
    // final String[] posAPIs = { "businesses", "menuItems", "checks" };
    private static final String headerFieldName = "Authorization";
    private static final String headerFieldValue = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpYXQiOjE1MzYyNjE2NDUsImV4cCI6MTUzODg1MzY0NX0.2VYZV5uW24EjdrCC-kJVfYJXSrf5qw4qSfJbqWl7q6o";

    /**
     * @throws IOException
     */
    public static void getData() throws IOException
    {

        CreateDirectories.createDirectories(baseDir, posAPIs);
        DataConsumer.dataProcessor(baseDir, baseURI, posAPIs, headerFieldName, headerFieldValue);

    }

    public static List<String> getCheckIDs(final String business_id, final String startTime, final String endTime) throws IOException
    {
        final File checksJson = new File(baseDir + "checks" + File.separator + "checks.json");
        final Filter checkFilter = Filter
            .filter(Criteria.where("business_id").is(business_id).and("closed").is(true).and("closed_at").gte(startTime).and("closed_at").lte(endTime));
        final List<String> checkIDs = JsonPath.parse(checksJson).read("$.data[?].id", checkFilter);

        return checkIDs;
    }

    /**
     * @param business_id
     * @param startTime
     * @param endTime
     * @return
     * @throws IOException
     */
    public static List<Object> getPayRateByHour(final String business_id, final String startTime, final String endTime) throws IOException
    {

        final File laborEntriesJson = new File(baseDir + "laborEntries" + File.separator + "laborEntries.json");
        final Filter payRateFilter = Filter.filter(Criteria.where("business_id").is(business_id).and("clock_in").lte(startTime).and("clock_out").gte(endTime));
        final List<Object> payRates = JsonPath.parse(laborEntriesJson).read("$.data[?].pay_rate", payRateFilter);
        return payRates;

    }

    public static List<Object> getPricesForChecks(final String business_id, final List<String> checkIDs) throws IOException
    {
        final File orderedItemsJson = new File(baseDir + "orderedItems" + File.separator + "orderedItems.json");
        final Filter pricesFilter = Filter.filter(Criteria.where("business_id").is(business_id).and("voided").is(false).and("check_id").in(checkIDs));
        final List<Object> prices = JsonPath.parse(orderedItemsJson).read("$.data[?].price", pricesFilter);

        return prices;

    }

    public static List<Object> getPayRateByDay(final String business_id, final String startTime, final String endTime) throws IOException
    {

        final File laborEntriesJson = new File(baseDir + "laborEntries" + File.separator + "laborEntries.json");
        final Filter filter1 = Filter.filter(Criteria.where("business_id").is(business_id).and("clock_in").lte(startTime).and("clock_out").gte(startTime));
        final Filter filter2 = Filter
            .filter(Criteria.where("business_id").is(business_id).and("clock_in").gt(startTime).and("clock_in").lte(endTime).and("clock_in").lt("clock_out"));
        // final Filter filter3 = Filter.filter(Criteria.where("business_id").is(business_id).and("clock_in").lte(startTime).and("clock_out").gte(endTime));
        // final Filter filter4 = Filter.filter(Criteria.where("business_id").is(business_id).and("clock_in").gte(startTime).and("clock_out").gte(endTime));

        final List<String> employeesWithFilter1 = JsonPath.parse(laborEntriesJson).read("$.data[?]", filter1);

        return null;

    }

    // final List<String> x = JsonPath.read(json, "$.data[?(@.closed_at>=\"2018-06-02T00:00:00.000Z\")]");

}
