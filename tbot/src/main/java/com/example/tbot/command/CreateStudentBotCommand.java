package com.example.tbot.command;

import com.example.tbot.sendmessageservice.SendBotMessageService;
import org.telegram.telegrambots.meta.api.objects.Update;

public class CreateStudentBotCommand implements BotCommand{
    private final SendBotMessageService sendBotMessageService;

    public CreateStudentBotCommand (SendBotMessageService sendBotMessageService){

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
        sendBotMessageService.sendMessage(telegramId.toString(), "Please write new student in format: \n{\n" +
                "\"firstName\": \"example\",\n" +
                "\"lastName\": \"example\",\n" +
                "\"patronymic\": \"example\",\n" +
                "\"group\": \"example\" \n}");
        return false;
    }
}
