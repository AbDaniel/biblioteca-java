package com.biblioteca.visitor;

import com.biblioteca.model.Book;
import com.biblioteca.model.User;

import java.util.ArrayList;
import java.util.Map;

import static com.biblioteca.model.Book.REGULAR_BOOK_FORMAT;

public class BookDefaulterVisitor implements Visitor {

    CheckedoutBookVisitor visitor;
    Map<User, String> userBooks;

    public BookDefaulterVisitor(CheckedoutBookVisitor visitor, Map<User, String> userBooks) {
        this.visitor = visitor;
        this.userBooks = userBooks;
    }

    @Override
    public void visit(User user) {
        userBooks.put(user, visitor.visitables());
        visitor = new CheckedoutBookVisitor(new ArrayList<>(), REGULAR_BOOK_FORMAT);
    }

    @Override
    public String visitables() {
        return userBooks.toString();
    }

}
