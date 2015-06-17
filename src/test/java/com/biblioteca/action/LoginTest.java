package com.biblioteca.action;

import com.biblioteca.model.User;
import com.biblioteca.search.UserSearcher;
import com.biblioteca.search.ValidUserSearcher;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(MockitoJUnitRunner.class)
public class LoginTest {

    Login login;

    @Mock
    UserSearcher searcher;

    @Before
    public void setUp() throws Exception {
        List<User> users = new ArrayList<>();
        User user = new User("111-1111", "sauron", "onering", null);
        users.add(user);
        login = new Login(users);
    }

    @Test
    public void shouldReturnNullOnInvalidLogin() {
        String librayNumber = "112";
        String password = "";
        User user = login.login(librayNumber, password);

        assertNull(user);
    }

    @Test
    public void shouldReturnUserOnValidLogin() {
        String librayNumber = "111-1111";
        String password = "onering";
        User actualUser = login.login(librayNumber, password);

        User expectedUser = new User("111-1111", "", "", null);
        assertEquals(actualUser, expectedUser);
    }

    @Test
    public void shouldReturnNullOnInvalidLoginUsingSearcher() {
        UserSearcher searcher = new ValidUserSearcher(new ArrayList<>(), "111-111", "onering");

        User user = login.login(searcher);

        assertNull(user);
    }

    @Test
    public void shouldReturnUserOnValidLoginOnSearcher() {
        UserSearcher searcher = new ValidUserSearcher(new ArrayList<>(), "111-1111", "onering");
        User actualUser = login.login(searcher);

        User expectedUser = new User("111-1111", "", "", null);
        assertEquals(expectedUser, actualUser);
    }

}
