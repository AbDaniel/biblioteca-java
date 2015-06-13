package com.biblioteca.visitor;

import com.biblioteca.model.Book;

import java.util.List;
import java.util.function.Function;

public class BookVisitor implements Visitor {

    private List<Book> books;

    public BookVisitor(List<Book> books) {
        this.books = books;
    }

    @Override
    public void visit(Book book) {
        books.add(book);
    }

    public String books(Function<Book, String> format) {
        return null;
    }

}
