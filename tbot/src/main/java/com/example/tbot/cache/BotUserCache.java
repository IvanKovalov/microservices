package com.example.tbot.cache;

import com.example.tbot.models.UserDto;

import java.util.HashMap;
import java.util.Map;

public class BotUserCache {
    private static Map<Long, UserDto> userCache = new HashMap<>();

    public static void addUserDto (UserDto userDto) {
        if(userDto.getId_telegram()!=null){
            userCache.put(userDto.getId_telegram(), userDto);
        }
    }

    public static UserDto getUserDtoById (Long telegramId){
        return userCache.get(telegramId);
    }

    public static void removeUserDto(UserDto userDto) {
        userCache.remove(userDto.getId_telegram());
    }

}
