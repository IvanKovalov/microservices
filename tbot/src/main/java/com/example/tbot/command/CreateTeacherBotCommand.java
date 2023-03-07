package com.example.tbot.command;

import com.example.tbot.sendmessageservice.SendBotMessageService;
import org.telegram.telegrambots.meta.api.objects.Update;

public class CreateTeacherBotCommand implements BotCommand{
    SendBotMessageService sendBotMessageService;

    public CreateTeacherBotCommand (SendBotMessageService sendBotMessageService){

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
        sendBotMessageService.sendMessage(telegramId.toString(), "Please write new teacher in format: \n{\n" +
                "\"name\": \"example\",\n" +
                "\"surname\": \"example\",\n" +
                "\"fatherName\": \"example\",\n" +
                "\"posada\":  \"example\" \n}");
        return false;
    }
}
