package com.example.tbot.telegrambot;

import com.example.tbot.BotUserCache;
import com.example.tbot.PositionMenu;
import com.example.tbot.UserDto;
import com.example.tbot.command.CommandContainer;
import com.example.tbot.command.MainMenuBotCommand;
import com.example.tbot.sendmessageservice.SendBotMessageService;
import com.example.tbot.sendmessageservice.SendBotMessageServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
public class TelegramBot extends TelegramLongPollingBot {
    private static final Logger LOGGER = LoggerFactory.getLogger(TelegramBot.class);
    private final CommandContainer commandContainer;



    @Autowired
    public TelegramBot() {
        this.commandContainer = new CommandContainer(new SendBotMessageServiceImpl(this));

    }


    @Value("${telegram.bot.name}")
    private String botUsername;

    @Value("${telegram.bot.token}")
    private String botToken;

    @Override
    public void onUpdateReceived(Update update) {

        try {
            if (update.hasCallbackQuery()) {
                callbackQueryHandler(update);
            } else if (update.getMessage().hasText() && update.getMessage().isCommand()) {
                commandHandler(update);
            } else {
                textHandler(update);
            }
        } catch (Exception ex) {
            LOGGER.warn(ex.getLocalizedMessage());
        }



    }

    private void textHandler(Update update) {
        UserDto userDto = BotUserCache.getUserDtoById(update.getMessage().getChatId());
        if (userDto.getPositionMenu().equals(PositionMenu.MENU_CREATE_SCHEDULE)){
            commandContainer.retrieveCommand("/add_schedule").execute(update);
        } else if (userDto.getPositionMenu().equals(PositionMenu.MENU_SEARCH_SCHEDULE)){
            commandContainer.retrieveCommand("/show_schedule").execute(update);
        }
    }

    private void commandHandler(Update update) {
        if(update.getMessage().getText().equals("/start")){
            commandContainer.retrieveCommand("/menu").execute(update);
        }else if (update.getMessage().getText().equals("/schedule")){
            commandContainer.retrieveCommand("/schedule").execute(update);
        }
    }

    private void callbackQueryHandler(Update update) {
        if(update.getCallbackQuery().getData().equals("create_schedule")){
            UserDto userDto = new UserDto();
            userDto.setId_telegram(update.getCallbackQuery().getFrom().getId());
            userDto.setName(update.getCallbackQuery().getFrom().getUserName());
            userDto.setPositionMenu(PositionMenu.MENU_CREATE_SCHEDULE);
            BotUserCache.addUserDto(userDto);
            commandContainer.retrieveCommand("/create_schedule").execute(update);
        }else if (update.getCallbackQuery().getData().equals("search_schedule")){
            UserDto userDto = new UserDto();
            userDto.setId_telegram(update.getCallbackQuery().getFrom().getId());
            userDto.setName(update.getCallbackQuery().getFrom().getUserName());
            userDto.setPositionMenu(PositionMenu.MENU_SEARCH_SCHEDULE);
            BotUserCache.addUserDto(userDto);
            commandContainer.retrieveCommand("/search_schedule").execute(update);
        }
    }


    @Override
    public String getBotUsername() {
        return botUsername;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }
}