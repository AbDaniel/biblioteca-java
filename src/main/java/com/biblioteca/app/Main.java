package com.biblioteca.app;

import com.biblioteca.action.Login;
import com.biblioteca.parser.Parser;
import com.biblioteca.controller.Controller;
import com.biblioteca.controller.LibrarianController;
import com.biblioteca.controller.UserController;
import com.biblioteca.controller.LoginController;
import com.biblioteca.model.*;
import com.biblioteca.repository.Accounts;
import com.biblioteca.repository.Library;
import com.biblioteca.view.LibrarianMenuView;
import com.biblioteca.view.MenuView;
import com.biblioteca.view.SubMenuView;
import com.biblioteca.view.View;

import java.util.*;

import static com.biblioteca.constants.Constants.WELCOME_TEXT;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        View view = new View(scanner);
        SubMenuView subMenuView = new SubMenuView(scanner);
        LibrarianMenuView librarianMenuView = new LibrarianMenuView(scanner);
        MenuView menuView = new MenuView(scanner, subMenuView);

        List<User> users = loadUsers();
        Parser parser = loadParser(view, users);
        Login login = new Login(users);

        UserController userController = new UserController(menuView, parser);
        Map<User, Controller> controllers = loadControllers(users, userController, librarianMenuView, parser);
        LoginController loginController = new LoginController(login, view, controllers);

        BibliotecaApp bibliotecaApp = new BibliotecaApp(WELCOME_TEXT, view, loginController);
        bibliotecaApp.start();
    }

    private static List<User> loadUsers() {
        List<User> users = new ArrayList<>();
        User user = new User("111-1111", "sauron", "onering", new ArrayList<>());
        users.add(user);
        return users;
    }

    private static Parser loadParser(View view, List<User> users) {
        Accounts accounts = new Accounts(users);
        accounts.addListener(view);
        Library borrowableLibrary = new Library(loadBorrowables(view));
        borrowableLibrary.addListener(view);
        return new Parser(borrowableLibrary, accounts);
    }

    private static List<Borrowable> loadBorrowables(View view) {
        List<Borrowable> availables = new ArrayList<>();
        availables.add(new AvailableBook("Lord of the Rings", "JR Toliken", 1930));
        availables.add(new AvailableBook("Harry Potter", "JK Rowling", 1992));
        availables.add(new AvailableBook("Catch-22", "Joesph Heller", 1950));
        availables.add(new AvailableBook("Winds of Winter", "George RR Martin", 2017));
        availables.add(new AvailableMovie("The Matrix", "The Wachowskis", 1999, 10));
        availables.add(new AvailableMovie("Departed", "Martin Scorsese", 2005, 8));
        availables.add(new AvailableMovie("The Dark Knight", "Nolan", 2008, 10));
        availables.add(new AvailableMovie("Seven Samurai", "Akira Kurosawa", 1950, 10));
        availables.forEach(borrowable -> borrowable.addListener(view));
        return availables;
    }

    private static Map<User, Controller> loadControllers(List<User> users, Controller controller, LibrarianMenuView
            menuView, Parser parser) {
        Map<User, Controller> controllers = new HashMap<>();
        for (User user : users) {
            controllers.put(user, controller);
        }
        User librarian = new User("000-0000", "Libraian", "damnit", new ArrayList<>());
        users.add(librarian);
        Controller librarianController = new LibrarianController(menuView, parser);
        controllers.put(librarian, librarianController);
        return controllers;
    }

}
