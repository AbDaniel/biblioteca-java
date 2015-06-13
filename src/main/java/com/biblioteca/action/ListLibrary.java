package com.biblioteca.action;

import com.biblioteca.view.ListView;
import com.biblioteca.repository.Library;
import com.biblioteca.visitor.Visitor;

public class ListLibrary implements Action {

    private final Library library;
    private final ListView listView;
    private final Visitor visitor;

    public ListLibrary(Library library, ListView listView, Visitor visitor) {
        this.library = library;
        this.listView = listView;
        this.visitor = visitor;
    }

    @Override
    public void execute() {
        library.getAvailableBorrowables(visitor);
        listView.displayMessage(visitor.visitables());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ListLibrary that = (ListLibrary) o;

        if (library != null ? !library.equals(that.library) : that.library != null) return false;
        return !(listView != null ? !listView.equals(that.listView) : that.listView != null);
    }

}
