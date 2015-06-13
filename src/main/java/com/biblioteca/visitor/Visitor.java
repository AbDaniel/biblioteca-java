package com.biblioteca.visitor;

import com.biblioteca.model.Book;

public interface Visitor {

    void visit(Book book);

}
