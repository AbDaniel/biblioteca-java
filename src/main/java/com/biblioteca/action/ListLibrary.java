package com.biblioteca.action;

import com.biblioteca.repository.Library;
import com.biblioteca.view.ListView;

public class ListLibrary implements Action {

    private final Library library;
    private final ListView listView;

    public ListLibrary(Library library, ListView listView) {
        this.library = library;
        this.listView = listView;
    }

    @Override
    public void execute() {
        library.allAvailableItems();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ListLibrary that = (ListLibrary) o;

        return !(library != null ? !library.equals(that.library) : that.library != null);
    }

    @Override
    public int hashCode() {
        return library != null ? library.hashCode() : 0;
    }
}
