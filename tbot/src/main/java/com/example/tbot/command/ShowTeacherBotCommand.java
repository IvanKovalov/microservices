package com.example.tbot.command;

import com.example.tbot.sendmessageservice.SendBotMessageService;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.io.IOException;

public class ShowTeacherBotCommand implements BotCommand{
    SendBotMessageService sendBotMessageService;
    public ShowTeacherBotCommand (SendBotMessageService sendBotMessageService){
        this.sendBotMessageService = sendBotMessageService;
    }

    @Override
    public boolean execute(Update update) {
        System.out.println(update.getMessage().getText());

        HttpGet request = new HttpGet("http://teacher-service:8085/teacher/" + update.getMessage().getText());
        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(request)) {
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                sendBotMessageService.sendMessage(String.valueOf(update.getMessage().getChatId()), EntityUtils.toString(response.getEntity()));
            }

        } catch (ClientProtocolException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return false;
    }
}
