package com.biblioteca.visitor;

import com.biblioteca.model.User;

import java.util.Map;

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
        visitor.reset();
    }

    @Override
    public String visitables() {
        return userBooks.toString();
    }

    @Override
    public void reset() {

    }

}
