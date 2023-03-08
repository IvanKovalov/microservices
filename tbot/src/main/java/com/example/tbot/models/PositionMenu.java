package com.example.tbot.models;


public enum PositionMenu {
    MENU_START,
    MENU_CREATE_SCHEDULE("/add_schedule"),
    MENU_SEARCH_SCHEDULE("/show_schedule"),
    MENU_CREATE_TEACHER("/add_teacher"),
    MENU_SEARCH_TEACHER("/show_teacher"),
    MENU_CREATE_STUDENT("/add_student"),
    MENU_CREATE_CLASSROOM("/add_classroom"),

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
