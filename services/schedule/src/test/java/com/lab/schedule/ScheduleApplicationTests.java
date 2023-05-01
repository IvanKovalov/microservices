package com.lab.schedule;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;

@SpringBootTest
class ScheduleApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void circuitBreakerTest () {
        int concurrentRequests = 100;
        int errCount = 0;

        float duration = 0;

        LocalDateTime localDateTime;

        String uri = "http://schedule-service:8085/schedules";

        for (int i = 0; i < concurrentRequests; i++) {
            localDateTime = LocalDateTime.now();
            HttpGet request = new HttpGet(uri);
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
