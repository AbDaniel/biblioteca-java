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
        if (listView != null ? !listView.getClass().equals(that.listView.getClass()) : that.listView != null) return
                false;
        return !(visitor != null ? !visitor.getClass().equals(that.visitor.getClass()) : that.visitor != null);

    }

    @Override
    public int hashCode() {
        int result = library != null ? library.hashCode() : 0;
        result = 31 * result + (listView != null ? listView.hashCode() : 0);
        result = 31 * result + (visitor != null ? visitor.hashCode() : 0);
        return result;
    }
}
