package com.biblioteca.enums;

import java.util.HashMap;
import java.util.Map;

public enum MenuItem {

    LIST_BOOKS(1, "List all books"),
    QUIT(2, "Quit Biblioteca");

    private final int code;
    private final String text;

    MenuItem(final int code, String text) {
        this.code = code;
        this.text = text;
    }

    public static boolean isInvalidMenuItem(int code) {
        boolean isInvalid = true;
        for (MenuItem menuItem : MenuItem.values()) {
            if (menuItem.code == code) {
                isInvalid = false;
                break;
            }
        }
        return isInvalid;
    }

    public String getText() {
        return code + ". " + text;
    }

    private static Map<Integer, MenuItem> map = new HashMap<>();

    static {
        for (MenuItem menuItem : MenuItem.values()) {
            map.put(menuItem.code, menuItem);
        }
    }

    public static MenuItem valueOf(int code) {
        return map.get(code);
    }

}
