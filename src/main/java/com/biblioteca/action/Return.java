package com.biblioteca.action;

import com.biblioteca.model.User;
import com.biblioteca.repository.Library;
import com.biblioteca.search.CheckedOutBookSearcher;
import com.biblioteca.search.Searcher;

import java.util.ArrayList;

public class Return implements Action {

    private final Library library;
    private final User user;
    private final String itemName;
    private Searcher searcher;

    public Return(Library library, User user, String itemName, Searcher searcher) {
        this.library = library;
        this.user = user;
        this.itemName = itemName;
        this.searcher = searcher;
    }

    @Override
    public void execute() {
        library.returnItem(itemName, user, searcher);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Return aReturn = (Return) o;

        if (library != null ? !library.equals(aReturn.library) : aReturn.library != null) return false;
        return !(itemName != null ? !itemName.equals(aReturn.itemName) : aReturn.itemName != null);

    }

    @Override
    public int hashCode() {
        int result = library != null ? library.hashCode() : 0;
        result = 31 * result + (itemName != null ? itemName.hashCode() : 0);
        return result;
    }
}
