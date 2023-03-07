package com.example.tbot.command;

import com.example.tbot.sendmessageservice.SendBotMessageService;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public class CreateScheduleBotCommand implements BotCommand{

    SendBotMessageService sendBotMessageService;

    public CreateScheduleBotCommand (SendBotMessageService sendBotMessageService){

        this.sendBotMessageService = sendBotMessageService;
    }
    @Override
    public boolean execute(Update update) {
        Long telegramId;
        if (update.hasCallbackQuery()){
            telegramId = update.getCallbackQuery().getFrom().getId();
        } else {
            telegramId = update.getMessage().getChatId();
        }
        sendBotMessageService.sendMessage(telegramId.toString(), "Please write new schedule in format: \n{\n" +
                "\"teacherId\": 1,\n" +
                "\"studentId\": 2,\n" +
                "\"classId\": 3,\n" +
                "\"subject\": 4,\n" +
                "\"meetingTime\": 2017-01-13T17:09:42.411 \n}");
        return false;
    }
}
