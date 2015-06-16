package com.biblioteca.enums;

import com.biblioteca.view.ListView;
import com.biblioteca.view.View;

import java.util.HashMap;
import java.util.Map;

import static com.biblioteca.constants.Constants.*;
import static com.biblioteca.constants.Constants.scanner;

/**
 * Has UI components for listing menu.
 */
public enum MenuItem {

    LIST_BOOKS(1, "List all books", new ListView(scanner), null),
    CHECKOUT_BOOK(2, "Checkout a book", new View(scanner), ENTER_BOOK_NAME),
    RETURN_BOOK(3, "Return a book", new View(scanner), ENTER_BOOK_NAME),
    LIST_MOVIES(4, "List all Movies", new ListView(scanner), null),
    CHECKOUT_MOVIE(5, "Checkout a Movie", new View(scanner), ENTER_MOVIE_NAME),
    RETURN_MOVIE(6, "Return a Movie", new View(scanner), ENTER_MOVIE_NAME),
    LOGOUT(7, "Logout", new View(scanner), null),
    QUIT(8, "Quit Biblioteca", new View(scanner), null),
    INVALID(-1, "", new View(scanner), null);

    private final int code;
    private final String text;
    private View view;
    private String promptText;

    MenuItem(final int code, String text, View view, String promptText) {
        this.code = code;
        this.text = text;
        this.view = view;
        this.promptText = promptText;
    }

    public View view() {
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
        if (isValidMenuItem())
            return code + ". " + text;
        return "";
    }

    public boolean isValidMenuItem() {
        return code > 0;
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

    public String getPromptText() {
        return promptText;
    }
}
