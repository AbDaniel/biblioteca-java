package com.biblioteca.app;

import com.biblioteca.action.*;
import com.biblioteca.model.Borrowable;
import com.biblioteca.view.ListView;
import com.biblioteca.view.MenuView;
import com.biblioteca.view.View;
import com.biblioteca.controller.Controller;
import com.biblioteca.controller.LoginController;
import com.biblioteca.enums.MenuItem;
import com.biblioteca.model.User;
import com.biblioteca.repository.Library;
import com.biblioteca.model.Book;

import java.util.*;

import static com.biblioteca.constants.Constants.*;
import static com.biblioteca.enums.MenuItem.CHECKOUT_BOOK;
import static com.biblioteca.enums.MenuItem.LIST_BOOKS;
import static com.biblioteca.enums.MenuItem.RETURN_BOOK;

public class Main {

    public static void main(String[] args) {
        List<Borrowable> availableBooks = new ArrayList<>();
        availableBooks.add(new Book("Lord of the Rings", "JR Toliken", 1930));
        availableBooks.add(new Book("Harry Potter", "JK Rowling", 1992));
        availableBooks.add(new Book("Catch-22", "Joesph Heller", 1950));
        availableBooks.add(new Book("W" +
                "inds of Winter", "George RR Martin", 2017));

        Scanner scanner = new Scanner(System.in);
        MenuView menuView = new MenuView(scanner);
        ListView listView = new ListView(scanner);
        View view = new View(scanner);

        Library library = new Library(availableBooks);

        Parser parser = new Parser(library);
        Controller controller = new Controller(menuView, parser);

        List<User> users = new ArrayList<>();
        User user = new User("111-1111", "sauron", "onering", new ArrayList<>());
        users.add(user);
        Login login = new Login(users);
        LoginController loginController = new LoginController(login, view);


        BibliotecaApp bibliotecaApp = new BibliotecaApp(WELCOME_TEXT, view, controller, loginController);
        bibliotecaApp.start();

        Action action = (owner) -> {

        };
    }

}
