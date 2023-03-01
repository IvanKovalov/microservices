package com.example.tbot.models;


public enum PositionMenu {
    MENU_START,
    MENU_CREATE_SCHEDULE("/add_schedule"),
    MENU_SEARCH_SCHEDULE("/show_schedule"),
    ;

    private String nextCommand;

    PositionMenu(String nextCommand) {
        this.nextCommand = nextCommand;
    }

    PositionMenu() {
    }

    public String getNextCommand() {
        return this.nextCommand;
    }
}
