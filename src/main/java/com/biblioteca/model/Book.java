package com.biblioteca.model;

import com.biblioteca.listener.Listener;

import java.util.function.Function;

import static com.biblioteca.constants.Constants.*;

public abstract class Book implements Borrowable<Book> {

    protected final String name;
    protected String author;
    protected int year;
    protected Listener listener;

    public Book(String name, String author, int year) {
        this.name = name;
        this.author = author;
        this.year = year;
    }

    public Book(Book book) {
        this.name = book.name;
        this.author = book.author;
        this.year = book.year;
        this.listener = book.listener;
    }

    @Override
    public String toString(Function<? super Book, String> format) {
        return format.apply(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;

        return !(name != null ? !name.equals(book.name) : book.name != null);
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", year=" + year;
    }

    @Override
    public void addListener(Listener listener) {
        this.listener = listener;
    }

    public static Function<Book, String> REGULAR_BOOK_FORMAT = book -> "name='" + book.name + '\'' +
            ", author='" + book.author + '\'' +
            ", year=" + book.year;

    @Override
    public Book checkoutBorrowable(User user) {
        return null;
    }

    @Override
    public Book returnBorrowable(User user) {
        return null;
    }

}