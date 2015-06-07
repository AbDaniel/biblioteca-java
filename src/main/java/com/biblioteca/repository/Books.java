package com.biblioteca.repository;

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

    public List<Book> allAvailableBooks() {
        return new ArrayList<>(availableBooks);
    }

    public Book findByName(String bookName) {
        int index = availableBooks.indexOf(new Book(bookName, null, 0));
        if (index == -1)
            return null;
        return availableBooks.get(index);
    }

    public boolean moveToCheckout(Book book) {
        if (availableBooks.remove(book)) {
            checkedOutBooks.add(book);
            return true;
        }
        return false;
    }

    public boolean moveToAvailable(Book book) {
        if (checkedOutBooks.remove(book))
            availableBooks.add(book);
        return false;
    }
}