package com.example.tbot.models;


public enum PositionMenu {
    MENU_START,
    MENU_CREATE_SCHEDULE("/add_schedule"),
    MENU_SEARCH_SCHEDULE("/show_schedule"),
    MENU_CREATE_TEACHER("/add_teacher"),
    MENU_SEARCH_TEACHER("/show_teacher")
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
