package com.averollc.backendreportingexericse.dao;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.jayway.jsonpath.JsonPath;

/**
 * This class contains method which fetches and stores data required by the application.
 *
 * @author Sambhav D Sethia
 * @version 1.0
 * @since 9/12/2018
 */
@RestController
public class DataConsumer
{

    /**
     * Invokes the POS REST APIs and fetches all the data and stores them as .json files on disk.
     *
     * @param baseDir
     * @param baseURI
     * @param posAPIs
     * @param headerFieldName
     * @param headerFieldValue
     * @throws IOException
     */
    public static void dataProcessor(final String baseDir, final String baseURI, final String[] posAPIs, final String headerFieldName,
        final String headerFieldValue) throws IOException
    {

        for (final String api : posAPIs) {
            final RestTemplate restTemplate = new RestTemplate();
            final HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
            headers.add(headerFieldName, headerFieldValue);
            final HttpEntity<String> entity = new HttpEntity<>(headers);
            final String jsonCount = restTemplate.exchange(baseURI + api + "?limit=0", HttpMethod.GET, entity, String.class).getBody();
            final int count = JsonPath.read(jsonCount, "$.count");
            final String fileName = baseDir + File.separator + api + File.separator + api + ".json";
            final File file = new File(fileName);
            if (!file.exists()) {
                file.createNewFile();
            }
            final String jsonData = restTemplate.exchange(baseURI + api + "?limit=" + count, HttpMethod.GET, entity, String.class).getBody();
            dataWriter(jsonData, fileName);
        }

    }

    private static void dataWriter(final String data, final String fileName)
    {
        try {

            Files.write(Paths.get(fileName), data.getBytes());
        }
        catch (final IOException e) {
            e.printStackTrace();
        }

    }

}
