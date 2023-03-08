package com.example.tbot.command;

import com.example.tbot.http.HttpSender;
import com.example.tbot.http.HttpSenderI;
import com.example.tbot.sendmessageservice.SendBotMessageService;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.io.IOException;

public class ShowTeacherBotCommand implements BotCommand{

    private final HttpSender httpSender = new HttpSenderI();
    private final SendBotMessageService sendBotMessageService;
    public ShowTeacherBotCommand (SendBotMessageService sendBotMessageService){
        this.sendBotMessageService = sendBotMessageService;
    }

    @Override
    public boolean execute(Update update) {
        //System.out.println(update.getMessage().getText());

        String response = httpSender.sendGetRequest("http://teacher-service:8085/teacher/" + update.getMessage().getText());
        sendBotMessageService.sendMessage(String.valueOf(update.getMessage().getChatId()), response);
        return false;
    }
}
