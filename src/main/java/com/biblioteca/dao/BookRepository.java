package com.biblioteca.dao;

import com.biblioteca.model.Book;

import java.util.ArrayList;
import java.util.List;

public class BookRepository {

    private List<Book> books;

    private BookRepository() {
        books = new ArrayList<>();
        books.add(new Book("Lord of the Rings", "JR Toliken", 1930));
        books.add(new Book("Harry Potter", "JK Rowling", 1992));
        books.add(new Book("Catch-22", "Joesph Heller", 1950));
        books.add(new Book("Winds of Winter", "George RR Martin", 2017));
    }

    public List<Book> books() {
        return books;
    }

    private static BookRepository bookRepository;

    public static BookRepository getInstance() {
        if (bookRepository == null)
            bookRepository = new BookRepository();
        return bookRepository;
    }

}
