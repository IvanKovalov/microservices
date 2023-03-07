package com.example.tbot.command;

import com.example.tbot.sendmessageservice.SendBotMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

public class TeacherMenuBotCommand implements BotCommand {
    private final SendBotMessageService sendBotMessageService;

    @Autowired
    public TeacherMenuBotCommand(SendBotMessageService sendBotMessageService) {
        this.sendBotMessageService = sendBotMessageService;
    }

    @Override
    public boolean execute(Update update) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();
        List<InlineKeyboardButton> buttonCreate = new ArrayList<>();
        buttonCreate.add(InlineKeyboardButton.builder()
                .text("Create teacher")
                .callbackData("/create_teacher")
                .build());
        List<InlineKeyboardButton> buttonSearch = new ArrayList<>();
        buttonCreate.add(InlineKeyboardButton.builder()
                .text("Choose teacher by id")
                .callbackData("/search_teacher")
                .build());
        keyboard.add(buttonCreate);
        keyboard.add(buttonSearch);
        inlineKeyboardMarkup.setKeyboard(keyboard);
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText("Choose menu option");
        sendMessage.setChatId(update.getMessage().getChatId());
        sendMessage.setReplyMarkup(inlineKeyboardMarkup);
        sendBotMessageService.sendMessage(sendMessage);
        return true;
    }
}
