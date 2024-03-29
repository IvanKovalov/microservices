package com.example.tbot.command;

import com.example.tbot.models.StudentDto;
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

public class AddStudentBotCommand implements BotCommand{

    private final SendBotMessageService sendBotMessageService;

    public AddStudentBotCommand(SendBotMessageService sendBotMessageService) {
        this.sendBotMessageService = sendBotMessageService;
    }
    @Override
    public boolean execute(Update update) {
        String messageContent = update.getMessage().getText();
        StudentDto studentDto;
        try {
            studentDto = new ObjectMapper().registerModule(new JavaTimeModule()).readValue(messageContent, StudentDto.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        String studentId = "";
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            HttpUriRequest httppost = RequestBuilder.post()
                    .setUri(new URI("http://student-service:8085/student"))
                    .addParameter("firstName", studentDto.getFirstName())
                    .addParameter("lastName", studentDto.getLastName())
                    .addParameter("patronymic", studentDto.getPatronymic())
                    .addParameter("group", studentDto.getGroup())
                    .build();

            CloseableHttpResponse response = httpclient.execute(httppost);
            try {
                studentId = EntityUtils.toString(response.getEntity()).split("\"id\":")[1].substring(0,1);
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

        sendBotMessageService.sendMessage(String.valueOf(update.getMessage().getChatId()), "Your student has been added, with id: " + studentId);


        return true;
    }
}
