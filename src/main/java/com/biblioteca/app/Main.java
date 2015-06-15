package com.biblioteca.app;

import com.biblioteca.action.Login;
import com.biblioteca.action.Parser;
import com.biblioteca.controller.Controller;
import com.biblioteca.controller.LoginController;
import com.biblioteca.model.*;
import com.biblioteca.repository.Library;
import com.biblioteca.view.MenuView;
import com.biblioteca.view.SubMenuView;
import com.biblioteca.view.View;
import com.biblioteca.visitor.BookVisitor;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static com.biblioteca.constants.Constants.WELCOME_TEXT;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        View view = new View(scanner);
        SubMenuView subMenuView = new SubMenuView(scanner);
        MenuView menuView = new MenuView(scanner, subMenuView);

        Library bookLibrary = new Library(loadBorrowables(view), new BookVisitor(new ArrayList<>(), Book.REGULAR_BOOK_FORMAT));
        Library movieLibrary = new Library(loadMovie(view), new BookVisitor(new ArrayList<>(), Book.REGULAR_BOOK_FORMAT));
        bookLibrary.addListener(view);
        movieLibrary.addListener(view);

        Parser parser = new Parser(bookLibrary, movieLibrary);

        Login login = new Login(loadUsers());

        Controller controller = new Controller(menuView, parser);
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

    private static List<Borrowable> loadBorrowables(View view) {
        List<Borrowable> availables = new ArrayList<>();
        availables.add(new AvailableBook("Lord of the Rings", "JR Toliken", 1930));
        availables.add(new AvailableBook("Harry Potter", "JK Rowling", 1992));
        availables.add(new AvailableBook("Catch-22", "Joesph Heller", 1950));
        availables.add(new AvailableBook("Winds of Winter", "George RR Martin", 2017));
        availables.forEach(borrowable -> borrowable.addListener(view));
        return availables;
    }

    private static List<Borrowable> loadMovie(View view) {
        List<Borrowable> availables = new ArrayList<>();
        availables.add(new Movie("The Matrix", "The Wachowskis", 1999, 10));
        availables.add(new Movie("Departed", "Martin Scorsese", 2005, 8));
        availables.add(new Movie("The Dark Knight", "Nolan", 2008, 10));
        availables.add(new Movie("Seven Samurai", "Akira Kurosawa", 1950, 10));
        availables.forEach(borrowable -> borrowable.addListener(view));
        return availables;
    }

}
