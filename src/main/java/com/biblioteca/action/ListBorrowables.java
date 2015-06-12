package com.biblioteca.action;

import com.biblioteca.view.BorrowablesListView;
import com.biblioteca.model.Borrowable;
import com.biblioteca.model.Owner;
import com.biblioteca.repository.Library;

public class ListBorrowables<T extends Borrowable> implements Action {

    private final Library<T> library;
    private final BorrowablesListView listView;

    public ListBorrowables(Library<T> library, BorrowablesListView borrowablesListView) {
        this.library = library;
        this.listView = borrowablesListView;
    }

    @Override
    public void execute(Owner owner) {
        listView.displayListOfBorrowables(library.allAvailableItems());
    }

}
