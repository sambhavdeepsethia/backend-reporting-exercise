package com.averollc.backendreportingexericse;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.averollc.backendreportingexericse.dao.FetchData;

@SpringBootApplication
public class BackendReportingExerciseApplication
{

    public static void main(final String[] args) throws IOException
    {

        // final String baseDir = System.getProperty("user.dir") + File.separator + "data" + File.separator;
        // final String business_id = "b2aeb27b-c85c-4ad8-83d4-d9511063d418";
        // final String startTime = "2018-05-02T03:00:00.000Z";
        // final String endTime = "2018-05-02T04:00:00.000Z";
        //
        // final File laborEntriesJson = new File(baseDir + "laborEntries" + File.separator + "laborEntries.json");
        // final File checksJson = new File(baseDir + "checks" + File.separator + "checks.json");
        // // Object document = Configuration.defaultConfiguration().jsonProvider().parse(json);
        //
        // final Filter filter = Filter.filter(Criteria.where("business_id").is(business_id).and("clock_in").lte(startTime).and("clock_out").gte(endTime));
        // final List<Double> payRate = JsonPath.parse(laborEntriesJson).read("$.data[?].pay_rate", filter);
        //
        // final Filter checkFilter = Filter
        // .filter(Criteria.where("business_id").is(business_id).and("closed").is(true).and("closed_at").gte(startTime).and("closed_at").lte(endTime));
        // final List<String> checkIDs = JsonPath.parse(checksJson).read("$.data[?].id", checkFilter);
        //
        // System.out.println("checks: " + checkIDs);

        FetchData.getData();
        SpringApplication.run(BackendReportingExerciseApplication.class, args);

    }
}
