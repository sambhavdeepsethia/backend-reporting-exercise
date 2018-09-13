package dao;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.MediaType;

@RestController
public class DataConsumer {

	// final static String[] posAPIs = {"businesses", "menuItems", "checks"};
	public static void dataProcessor(String baseDir, String baseURI, String[] posAPIs, String headerFieldName,
			String headerFieldValue) {

		for (String api : posAPIs) {
			RestTemplate restTemplate = new RestTemplate();
			HttpHeaders headers = new HttpHeaders();
			headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
			headers.add(headerFieldName, headerFieldValue);
			HttpEntity<String> entity = new HttpEntity<String>(headers);
			String data = restTemplate.exchange(baseURI + api, HttpMethod.GET, entity, String.class).getBody();
			dataWriter(data, baseDir + File.separator + api + File.separator + api + ".json");

		}

	}

	private static void dataWriter(String data, String filePath) {

		try {
			Files.write(Paths.get(filePath), data.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
