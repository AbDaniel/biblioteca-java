package com.biblioteca.action;

import com.biblioteca.model.User;
import com.biblioteca.repository.Library;
import com.biblioteca.search.Searcher;
import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CheckoutTest {

    @Mock
    private Library library;

    @Mock
    private User user;

    @Mock
    private Searcher searcher;

    private Checkout command;

    @Before
    public void setUp() throws Exception {
        command = new Checkout(library, user, searcher);
    }

    @Test
    public void shouldCallCheckoutWithGivenInput() {
        String bookName = "Lord of the Rings";

        command.execute();

        verify(library).checkout(Matchers.any(User.class), eq(searcher));
    }

    @Test
    public void shouldVerifyEqualsContract() {
        EqualsVerifier.forClass(Checkout.class).usingGetClass().suppress(Warning.NULL_FIELDS).verify();
    }

}