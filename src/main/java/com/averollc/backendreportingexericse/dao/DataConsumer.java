package com.averollc.backendreportingexericse.dao;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.jayway.jsonpath.JsonPath;

@RestController
public class DataConsumer
{

    // final static String[] posAPIs = {"businesses", "menuItems", "checks"};
    public static void dataProcessor(final String baseDir, final String baseURI, final String[] posAPIs, final String headerFieldName,
        final String headerFieldValue) throws IOException
    {

        for (final String api : posAPIs) {
            final RestTemplate restTemplate = new RestTemplate();
            final HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
            headers.add(headerFieldName, headerFieldValue);
            final HttpEntity<String> entity = new HttpEntity<String>(headers);
            final String jsonCount = restTemplate.exchange(baseURI + api + "?limit=0", HttpMethod.GET, entity, String.class).getBody();
            final int count = JsonPath.read(jsonCount, "$.count");
            final String fileName = baseDir + File.separator + api + File.separator + api + ".json";
            final File file = new File(fileName);
            if (!file.exists()) {
                file.createNewFile();
            }
            System.out.println("Count: " + count);
            final String jsonData = restTemplate.exchange(baseURI + api + "?limit=" + count, HttpMethod.GET, entity, String.class).getBody();
            dataWriter(jsonData, fileName);

            // final int limit = 500;
            // int index = 0;
            // int offset = 0;
            //
            // System.out.println("Count: " + count);
            // while (index <= count) {
            //
            // final String jsonData = restTemplate.exchange(baseURI + api + "?limit=" + limit + "&offset=" + offset, HttpMethod.GET, entity, String.class)
            // .getBody();
            // dataWriter(jsonData, fileName);
            // index = index + limit;
            // offset = offset + limit;
            //
            // }

        }

    }

    private static void dataWriter(final String data, final String fileName)
    {

        try {

            Files.write(Paths.get(fileName), data.getBytes(), StandardOpenOption.APPEND);
        }
        catch (final IOException e) {
            e.printStackTrace();
        }

    }

}
