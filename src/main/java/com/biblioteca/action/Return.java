package com.biblioteca.action;

import com.biblioteca.model.User;
import com.biblioteca.repository.Library;

public class Return implements Action {

    private final Library library;
    private final User user;
    private String itemName;

    public Return(Library library, User user, String itemName) {
        this.library = library;
        this.user = user;
        this.itemName = itemName;
    }

    @Override
    public void execute() {
        library.returnItem(itemName, user);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Return aReturn = (Return) o;

        if (library != null ? !library.equals(aReturn.library) : aReturn.library != null) return false;
        return !(user != null ? !user.equals(aReturn.user) : aReturn.user != null);

    }

    @Override
    public int hashCode() {
        int result = library != null ? library.hashCode() : 0;
        result = 31 * result + (user != null ? user.hashCode() : 0);
        return result;
    }
}
