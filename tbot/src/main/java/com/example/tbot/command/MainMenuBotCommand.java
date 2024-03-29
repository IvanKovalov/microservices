package com.example.tbot.command;

import com.example.tbot.sendmessageservice.SendBotMessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import org.telegram.telegrambots.meta.api.objects.commands.scope.BotCommandScopeDefault;

import java.util.ArrayList;
import java.util.List;

public class MainMenuBotCommand implements com.example.tbot.command.BotCommand {

    private static final Logger LOGGER = LoggerFactory.getLogger(MainMenuBotCommand.class);
    private final SendBotMessageService sendBotMessageService;
    private final List<BotCommand> listOfCommand = new ArrayList<>();

    @Autowired
    public MainMenuBotCommand(SendBotMessageService sendBotMessageService) {
        this.sendBotMessageService = sendBotMessageService;
    }


    @Override
    public boolean execute(Update update) {
        Long id = update.getMessage().getChatId();
        listOfCommand.add(new org.telegram.telegrambots.meta.api.objects.commands.BotCommand("/start", "start"));
        listOfCommand.add(new org.telegram.telegrambots.meta.api.objects.commands.BotCommand("/classroom",  "classroom service"));
        listOfCommand.add(new org.telegram.telegrambots.meta.api.objects.commands.BotCommand("/student",  "student service"));
        listOfCommand.add(new org.telegram.telegrambots.meta.api.objects.commands.BotCommand("/teacher", "teacher service"));
        listOfCommand.add(new org.telegram.telegrambots.meta.api.objects.commands.BotCommand("/schedule", "schedule service"));
        sendBotMessageService.sendMenu(new SetMyCommands(listOfCommand, new BotCommandScopeDefault(), null));
        LOGGER.info("Created main menu successful.");
        return true;
    }
}