package com.biblioteca.model;

import com.biblioteca.visitor.Visitable;
import com.biblioteca.visitor.Visitor;

import java.util.function.Function;

public class Book implements Borrowable<Book>, Visitable {

    private final String name;
    private String author;
    private int year;
    private boolean checkedOut;

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
    public boolean isEqualTo(String itemName) {
        return name.equals(itemName);
    }

    @Override
    public boolean checkout(User user) {
        if (checkedOut) {
            return false;
        } else {
            checkedOut = true;
            user.addBorrowable(this);
            return true;
        }
    }

    @Override
    public boolean returnItem(User user) {
        if (checkedOut) {
            checkedOut = false;
            return true;
        } else {
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
    public void accept(Visitor visitor) {
        visitor.visit(new Book(this));
    }

    public static Function<Book, String> REGULAR_BOOK_FORMAT = book -> "name='" + book.name + '\'' +
            ", author='" + book.author + '\'' +
            ", year=" + book.year;
}