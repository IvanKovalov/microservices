package com.example.tbot.command;

import com.example.tbot.sendmessageservice.SendBotMessageService;
import com.google.common.collect.ImmutableMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommandContainer {

    private final ImmutableMap<String, BotCommand> commandMap;


    @Autowired
    public CommandContainer(SendBotMessageService sendBotMessageService) {
        commandMap = ImmutableMap.<String, BotCommand>builder()
                .put("/start", new MainMenuBotCommand(sendBotMessageService))
                .put("/schedule", new ScheduleMenuBotCommand(sendBotMessageService))
                .put("/create_schedule", new CreateScheduleBotCommand(sendBotMessageService))
                .put("/add_schedule", new AddScheduleBotCommand(sendBotMessageService))
                .put("/search_schedule", new SearchScheduleBotCommand(sendBotMessageService))
                .put("/show_schedule", new ShowScheduleBotCommand(sendBotMessageService))
                .put("/teacher", new TeacherMenuBotCommand(sendBotMessageService))
                .put("/create_teacher", new CreateTeacherBotCommand(sendBotMessageService))
                .put("/add_teacher", new AddTeacherBotCommand(sendBotMessageService))
                .put("/search_teacher", new SearchTeacherBotCommand(sendBotMessageService))
                .put("/show_teacher", new SearchTeacherBotCommand(sendBotMessageService))
                .build();

    }

    public BotCommand retrieveCommand(String commandIdentifier) {
        return commandMap.get(commandIdentifier);
    }
}