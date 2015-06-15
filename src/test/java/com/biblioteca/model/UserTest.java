package com.biblioteca.model;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class UserTest {

    User user;
    List<Borrowable> ownables;

    @Before
    public void setUp() {
        String password = "onering";
        ownables = new ArrayList<>();
        user = new User("111-1111", "Sauron", password, ownables);
    }

    @Test
    public void shouldVerifyEqualsContract() {
        EqualsVerifier.forClass(User.class).usingGetClass().verify();
    }

    @Test
    public void shouldAddOwnable() {
        Book book = new AvailableBook("I'm Damned", "Myself", 2025);

        user.addBorrowable(book);
        int actualSize = ownables.size();

        assertEquals(1, actualSize);
    }

    @Test
    public void shouldRemoveOwnable() {
        Book book = new AvailableBook("I'm Damned", "Myself", 2025);
        ownables.add(book);

        user.removeOwnable(book);
        int actualSize = ownables.size();

        assertEquals(0, actualSize);
    }

    @Test
    public void shouldReturnFalseIfCredentialsAreInvalid() {
        String userName = "";
        String password = "";

        assertFalse(user.isValidCredential(userName, password));
    }

    @Test
    public void shouldReturnTrueIfCredentialsAreValid() {
        String userName = "111-1111";
        String password = "onering";

        assertTrue(user.isValidCredential(userName, password));
    }

}