package com.biblioteca.app;

import com.biblioteca.action.*;
import com.biblioteca.console.BibliotecaConsoleIO;
import com.biblioteca.console.BorrowablesListView;
import com.biblioteca.console.MenuView;
import com.biblioteca.console.View;
import com.biblioteca.controller.Controller;
import com.biblioteca.controller.LoginController;
import com.biblioteca.enums.MenuItem;
import com.biblioteca.model.User;
import com.biblioteca.repository.Borrowables;
import com.biblioteca.model.Book;

import java.util.*;

import static com.biblioteca.constants.Constants.*;
import static com.biblioteca.enums.MenuItem.CHECKOUT_BOOK;
import static com.biblioteca.enums.MenuItem.LIST_BOOKS;
import static com.biblioteca.enums.MenuItem.RETURN_BOOK;
import static java.lang.System.*;

public class Main {

    public static void main(String[] args) {
        List<Book> availableBooks = new ArrayList<>();
        availableBooks.add(new Book("Lord of the Rings", "JR Toliken", 1930));
        availableBooks.add(new Book("Harry Potter", "JK Rowling", 1992));
        availableBooks.add(new Book("Catch-22", "Joesph Heller", 1950));
        availableBooks.add(new Book("W" +
                "inds of Winter", "George RR Martin", 2017));

        BibliotecaConsoleIO bibliotecaConsoleIO = new BibliotecaConsoleIO(new Scanner(in));
        Scanner scanner = new Scanner(System.in);
        MenuView menuView = new MenuView(scanner);
        BorrowablesListView listView = new BorrowablesListView(scanner);
        View view = new View(scanner);

        Borrowables<Book> borrowables = new Borrowables<>(availableBooks);

        Map<MenuItem, Action> actions = new HashMap<>();
        actions.put(LIST_BOOKS, new ListBorrowables<>(borrowables, listView));
        actions.put(CHECKOUT_BOOK, new Checkout(borrowables, bibliotecaConsoleIO));
        actions.put(RETURN_BOOK, new Return(borrowables, bibliotecaConsoleIO));

        Dispatcher dispatcher = new Dispatcher(actions);
        Controller controller = new Controller(menuView, dispatcher);

        List<User> users = new ArrayList<>();
        User user = new User("111-1111", "sauron", "onering", new ArrayList<>());
        users.add(user);
        Login login = new Login(users);
        LoginController loginController = new LoginController(login, view);


        BibliotecaApp bibliotecaApp = new BibliotecaApp(WELCOME_TEXT, bibliotecaConsoleIO, controller, loginController);
        bibliotecaApp.start();
    }

}
