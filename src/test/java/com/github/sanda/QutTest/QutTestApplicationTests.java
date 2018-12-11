package com.github.sanda.QutTest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Arrays;
import java.util.Date;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class QutTestApplicationTests {
	@Autowired
	PollRepository pollRepository;

	@Autowired
	PollResources pollResources;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void getQuestionsTest() {
		//loading data.sql
		String body = this.restTemplate.getForObject("/questions", String.class);
		assertEquals("[{\"id\":1,\"choices\":[{\"choice\":\"Swift\",\"votes\":256},{\"choice\":\"Python\",\"votes\":1024},{\"choice\":\"Objective-C\",\"votes\":512},{\"choice\":\"Ruby\",\"votes\":256}],\"question\":\"Favourite programming language?\",\"published_at\":\"2015-08-05T08:40:51.620+0000\"}]",body);

	}

	@Test
	public void postQuestionTest() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<String> post = new HttpEntity<String>(
		"{\n" +
			"            \"question\": \"Favourite programming language?\",\n" +
			"            \"choices\": [\n" +
			"                \"Swift\",\n" +
			"                \"Python\",\n" +
			"                \"Objective-C\",\n" +
			"                \"Ruby\"\n" +
			"            ]\n" +
			"        }\n", headers);
		ResponseEntity<String> response = this.restTemplate.postForEntity("/questions", post, String.class);
		assertEquals(201, response.getStatusCodeValue());
		assertTrue(response.getHeaders().get("Location").get(0).endsWith("/questions/2"));
	}
}
