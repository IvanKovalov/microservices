package com.example.tbot.command;

import com.example.tbot.models.ClassroomDto;
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

public class AddClassroomBotCommand implements BotCommand{
    private final SendBotMessageService sendBotMessageService;

    public AddClassroomBotCommand(SendBotMessageService sendBotMessageService) {
        this.sendBotMessageService = sendBotMessageService;
    }
    @Override
    public boolean execute(Update update) {
        String messageContent = update.getMessage().getText();
        ClassroomDto classroomDto;
        try {
            classroomDto = new ObjectMapper().registerModule(new JavaTimeModule()).readValue(messageContent, ClassroomDto.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        String classroomId = "";
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            HttpUriRequest httppost = RequestBuilder.post()
                    .setUri(new URI("http://classroom-service:8085/classroom"))
                    .addParameter("buildingNum", String.valueOf(classroomDto.getBuildingNum()))
                    .addParameter("roomNum", String.valueOf(classroomDto.getRoomNum()))
                    .build();

            CloseableHttpResponse response = httpclient.execute(httppost);
            try {
                classroomId = EntityUtils.toString(response.getEntity()).split("\"id\":")[1].substring(0,1);
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

        sendBotMessageService.sendMessage(String.valueOf(update.getMessage().getChatId()), "Your classroom has been added, with id: " + classroomId);


        return true;
    }
}
