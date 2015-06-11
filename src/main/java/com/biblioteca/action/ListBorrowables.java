package com.biblioteca.action;

import com.biblioteca.view.BorrowablesListView;
import com.biblioteca.model.Borrowable;
import com.biblioteca.model.Owner;
import com.biblioteca.repository.Borrowables;

public class ListBorrowables<T extends Borrowable> implements Action {

    private final Borrowables<T> borrowables;
    private final BorrowablesListView listView;

    public ListBorrowables(Borrowables<T> borrowables, BorrowablesListView borrowablesListView) {
        this.borrowables = borrowables;
        this.listView = borrowablesListView;
    }

    @Override
    public void execute(Owner owner) {
        listView.displayListOfBorrowables(borrowables.allAvailableItems());
    }

}
