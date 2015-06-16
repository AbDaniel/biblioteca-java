package com.biblioteca.model;

import com.biblioteca.listener.Listener;

import java.util.function.Function;

import static com.biblioteca.constants.Constants.*;

public abstract class Book implements Borrowable<Book> {

    protected final String name;
    protected String author;
    protected int year;
    protected boolean checkedOut;
    protected Listener listener;

    public Book(String name, String author, int year) {
        this.name = name;
        this.author = author;
        this.year = year;
        this.checkedOut = false;
    }

    public Book(Book book) {
        this.name = book.name;
        this.author = book.author;
        this.year = book.year;
        this.checkedOut = book.isCheckedOut();
        this.listener = book.listener;
    }

    @Override
    public boolean checkout(User user) {
        if (checkedOut) {
            listener.update(BOOK_NOT_PRESENT_TEXT);
            return false;
        } else {
            checkedOut = true;
            user.addBorrowable(this);
            listener.update(SUCCESSFUL_BOOK_CHECKOUT);
            return true;
        }
    }

    @Override
    public boolean returnItem(User user) {
        if (checkedOut) {
            checkedOut = false;
            user.removeOwnable(this);
            listener.update(SUCCESSFUL_BOOK_RETURN);
            return true;
        } else {
            listener.update(BOOK_NOT_VALID_TEXT);
            return false;
        }
    }

    @Override
    public boolean isCheckedOut() {
        return checkedOut;
    }

    @Override
    public String toString(Function<? super Book, String> format) {
        return format.apply(this);
    }

    @Override
    public boolean isEqualTo(String itemName) {
        return name.equals(itemName);
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