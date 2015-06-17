package com.biblioteca.search;

import com.biblioteca.listener.Listener;
import com.biblioteca.model.*;

import java.util.List;

public interface UserSearcher {

    String getUserName();

    String getPassword();

    void visit(User user);

    List<User> searchResults();

    void addListener(Listener listener);

}
