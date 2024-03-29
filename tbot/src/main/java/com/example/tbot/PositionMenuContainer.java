package com.example.tbot;

import com.example.tbot.cache.BotUserCache;
import com.example.tbot.command.CommandContainer;
import com.example.tbot.models.PositionMenu;
import com.example.tbot.models.UserDto;
import com.google.common.collect.ImmutableMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

@Service
public class PositionMenuContainer {

    CommandContainer commandContainer;

    private final ImmutableMap<String, PositionMenu> positionMenuImmutableMap;

    @Autowired
    public PositionMenuContainer (CommandContainer commandContainer) {
        positionMenuImmutableMap = ImmutableMap.<String, PositionMenu>builder()
                .put("/create_schedule", PositionMenu.MENU_CREATE_SCHEDULE)
                .put("/search_schedule", PositionMenu.MENU_SEARCH_SCHEDULE)
                .put("/create_teacher", PositionMenu.MENU_CREATE_TEACHER)
                .put("/search_teacher", PositionMenu.MENU_SEARCH_TEACHER)
                .put("/create_student", PositionMenu.MENU_CREATE_STUDENT)
                .put("/create_classroom", PositionMenu.MENU_CREATE_CLASSROOM)
                .put("/search_student", PositionMenu.MENU_SEARCH_STUDENT)
                .put("/search_classroom", PositionMenu.MENU_SEARCH_CLASSROOM)
                .build();
        this.commandContainer = commandContainer;
    }

    public void executeCommand (Update update) {
        String command;
        if(update.hasCallbackQuery()){
            command = update.getCallbackQuery().getData();
        } else {
            command = update.getMessage().getText();
        }
        UserDto userDto = new UserDto();
        userDto.setId_telegram(update.getCallbackQuery().getFrom().getId());
        userDto.setName(update.getCallbackQuery().getFrom().getUserName());
        userDto.setPositionMenu(positionMenuImmutableMap.get(command));
        BotUserCache.addUserDto(userDto);
        commandContainer.retrieveCommand(command).execute(update);
    }
}
