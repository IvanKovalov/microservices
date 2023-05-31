package com.lab.student;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;

public class CircuitTest {
    public static void main(String[] args) {
        int concurrentRequests = 100;
        int errCount = 0;

        float duration = 0;

        LocalDateTime localDateTime;

        String uri = "http://student-service:8085/students";

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