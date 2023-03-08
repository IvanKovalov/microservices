package com.example.tbot.command;

import com.example.tbot.sendmessageservice.SendBotMessageService;
import org.telegram.telegrambots.meta.api.objects.Update;

public class CreateClassroomBotCommand implements BotCommand {
    private final SendBotMessageService sendBotMessageService;

    public  CreateClassroomBotCommand (SendBotMessageService sendBotMessageService){

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
        sendBotMessageService.sendMessage(telegramId.toString(), "Please write new classroom in format: \n{\n" +
                "\"buildingNum\": 1,\n" +
                "\"roomNum\": 2 \n}");
        return false;
    }
}
