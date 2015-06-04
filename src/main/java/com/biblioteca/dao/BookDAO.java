package com.biblioteca.dao;

import com.biblioteca.model.Book;

import java.util.ArrayList;
import java.util.List;

public class BookDAO {

    private List<Book> books;

    private BookDAO() {
        books = new ArrayList<>();
        books.add(new Book("Lord of the Rings", "JR Toliken", 1930));
        books.add(new Book("Harry Potter", "JK Rowling", 1992));
        books.add(new Book("Catch-22", "Joesph Heller", 1950));
        books.add(new Book("Winds of Winter", "George RR Martin", 2017));
    }

    public List<Book> books() {
        return books;
    }

    private static BookDAO bookDAO;

    public static BookDAO getInstance() {
        if (bookDAO == null)
            bookDAO = new BookDAO();
        return bookDAO;
    }

}
