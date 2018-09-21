package com.averollc.backendreportingexericse;

import java.io.File;
import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BackendReportingExerciseApplication
{

    public static void main(final String[] args) throws IOException
    {

        final String baseDir = System.getProperty("user.dir") + File.separator + "data" + File.separator;
        final String business_id = "b2aeb27b-c85c-4ad8-83d4-d9511063d418";
        final String startTime = "2018-06-01T00:00:00.000Z";
        final String endTime = "2018-06-02T00:00:00.000Z";
        final File laborEntriesJson = new File(baseDir + "laborEntries" + File.separator + "laborEntries.json");
        // final File checksJson = new File(baseDir + "checks" + File.separator + "checks.json");
        // final File orderedItemsJson = new File(baseDir + "orderedItems" + File.separator + "orderedItems.json");
        //
        // final Filter filter = Filter.filter(Criteria.where("business_id").is(business_id).and("clock_in").lte(startTime).and("clock_out").gte(endTime));
        // List<Object> payRate = new ArrayList<>();
        // payRate = JsonPath.parse(laborEntriesJson).read("$.data[?]", filter);
        //
        // final Filter checkFilter = Filter
        // .filter(Criteria.where("business_id").is(business_id).and("closed").is(true).and("closed_at").gte(startTime).and("closed_at").lte(endTime));
        // final List<String> checkIDs = JsonPath.parse(checksJson).read("$.data[?].id", checkFilter);
        // final Filter pricesFilter = Filter.filter(Criteria.where("business_id").is(business_id).and("voided").is(false).and("check_id").in(checkIDs));
        // final List<Object> prices = JsonPath.parse(orderedItemsJson).read("$.data[?].price", pricesFilter);
        //
        // System.out.println(payRate);
        // System.out.println(prices);

        // final ObjectMapper mapper = new ObjectMapper();
        // mapper.registerModule(new JavaTimeModule());
        // final Filter filter1 = Filter.filter(Criteria.where("business_id").is(business_id).and("clock_in").lte(startTime).and("clock_out").gte(startTime));
        // final Filter filter2 = Filter
        // .filter(Criteria.where("business_id").is(business_id).and("clock_in").gt(startTime).and("clock_in").lte(endTime).and("clock_in").lt("clock_out"));
        //
        // final List<LaborEntry> laborEntriesWithFilter1 = mapper.convertValue(JsonPath.parse(laborEntriesJson).read("$.data[?]", filter1),
        // new TypeReference<List<LaborEntry>>() {});
        // final List<LaborEntry> laborEntriesWithFilter2 = mapper.convertValue(JsonPath.parse(laborEntriesJson).read("$.data[?]", filter2),
        // new TypeReference<List<LaborEntry>>() {});
        //
        // laborEntriesWithFilter1.addAll(laborEntriesWithFilter2);
        //
        // double totalLaborCost = 0;
        // final ZonedDateTime start = ZonedDateTime.parse(startTime);
        // final ZonedDateTime end = ZonedDateTime.parse(endTime);
        // System.out.println("laborEntries: " + laborEntriesWithFilter1);
        //
        // for (final LaborEntry l : laborEntriesWithFilter1) {
        // double hours = 0;
        // LocalDateTime cin = l.getClock_in().toLocalDateTime();
        // LocalDateTime cout = l.getClock_out().toLocalDateTime();
        //
        // if (l.getClock_in().isBefore(start)) {
        // cin = start.toLocalDateTime();
        // }
        // if (l.getClock_out().isAfter(end)) {
        // cout = end.toLocalDateTime();
        // }
        //
        // final Duration d = Duration.between(cin, cout);
        // hours = d.toHours();
        // System.out.println("cout: " + cout + ", cin: " + cin + ", hours: " + hours);
        // totalLaborCost += (hours * l.getPay_rate());
        // }
        //
        // System.out.println("totalLaborCost: " + totalLaborCost);

        // FetchData.getData();
        SpringApplication.run(BackendReportingExerciseApplication.class, args);

    }
}
