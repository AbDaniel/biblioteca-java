package com.biblioteca.enums;

public enum MenuItems {

    LIST_BOOKS(1, "List all books"),
    QUIT(2, "Quit Biblioteca");

    private int code;
    private String text;

    MenuItems(int code, String text) {
        this.code = code;
        this.text = text;
    }

    public int getCode() {
        return code;
    }

    public String getText() {
        return code + ". " + text;
    }

}
