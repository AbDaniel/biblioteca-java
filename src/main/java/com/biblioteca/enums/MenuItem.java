package com.biblioteca.enums;

import com.biblioteca.constants.Constants;
import com.biblioteca.view.ListView;
import com.biblioteca.view.View;

import java.util.HashMap;
import java.util.Map;

import static com.biblioteca.constants.Constants.*;

public enum MenuItem {

    LIST_BOOKS(1, "List all books", new ListView(scanner)),
    CHECKOUT_BOOK(2, "Checkout a book", new View(scanner)),
    RETURN_BOOK(3, "Return a book", new View(scanner)),
    LOGOUT(4, "Logout", new View(scanner)),
    QUIT(5, "Quit Biblioteca", new View(scanner));

    private final int code;
    private final String text;
    private View view;

    MenuItem(final int code, String text, View view) {
        this.code = code;
        this.text = text;
        this.view = view;
    }

    public int getCode() {
        return code;
    }

    public View getView() {
        return view;
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
