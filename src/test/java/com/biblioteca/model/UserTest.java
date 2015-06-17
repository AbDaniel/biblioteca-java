package com.biblioteca.model;

import com.biblioteca.search.UserSearcher;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserTest {

    User user;
    List<Borrowable> ownables;

    @Mock
    UserSearcher searcher;

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

        user.removeBorrowable(book);
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

    @Test
    public void shouldReturnStateAsStringOnToString() {
        String acutalString = user.toString();

        assertEquals("libraryNo='111-1111', name='Sauron'", acutalString);
    }

    @Test
    public void shouldAddAcceptTheSearcherIfCredentialsAreValid() {
        when(searcher.getUserName()).thenReturn("111-1111");
        when(searcher.getPassword()).thenReturn("onering");

        user.accept(searcher);

        verify(searcher).visit(user);
    }

}