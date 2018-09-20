package com.averollc.backendreportingexericse;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.jayway.jsonpath.Criteria;
import com.jayway.jsonpath.Filter;
import com.jayway.jsonpath.JsonPath;

@SpringBootApplication
public class BackendReportingExerciseApplication
{

    public static void main(final String[] args) throws IOException
    {

        final String baseDir = System.getProperty("user.dir") + File.separator + "data" + File.separator;
        final String business_id = "b2aeb27b-c85c-4ad8-83d4-d9511063d418";
        final String startTime = "2018-05-02T03:00:00.000Z";
        final String endTime = "2018-05-02T04:00:00.000Z";

        final File laborEntriesJson = new File(baseDir + "laborEntries" + File.separator + "laborEntries.json");
        final File checksJson = new File(baseDir + "checks" + File.separator + "checks.json");
        final File orderedItemsJson = new File(baseDir + "orderedItems" + File.separator + "orderedItems.json");

        final Filter filter = Filter.filter(Criteria.where("business_id").is(business_id).and("clock_in").lte(startTime).and("clock_out").gte(endTime));
        List<Object> payRate = new ArrayList<>();
        payRate = JsonPath.parse(laborEntriesJson).read("$.data[?]", filter);

        final Filter checkFilter = Filter
            .filter(Criteria.where("business_id").is(business_id).and("closed").is(true).and("closed_at").gte(startTime).and("closed_at").lte(endTime));
        final List<String> checkIDs = JsonPath.parse(checksJson).read("$.data[?].id", checkFilter);
        final Filter pricesFilter = Filter.filter(Criteria.where("business_id").is(business_id).and("voided").is(false).and("check_id").in(checkIDs));
        final List<Object> prices = JsonPath.parse(orderedItemsJson).read("$.data[?].price", pricesFilter);

        System.out.println(payRate);
        System.out.println(prices);
        // FetchData.getData();
        // SpringApplication.run(BackendReportingExerciseApplication.class, args);

    }
}
