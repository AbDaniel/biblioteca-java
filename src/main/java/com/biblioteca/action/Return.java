package com.biblioteca.action;

import com.biblioteca.model.User;
import com.biblioteca.repository.Library;
import com.biblioteca.search.CheckedOutBookSearcher;
import com.biblioteca.search.Searcher;

import java.util.ArrayList;

public class Return implements Action {

    private final Library library;
    private final User user;
    private final Searcher searcher;

    public Return(Library library, User user, Searcher searcher) {
        this.library = library;
        this.user = user;
        this.searcher = searcher;
    }

    @Override
    public void execute() {
        library.returnItem(searcher.getSearchString(), user, searcher);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Return aReturn = (Return) o;

        if (library != null ? !library.equals(aReturn.library) : aReturn.library != null) return false;
        if (user != null ? !user.equals(aReturn.user) : aReturn.user != null) return false;
        return !(searcher != null ? !searcher.equals(aReturn.searcher) : aReturn.searcher != null);

    }

    @Override
    public int hashCode() {
        int result = library != null ? library.hashCode() : 0;
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (searcher != null ? searcher.hashCode() : 0);
        return result;
    }
}
