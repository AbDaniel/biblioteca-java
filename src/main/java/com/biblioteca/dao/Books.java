package com.biblioteca.dao;

import com.biblioteca.model.Book;

import java.util.ArrayList;
import java.util.List;

public class Books {

    private List<Book> availableBooks;
    private List<Book> checkedOutBooks;

    public Books(List<Book> availableBooks, List<Book> checkedOutBooks) {
        this.availableBooks = availableBooks;
        this.checkedOutBooks = checkedOutBooks;
    }

    //    public Books() {
//        availableBooks = new ArrayList<>();
//        availableBooks.add(new Book("Lord of the Rings", "JR Toliken", 1930));
//        availableBooks.add(new Book("Harry Potter", "JK Rowling", 1992));
//        availableBooks.add(new Book("Catch-22", "Joesph Heller", 1950));
//        availableBooks.add(new Book("Winds of Winter", "George RR Martin", 2017));
//    }

    public List<Book> allAvialableBooks() {
        return availableBooks;
    }

    public Book findByName(String bookName) {
        int index = availableBooks.indexOf(new Book(bookName));
        if (index == -1)
            return null;
        return availableBooks.get(index);
    }

}
