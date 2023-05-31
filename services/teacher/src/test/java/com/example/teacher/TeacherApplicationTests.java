package com.example.teacher;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;


import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;



@SpringBootTest
class TeacherApplicationTests {

	@Test
	void contextLoads() {
	}



	@Test
	void circuitBreakerTest () {
		int concurrentRequests = 100;
		int errCount = 0;

		float duration = 0;

		LocalDateTime localDateTime;

		String url = "http://teacher-service:8085/teachers";

		for (int i = 0; i < concurrentRequests; i++) {
			localDateTime = LocalDateTime.now();
			HttpGet request = new HttpGet(url);
			try (CloseableHttpClient httpClient = HttpClients.createDefault();
				 CloseableHttpResponse response = httpClient.execute(request)) {
				Duration dur = Duration.between(localDateTime, LocalDateTime.now());
				System.out.println("Request duration: " + dur.toString());
				if(response.getStatusLine().getStatusCode() >= 400){
					errCount ++;
				}
				// Get HttpResponse Status
				System.out.println(response.getStatusLine().getStatusCode());
				System.out.println(response.getStatusLine().getReasonPhrase());

			} catch (ClientProtocolException e) {
				errCount++;
			} catch (IOException e) {
				errCount++;
			}
		}

		System.out.println("Requests sent: " + concurrentRequests + " Request failed: " + errCount);
	}


}
