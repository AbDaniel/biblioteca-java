package com.biblioteca.search;

import com.biblioteca.listener.Listener;
import com.biblioteca.model.*;

import java.util.List;

public interface Searcher {

    String getSearchString();

    default void visit(Book book) {

    }


    default void visit(AvailableBook book) {

    }

    default void visit(CheckedOutBook book) {

    }

    default void visit(AvailableMovie movie) {

    }

    default void visit(CheckedoutMovie movie) {

    }


    default void visit(Movie movie) {

    }

    List<? extends Borrowable> searchResults();

    void addListener(Listener listener);

}
