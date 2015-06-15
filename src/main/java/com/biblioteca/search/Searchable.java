package com.biblioteca.search;

import com.biblioteca.visitor.Visitor;

public interface Searchable {
    void match(Searcher searcher);
}
