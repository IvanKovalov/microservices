package com.example.tbot.command;

import com.example.tbot.models.ScheduleDto;
import com.example.tbot.sendmessageservice.SendBotMessageService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class AddScheduleBotCommand implements BotCommand{
    SendBotMessageService sendBotMessageService;

    public AddScheduleBotCommand(SendBotMessageService sendBotMessageService) {
        this.sendBotMessageService = sendBotMessageService;
    }
    @Override
    public boolean execute(Update update) {
        String messageContent = update.getMessage().getText();
        ScheduleDto scheduleDto;
        try {
            scheduleDto = new ObjectMapper().registerModule(new JavaTimeModule()).readValue(messageContent, ScheduleDto.class);
            System.out.println(scheduleDto.getClassId());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        String scheduleId = "";
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            HttpUriRequest httppost = RequestBuilder.post()
                    .setUri(new URI("http://127.0.0.1/schedule"))
                    .addParameter("teacherId", String.valueOf(scheduleDto.getTeacherId()))
                    .addParameter("studentId", String.valueOf(scheduleDto.getStudentId()))
                    .addParameter("classId", String.valueOf(scheduleDto.getClassId()))
                    .addParameter("subject", scheduleDto.getSubject())
                    .addParameter("meetingTime", "2023-02-28T18:55:41.014")
                    .build();

            CloseableHttpResponse response = httpclient.execute(httppost);
            try {
                scheduleId = EntityUtils.toString(response.getEntity()).split("\"id\":")[1].substring(0,1);
            } finally {
                response.close();
            }
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                httpclient.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        sendBotMessageService.sendMessage(String.valueOf(update.getMessage().getChatId()), "Your schedule has been added, with id: " + scheduleId);


        return true;
    }
}
