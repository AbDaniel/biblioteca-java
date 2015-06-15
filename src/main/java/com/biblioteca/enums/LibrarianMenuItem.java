package com.biblioteca.enums;

public enum LibrarianMenuItem {
    LIST_BOOK_DEFAULTERS(1, "List all Book defaulters"),
    LIST_MOVIE_DEFAULTERS(2, "List all Movie defaulters");

    private int code;
    private String text;

    LibrarianMenuItem(int code, String text) {
        this.code = code;
        this.text = text;
    }

    public String getText() {
        return code + ". " + text;
    }
}
