package com.biblioteca.app;

import com.biblioteca.action.Login;
import com.biblioteca.action.Parser;
import com.biblioteca.controller.Controller;
import com.biblioteca.controller.LoginController;
import com.biblioteca.model.Book;
import com.biblioteca.model.Borrowable;
import com.biblioteca.model.User;
import com.biblioteca.repository.Library;
import com.biblioteca.view.MenuView;
import com.biblioteca.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static com.biblioteca.constants.Constants.WELCOME_TEXT;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MenuView menuView = new MenuView(scanner);
        View view = new View(scanner);

        Library library = new Library(loadBorrowables());
        Parser parser = new Parser(library);
        Controller controller = new Controller(menuView, parser);

        Login login = new Login(loadUsers());
        LoginController loginController = new LoginController(login, view);

        BibliotecaApp bibliotecaApp = new BibliotecaApp(WELCOME_TEXT, view, controller, loginController);
        bibliotecaApp.start();

    }

    private static List<User> loadUsers() {
        List<User> users = new ArrayList<>();
        User user = new User("111-1111", "sauron", "onering", new ArrayList<>());
        users.add(user);
        return users;
    }

    private static List<Borrowable> loadBorrowables() {
        List<Borrowable> availableBooks = new ArrayList<>();
        availableBooks.add(new Book("Lord of the Rings", "JR Toliken", 1930));
        availableBooks.add(new Book("Harry Potter", "JK Rowling", 1992));
        availableBooks.add(new Book("Catch-22", "Joesph Heller", 1950));
        availableBooks.add(new Book("Winds of Winter", "George RR Martin", 2017));
        return availableBooks;
    }

}
