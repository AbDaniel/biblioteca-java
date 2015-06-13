package com.biblioteca.action;

import com.biblioteca.view.ListView;
import com.biblioteca.model.Borrowable;
import com.biblioteca.model.Owner;
import com.biblioteca.repository.Library;

public class ListLibrary<T extends Borrowable> implements Action {

    private final Library<T> library;
    private final ListView listView;

    public ListLibrary(Library<T> library, ListView listView) {
        this.library = library;
        this.listView = listView;
    }

    @Override
    public void execute(Owner owner) {
        listView.displayListOfBorrowables(library.allAvailableItems());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ListLibrary<?> that = (ListLibrary<?>) o;

        if (library != null ? !library.equals(that.library) : that.library != null) return false;
        return !(listView != null ? !listView.equals(that.listView) : that.listView != null);
    }

}
