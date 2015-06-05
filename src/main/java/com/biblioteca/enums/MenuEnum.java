package com.biblioteca.enums;

public enum MenuEnum {

    LIST_BOOKS(1, "List all books"),
    QUIT(2, "Quit Biblioteca!! (Highly Recommended!!)");

    private int code;
    private String text;

    MenuEnum(int code, String text) {
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
