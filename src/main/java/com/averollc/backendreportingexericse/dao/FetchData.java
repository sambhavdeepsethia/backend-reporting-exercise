package com.averollc.backendreportingexericse.dao;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.averollc.backendreportingexericse.model.Check;
import com.averollc.backendreportingexericse.model.LaborEntry;
import com.averollc.backendreportingexericse.model.OrderedItem;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
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

    public static List<LaborEntry> getLaborEntries(final String business_id, final String startTime, final String endTime)
        throws IllegalArgumentException, IOException
    {
        final File laborEntriesJson = new File(baseDir + "laborEntries" + File.separator + "laborEntries.json");
        final ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        final Filter filter1 = Filter.filter(Criteria.where("business_id").is(business_id).and("clock_in").lte(startTime).and("clock_out").gte(startTime));
        final Filter filter2 = Filter
            .filter(Criteria.where("business_id").is(business_id).and("clock_in").gt(startTime).and("clock_in").lte(endTime).and("clock_in").lt("clock_out"));

        final List<LaborEntry> laborEntriesWithFilter1 = mapper.convertValue(JsonPath.parse(laborEntriesJson).read("$.data[?]", filter1),
            new TypeReference<List<LaborEntry>>() {});
        final List<LaborEntry> laborEntriesWithFilter2 = mapper.convertValue(JsonPath.parse(laborEntriesJson).read("$.data[?]", filter2),
            new TypeReference<List<LaborEntry>>() {});

        final List<LaborEntry> totalLaborEntries = new ArrayList<>(laborEntriesWithFilter1);
        totalLaborEntries.addAll(laborEntriesWithFilter2);

        return totalLaborEntries;

    }

    public static List<OrderedItem> getOrderedItems(final String business_id, final List<String> checkIDs) throws IllegalArgumentException, IOException
    {
        final File orderedItemsJson = new File(baseDir + "orderedItems" + File.separator + "orderedItems.json");
        final ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        final Filter filter = Filter.filter(Criteria.where("business_id").is(business_id).and("check_id").in(checkIDs));

        final List<OrderedItem> orderedItems = mapper.convertValue(JsonPath.parse(orderedItemsJson).read("$.data[?]", filter),
            new TypeReference<List<OrderedItem>>() {});

        return orderedItems;
    }

    public static List<Check> getChecks(final String business_id, final String startTime, final String endTime) throws IOException
    {
        final File checksJson = new File(baseDir + "checks" + File.separator + "checks.json");
        final ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());

        final Filter checkFilter = Filter
            .filter(Criteria.where("business_id").is(business_id).and("closed").is(true).and("closed_at").gte(startTime).and("closed_at").lte(endTime));
        final List<Check> checks = mapper.convertValue(JsonPath.parse(checksJson).read("$.data[?]", checkFilter), new TypeReference<List<Check>>() {});

        return checks;
    }

    // public static double get

}
