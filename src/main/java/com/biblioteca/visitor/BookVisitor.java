package com.biblioteca.visitor;

import com.biblioteca.model.Book;
import com.biblioteca.model.Movie;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class BookVisitor implements Visitor {

    private List<Book> books;
    private Function<Book, String> format;

    public BookVisitor(Function<Book, String> format) {
        this.format = format;
        this.books = new ArrayList<>();
    }

    @Override
    public void visit(Book book) {
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
