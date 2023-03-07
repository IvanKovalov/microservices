package com.example.tbot.command;

import com.example.tbot.sendmessageservice.SendBotMessageService;
import org.telegram.telegrambots.meta.api.objects.Update;

public class SearchTeacherBotCommand implements BotCommand{
    SendBotMessageService sendBotMessageService;

    public SearchTeacherBotCommand (SendBotMessageService sendBotMessageService){
        this.sendBotMessageService = sendBotMessageService;
    }

    @Override
    public boolean execute(Update update) {
        Long telegramId;
        if(update.hasCallbackQuery()){
            telegramId = update.getCallbackQuery().getFrom().getId();
        }else {
            telegramId = update.getMessage().getChatId();
        }
        sendBotMessageService.sendMessage(String.valueOf(telegramId), "Type id of teacher");
        return false;
    }
}
