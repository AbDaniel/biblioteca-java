package com.biblioteca.action;

import com.biblioteca.repository.Library;
import com.biblioteca.view.ListView;
import com.biblioteca.visitor.Visitor;

public class ListLibrary implements Action {

    private final Library library;
    private final ListView listView;
    private Visitor visitor;

    public ListLibrary(Library library, ListView listView, Visitor visitor) {
        this.library = library;
        this.listView = listView;
        this.visitor = visitor;
    }

    @Override
    public void execute() {
        library.allAvailableItems(visitor);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ListLibrary that = (ListLibrary) o;

        if (library != null ? !library.equals(that.library) : that.library != null) return false;
        return !(visitor != null ? !visitor.getClass().equals(that.visitor.getClass()) : that.visitor != null);

    }

    @Override
    public int hashCode() {
        return library != null ? library.hashCode() : 0;
    }
}
