package com.biblioteca.enums;

import java.util.HashMap;
import java.util.Map;

public enum LibrarianMenuItem {
    LIST_BOOK_DEFAULTERS(1, "List all Book defaulters"),
    LIST_MOVIE_DEFAULTERS(2, "List all Movie defaulters"),
    LOGOUT(3, "Logout"),
    QUIT(4, "Quit"),
    INVALID(-1, "");

    private int code;
    private String text;

    LibrarianMenuItem(int code, String text) {
        this.code = code;
        this.text = text;
    }

    public String getText() {
        if (isValidMenuItem())
            return code + ". " + text;
        return "";
    }

    public boolean isValidMenuItem() {
        return code > 0;
    }

    public static boolean isInvalidMenuItem(int code) {
        boolean isInvalid = true;
        for (LibrarianMenuItem menuItem : LibrarianMenuItem.values()) {
            if (menuItem.code == code) {
                isInvalid = false;
                break;
            }
        }
        return isInvalid;
    }

    private static Map<Integer, LibrarianMenuItem> map = new HashMap<>();

    static {
        for (LibrarianMenuItem menuItem : LibrarianMenuItem.values()) {
            map.put(menuItem.code, menuItem);
        }
    }

    public static LibrarianMenuItem valueOf(int code) {
        return map.get(code);
    }

}
