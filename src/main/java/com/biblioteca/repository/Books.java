package com.biblioteca.repository;

import com.biblioteca.model.Book;

import java.util.ArrayList;
import java.util.List;

public class Books {

    private List<Book> books;

    public Books(List<Book> books) {
        this.books = books;
    }

    public List<Book> allAvailableBooks() {
        List<Book> availableBooks = new ArrayList<>();
        if (books != null) {
            for (Book book : books) {
                if (!book.isCheckedOut())
                    availableBooks.add(book);
            }
        }
        return availableBooks;
    }
}
