package com.biblioteca.visitor;

import com.biblioteca.model.AvailableBook;
import com.biblioteca.model.Book;
import com.biblioteca.model.CheckedOutBook;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class CheckedoutBookVisitor implements Visitor {

    private List<Book> books;
    private Function<Book, String> format;

    public CheckedoutBookVisitor(List<Book> books, Function<Book, String> format) {
        this.format = format;
        this.books = books;
    }

    @Override
    public void visit(CheckedOutBook book) {
        books.add(book);
    }

    @Override
    public String visitables() {
        StringBuilder builder = new StringBuilder();
        books.forEach(book -> builder.append(book.toString(format)).append("\n"));
        return builder.toString();
    }

    public int size() {
        return books.size();
    }

}
