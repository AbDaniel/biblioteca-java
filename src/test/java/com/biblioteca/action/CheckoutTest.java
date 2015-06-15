package com.biblioteca.action;

import com.biblioteca.model.User;
import com.biblioteca.search.BookSearcher;
import com.biblioteca.repository.Library;
import com.biblioteca.search.Searcher;
import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;

public class CheckoutTest {

    @Mock
    private Library library;

    @Mock
    private User user;

    private Checkout command;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        command = new Checkout(library, user, "Lord of the Rings");
    }

    @Test
    public void shouldCallCheckoutWithGivenInput() {
        String bookName = "Lord of the Rings";

        command.execute();

        verify(library).checkout(eq(bookName), Matchers.any(User.class), any(Searcher.class));
    }

    @Test
    public void shouldVerifyEqualsContract() {
        EqualsVerifier.forClass(Checkout.class).usingGetClass().suppress(Warning.NULL_FIELDS).verify();
    }

}