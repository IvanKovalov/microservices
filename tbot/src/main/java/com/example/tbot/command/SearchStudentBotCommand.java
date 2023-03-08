package com.example.tbot.command;

import com.example.tbot.sendmessageservice.SendBotMessageService;
import org.telegram.telegrambots.meta.api.objects.Update;

public class SearchStudentBotCommand implements BotCommand{
    private final SendBotMessageService sendBotMessageService;

    public SearchStudentBotCommand (SendBotMessageService sendBotMessageService){
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
        sendBotMessageService.sendMessage(String.valueOf(telegramId), "Type id of student");
        return false;
    }
}
