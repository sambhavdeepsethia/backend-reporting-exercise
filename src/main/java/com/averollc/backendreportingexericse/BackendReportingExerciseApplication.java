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

        // FetchData.getData();
        SpringApplication.run(BackendReportingExerciseApplication.class, args);

    }
}
