package com.biblioteca.visitor;

import com.biblioteca.model.Borrowable;

public interface Visitor<T extends Borrowable> {

    void visit(T t);

}
