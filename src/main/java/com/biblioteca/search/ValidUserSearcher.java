package com.biblioteca.search;

import com.biblioteca.listener.Listener;
import com.biblioteca.model.User;

import java.util.List;

import static com.biblioteca.constants.Constants.BOOK_NOT_PRESENT_TEXT;

public class ValidUserSearcher implements UserSearcher {

    private List<User> user;
    private final String userName;
    private final String password;
    private Listener listener;

    public ValidUserSearcher(List<User> user, String userName, String password) {
        this.user = user;
        this.userName = userName;
        this.password = password;
    }

    @Override
    public String getUserName() {
        return userName;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public void visit(User user) {
        this.user.add(user);
    }

    @Override
    public List<User> searchResults() {
        if (user.isEmpty())
            listener.update(BOOK_NOT_PRESENT_TEXT);
        return user;
    }

    @Override
    public void addListener(Listener listener) {
        this.listener = listener;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ValidUserSearcher that = (ValidUserSearcher) o;

        if (userName != null ? !userName.equals(that.userName) : that.userName != null) return false;
        return !(password != null ? !password.equals(that.password) : that.password != null);

    }

    @Override
    public int hashCode() {
        int result = userName != null ? userName.hashCode() : 0;
        result = 31 * result + (password != null ? password.hashCode() : 0);
        return result;
    }
}
