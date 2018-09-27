package com.averollc.backendreportingexericse;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.averollc.backendreportingexericse.dao.FetchData;

/**
 * This is the main Spring Boot application class
 *
 * @author Sambhav D Sethia
 * @version 1.0
 * @since 9/12/2018
 */
@SpringBootApplication
public class BackendReportingExerciseApplication
{

    public static void main(final String[] args) throws IOException
    {
        FetchData.getData();
        SpringApplication.run(BackendReportingExerciseApplication.class, args);

    }
}
